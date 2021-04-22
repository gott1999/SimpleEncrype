package edu.nytd.xww.encryption.implment;

import edu.nytd.xww.encryption.EncryptMethod;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;
/**
 * @author Yanyu
 * @date 2021年4月22日
 */
public class RsaEncryption implements EncryptMethod {

    /**
     * 方法号：加密
     */
    public static final int ENCRYPT_METHOD = 0;

    /**
     * 方法号：解密
     */
    public static final int DECRYPT_METHOD = 1;

    /**
     * 算法
     */
    private static final String ALGORITHM = "RSA";

    /**
     * 密钥对
     */
    private KeyPair keyPair;

    /**
     * 任意长度密钥对的构造方法
     * @param length 长度
     */
    public RsaEncryption(int length) {
        createKey(length);
    }

    /**
     * 默认构造方法
     * 生成1024位的密钥对
     */
    public RsaEncryption() {
        this(1024);
    }

    /**
     * 生成公私钥
     */
    private void createKey(int length){
        try{
            KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance(ALGORITHM);
            keyGenerator.initialize(length,new SecureRandom());
            keyPair = keyGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    /**
     * 加密
     * @param code 明文
     * @return 密文
     */
    private String encrypt(String code){
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE,keyPair.getPublic());
            byte[] bytes = code.getBytes(StandardCharsets.UTF_8);
            return new String(Base64.getEncoder().encode(cipher.doFinal(bytes)));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     * @param code 密文
     * @return 明文
     */
    private String decrypt(String code){
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE,keyPair.getPrivate());
            byte[] bytes = Base64.getDecoder().decode(code);
            return new String(cipher.doFinal(bytes));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 对外开放的操作方法
     * @param code 操作码
     * @param method 操作符号
     *        如下:
     *        ENCRYPT_METHOD 加密
     *        DECRYPT_METHOD 解密
     */
    public String operate(String code, int method){
        switch (method){
            case DECRYPT_METHOD:
                return decrypt(code);
            case ENCRYPT_METHOD:
                return encrypt(code);
            default:
                return null;
        }
    }

    public Key getPublicKey() {
        return keyPair.getPublic();
    }

    public Key getPrivateKey() {
        return keyPair.getPrivate();
    }
}
