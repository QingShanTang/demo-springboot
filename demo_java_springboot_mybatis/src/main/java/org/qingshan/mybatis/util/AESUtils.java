package org.qingshan.mybatis.util;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;


@Slf4j
public final class AESUtils {

    private static final String ENCODE_RULE = "qingshan"; //类似于加盐

    private static final String CHARSET_NAME = "utf-8";

    private static final String KEY_GENERATE_TYPE = "AES";

    private static final String RANDOM_TYPE = "SHA1PRNG";

    static Cipher enCipher;

    static Cipher deCipher;

    static {
        try {
            //1.构造密钥生成器，指定AES算法，不区分大小写
            KeyGenerator keygen = KeyGenerator.getInstance(KEY_GENERATE_TYPE);
            //2.根据encodeRules规则初始化秘钥生成器
            SecureRandom random = SecureRandom.getInstance(RANDOM_TYPE);
            random.setSeed(ENCODE_RULE.getBytes());
            //根据传入的字节数组，生成一个128位的随机源
            keygen.init(128, random);
            //3.产生原始对称密钥
            SecretKey original_key = keygen.generateKey();
            //4.获得原始对称密钥字节数组
            byte[] raw = original_key.getEncoded();
            //根据字节数组生成AES密钥
            SecretKey key = new SecretKeySpec(raw, KEY_GENERATE_TYPE);
            //根据指定算法AES自成密码器
            //加密密码器
            enCipher = Cipher.getInstance(KEY_GENERATE_TYPE);
            //解密密码器
            deCipher = Cipher.getInstance(KEY_GENERATE_TYPE);
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            enCipher.init(Cipher.ENCRYPT_MODE, key);
            deCipher.init(Cipher.DECRYPT_MODE, key);
        } catch (NoSuchAlgorithmException e) {
            log.error("AesUtils init failure, " + e.getMessage(), e);
        } catch (NoSuchPaddingException e) {
            log.error("AesUtils init failure, " + e.getMessage(), e);
        } catch (InvalidKeyException e) {
            log.error("AesUtils init failure, " + e.getMessage(), e);
        }

    }


    /*
     * 加密
     */
    public static String encode(String content) {
        try {
            //8.获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
            byte[] byte_encode = content.getBytes(CHARSET_NAME);
            //9.根据密码器的初始化方式--加密：将数据加密
            byte[] byte_AES = enCipher.doFinal(byte_encode);
            //10.将加密后的数据转换为字符串
            String AES_encode = new String(Base64.getEncoder().encode(byte_AES));
            return AES_encode;
        } catch (IllegalBlockSizeException e) {
            log.error(e.getMessage(), e);
        } catch (BadPaddingException e) {
            log.error(e.getMessage(), e);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }


    /*
     * 解密 （同解密，只是用的不同的密码器）
     */
    public static String decode(String content) {
        try {
            byte[] byte_content = Base64.getDecoder().decode(content);
            byte[] byte_decode = deCipher.doFinal(byte_content);
            String AES_decode = new String(byte_decode, CHARSET_NAME);
            return AES_decode;
        } catch (IllegalBlockSizeException e) {
            log.error(e.getMessage(), e);
        } catch (BadPaddingException e) {
            log.error(e.getMessage(), e);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static void main(String[] args) {
        if (args == null || args.length < 1) {
            String key = "123456";
            System.out.println("key | AESEncode | AESDecode");
            System.out.print(key + " | ");
            String encryptString = encode(key);
            System.out.print(encryptString + " | ");
            String decryptString = decode(encryptString);
            System.out.println(decryptString);
        } else {
            String key = args[0];
            System.out.println("key | AESEncode | AESDecode");
            System.out.print(key + " | ");
            String encryptString = encode(key);
            System.out.print(encryptString + " | ");
            String decryptString = decode(encryptString);
            System.out.println(decryptString);
        }

    }


}
