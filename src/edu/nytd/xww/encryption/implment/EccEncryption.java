package edu.nytd.xww.encryption.implment;

import edu.nytd.xww.encryption.EncryptMethod;

import java.security.KeyPair;

/**
 * @author Yanyu
 */
public class EccEncryption implements EncryptMethod {

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
    private static final String ALGORITHM = "ECC";

    /**
     * 密钥对
     */
    private KeyPair keyPair;

    /**
     * 生成公私钥
     * @param length 密钥长度
     */
    private void createKey(int length){ }

    /**
     * 加密
     * @param code 明文
     * @return 密文
     */
    private String encrypt(String code){ return null; }

    /**
     * 解密
     * @param code 密文
     * @return 明文
     */
    private String decrypt(String code){ return null; }

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
}
