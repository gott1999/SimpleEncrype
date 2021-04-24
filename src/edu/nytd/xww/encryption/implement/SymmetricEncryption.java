package edu.nytd.xww.encryption.implement;

import edu.nytd.xww.encryption.EncryptionMethod;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author Yanyu
 * @date 2021年4月23日
 * 兼容DES、AES加密
 */
public class SymmetricEncryption implements EncryptionMethod {

    /**
     * 加解密算法
     * 主要为了解决 KeyPairGenerator和 Cipher签名不一致的问题
     */
    private String cipherAlgorithm;

    /**
     * 密钥
     */
    private Key key;

    /**
     * 运算器
     */
    private Cipher cipher;

    /**
     * 默认构造方法
     * 生成56位DES
     */
    public SymmetricEncryption(String algorithm, int length){
        fixSign(algorithm);
        createKey(algorithm, length);
    }

    public void fixSign(String algorithm) {
        try {
            cipher = Cipher.getInstance(algorithm);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void createKey(String algorithm, int length) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
            keyGenerator.init(length, new SecureRandom());
            key = keyGenerator.generateKey();
            System.out.println(key.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public byte[] encrypt(byte[] code){
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(code);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public byte[] decrypt(byte[] code){
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(code);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Key getKey() {
        return key;
    }
}
