package org.qingshan.sftp.utils;

import com.jcraft.jsch.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Vector;


public class SftpUtils {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private ChannelSftp sftp;

    private Session session;
    /**
     * 登录用户名
     */
    private String username;
    /**
     * 登录密码
     */
    private String password;

    /**
     * 私钥
     */
    private String identityFile;

    /**
     * 服务器地址IP地址
     */
    private String host;
    /**
     * 端口
     */
    private int port;

    /**
     * 构造基于密码认证的sftp对象
     */
    public SftpUtils(String username, String password, String identityFile, String host, int port) {
        this.username = username;
        this.password = password;
        this.identityFile = identityFile;
        this.host = host;
        this.port = port;
    }

    /**
     * 连接sftp服务器
     */
    public void login() {
        try {
            JSch jsch = new JSch();
            if (StringUtils.isNotBlank(identityFile)) {
                jsch.addIdentity(identityFile);// 设置私钥
                logger.debug(">>>sftp connect,path of identity file：{}" + identityFile);
            }
            logger.debug(">>>sftp connect by host:{} username:{} port:{}", host, username, port);
            // 根据用户名，主机ip，端口获取一个Session对象
            session = jsch.getSession(username, host, port);
            logger.debug(">>>Session is build");
            if (StringUtils.isNotBlank(password)) {
                // 设置密码
                session.setPassword(password);
            }
            //https://blog.csdn.net/aabbcc456aa/article/details/18981279
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            logger.debug(">>>StrictHostKeyChecking no");
            // 为Session对象设置properties
            session.setConfig(config);
            // 通过Session建立链接
            session.connect();
            logger.debug(">>>Session is connected");

            // 打开SFTP通道
            Channel channel = session.openChannel("sftp");
            // 建立SFTP通道的连接
            channel.connect();
            logger.debug(">>>channel is connected");

            sftp = (ChannelSftp) channel;
            logger.debug(">>>Connected successfully to ftpHost = " + host);
        } catch (JSchException e) {
            logger.error(">>>Cannot connect to specified sftp server : host:{},port:{},errorMsg:-->{}", host, port, e.getMessage());
        }
    }

    /**
     * 关闭连接 server
     */
    public void logout() {
        if (sftp != null) {
            if (sftp.isConnected()) {
                sftp.disconnect();
                logger.debug(">>>sftp is closed already");
            }
        }
        if (session != null) {
            if (session.isConnected()) {
                session.disconnect();
                logger.debug(">>>sshSession is closed already");
            }
        }
    }

    /**
     * 将输入流的数据上传到sftp作为文件
     *
     * @param remoteFileFolder sftp端目录
     * @param sftpFileName     sftp端文件名
     * @param input            输入流
     * @throws SftpException
     * @throws Exception
     */
    public void uploadStream(String remoteFileFolder, String sftpFileName, InputStream input) throws SftpException {
        logger.debug(">>>start upload stream {} to remote {}", sftpFileName, remoteFileFolder);
        try {
            sftp.cd(remoteFileFolder);
        } catch (SftpException e) {
            logger.error(">>>directory is not exist {}", e.getMessage());
            sftp.mkdir(remoteFileFolder);
            sftp.cd(remoteFileFolder);
        }
        sftp.put(input, sftpFileName);
        logger.debug(">>>file:{} is upload successful ", sftpFileName);
    }

    /**
     * 下载文件
     *
     * @param remoteFileFolder 下载目录
     * @param downloadFile     下载的文件
     * @param localFileFolder  存在本地的路径+自定义文件名
     * @throws SftpException
     * @throws FileNotFoundException
     */
    public void download(String remoteFileFolder, String downloadFile, String localFileFolder) throws SftpException, FileNotFoundException {
        logger.debug(">>>Start download files from " + remoteFileFolder + " to local folder " + localFileFolder);
        if (remoteFileFolder != null && !"".equals(remoteFileFolder)) {
            sftp.cd(remoteFileFolder);
        }
        File file = new File(localFileFolder);
        sftp.get(downloadFile, new FileOutputStream(file));
        logger.debug(">>>file:{} is download successful" + downloadFile);
    }

    /**
     * 批量下载文件
     *
     * @param remoteFileFolder 下载目录
     * @param fileFormat       下载的文件名称格式 比如（*.xml）
     * @param localFileFolder  存在本地的路径
     * @param whetherDel       是否删除文件
     * @param maxFileSize      最多一次处理的文件数量
     * @throws SftpException
     * @throws IOException
     */
    public void downloadFileByNameFormat(String remoteFileFolder, String fileFormat, String localFileFolder, boolean whetherDel, int maxFileSize) throws SftpException, IOException {
        logger.debug(">>>Start download files from " + remoteFileFolder + " with file format" + fileFormat + " to local folder " + localFileFolder);
        if (remoteFileFolder != null && !"".equals(remoteFileFolder)) {
            sftp.cd(remoteFileFolder);
        }
        Vector<ChannelSftp.LsEntry> fileList = sftp.ls(fileFormat);
        if (fileList != null) {
            int idx = 0;
            for (ChannelSftp.LsEntry channelSftp : fileList) {
                if (maxFileSize > 0) {
                    idx++;
                    if (idx > maxFileSize) {
                        return;
                    }
                }
                String downloadFile = remoteFileFolder + "/" + channelSftp.getFilename();
                File file = new File(localFileFolder + File.separator + channelSftp.getFilename());
                FileOutputStream localFile = new FileOutputStream(file);
                sftp.get(downloadFile, localFile);
                logger.debug(">>>file:{} is download successful", channelSftp.getFilename());
                //这里可以根据项目业务添加一些逻辑，比如检查内容
                if (whetherDel) {
                    sftp.rm(downloadFile);
                    logger.debug("File {} has been delete success", localFileFolder + File.separator + channelSftp.getFilename());
                }
            }
        }
    }

    /**
     * 检查文件
     *
     * @param file
     * @return
     * @throws IOException
     */
    public boolean checkFileContent(File file) throws IOException {
        Path path = FileSystems.getDefault().getPath(file.getAbsolutePath());
        List<String> lines = java.nio.file.Files.readAllLines(path);
        for (String line : lines) {
            if (StringUtils.startsWith(line, "IEA*")) {
                logger.debug("File {} contains {}", file.getAbsolutePath(), "IEA*");
                return true;
            }
        }
        return false;
    }

    public File string2File(String res, String filePath) {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        File distFile = new File(filePath);
        try {
            bufferedReader = new BufferedReader(new StringReader(res));
            bufferedWriter = new BufferedWriter(new FileWriter(distFile));
            char buf[] = new char[1024];         //字符缓冲区
            int len;
            while ((len = bufferedReader.read(buf)) != -1) {
                bufferedWriter.write(buf, 0, len);
            }
            bufferedWriter.flush();
            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }
        return distFile;
    }

    public static void main(String args[]) throws SftpException, FileNotFoundException {
        //测试上传
//        SftpUtils sftp = new SftpUtils("${username}", "${password}", null, "localhost", 22);
//        sftp.login();
//        FileInputStream inputStream = new FileInputStream("/Users/qingshan/Desktop/xixi.html");
//        sftp.uploadStream("${remotefolder}","xixi.html",inputStream);
//        sftp.logout();

//        //测试单个下载
//        SftpUtils sftp = new SftpUtils("${username}", "${password}", null, "localhost", 22);
//        sftp.login();
//        sftp.download("${remotefolder}", "java_error_in_idea_2341.log", "/Users/qingshan/Desktop/xixi.log");
//        sftp.logout();

//        测试批量下载
//        SftpUtils sftp = new SftpUtils("${username}", "${password}", null, "localhost", 22);
//        sftp.login();
//        try {
//            sftp.downloadFileByNameFormat("${remotefolder}", "*.html", "/Users/qingshan/Desktop/xixi/haah", true, 100);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        sftp.logout();
    }
}
