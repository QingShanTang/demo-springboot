package org.qingshan.mybatis.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;



public final class AesUtils {
    https://www.cnblogs.com/liunanjava/p/4297854.html

    private static transient Logger log = LoggerFactory.getLogger(AesUtils.class);

    private static final String ENCODE_RULE = "xixi";

    private static final String CHARSET_NAME = "utf-8";

    private static final String KEY_GENERATE_TYPE = "AES";

    private static final String RANDOM_TYPE = "SHA1PRNG";

    static Cipher enCipher;

    static Cipher deCipher;

    static{
        try {

            KeyGenerator keygen = KeyGenerator.getInstance(KEY_GENERATE_TYPE);
            SecureRandom random = SecureRandom.getInstance(RANDOM_TYPE);
            random.setSeed(ENCODE_RULE.getBytes());
            keygen.init(128, random);
            SecretKey original_key = keygen.generateKey();
            byte[] raw = original_key.getEncoded();
            SecretKey key = new SecretKeySpec(raw, KEY_GENERATE_TYPE);
            enCipher = Cipher.getInstance(KEY_GENERATE_TYPE);
            deCipher = Cipher.getInstance(KEY_GENERATE_TYPE);

            enCipher.init(Cipher.ENCRYPT_MODE, key);
            deCipher.init(Cipher.DECRYPT_MODE, key);
        }catch(NoSuchAlgorithmException e){
            log.error("AesUtils init failure, " + e.getMessage(),e);
        }catch(NoSuchPaddingException e){
            log.error("AesUtils init failure, " + e.getMessage(),e);
        }catch(InvalidKeyException e){
            log.error("AesUtils init failure, " + e.getMessage(),e);
        }

    }

    public static String encode(String content) {
        try {
            byte[] byte_encode = content.getBytes(CHARSET_NAME);
            byte[] byte_AES = enCipher.doFinal(byte_encode);
            String AES_encode = new String(Base64.getEncoder().encode(byte_AES));
            return AES_encode;
        }catch (IllegalBlockSizeException e) {
            log.error(e.getMessage(),e);
        } catch (BadPaddingException e) {
            log.error(e.getMessage(),e);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(),e);
        }
        return null;
    }


    public static String decode(String content) {
        try {
            byte[] byte_content = Base64.getDecoder().decode(content);
            byte[] byte_decode = deCipher.doFinal(byte_content);
            String AES_decode = new String(byte_decode, CHARSET_NAME);
            return AES_decode;
        }catch (IllegalBlockSizeException e) {
            log.error(e.getMessage(),e);
        } catch (BadPaddingException e) {
            log.error(e.getMessage(),e);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(),e);
        }
        return null;
    }

    public static void main(String[] args) {
        if (args == null || args.length < 1) {
            String key = "AES";
            System.out.println("key | 123456 | AES");
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
