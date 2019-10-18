package org.qingshan.sftp.config.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "sftp")
public class SftpProps {
    /**
     * 用户名称
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 私钥文件路径(可以用密码登录也可以用私钥登录)
     */
    private String identityFile;

    /**
     * 服务器
     */
    private String host;

    /**
     * 端口
     */
    private String port;

    /**
     * 目录
     */
    private String remotefolder;

    /**
     * 最大文件读取数量
     */
    private int maxFileSize;


}
