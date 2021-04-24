package edu.nytd.xww.encryption.implement;

import edu.nytd.xww.encryption.EncryptionMethod;

import javax.crypto.Cipher;
import java.security.*;

/**
 * @author Yanyu
 * @date 2021年4月22日
 * RSA加密
 */
public class AsymmetricEncryption implements EncryptionMethod {

    /**
     * 密钥对
     */
    private KeyPair keyPair;

    /**
     * 运算器
     */
    private Cipher cipher;

    /**
     * 默认构造方法
     * 生成1024位的密钥对
     */
    public AsymmetricEncryption(String algorithm, int length) {
        fixSign(algorithm);
        createKey(algorithm, length);
    }

    public void fixSign(String algorithm) {
        try {
            cipher = Cipher.getInstance(algorithm);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void createKey(String algorithm, int length){
        try{
            KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance(algorithm);
            keyGenerator.initialize(length,new SecureRandom());
            keyPair = keyGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte[] encrypt(byte[] code){
        try {
            cipher.init(Cipher.ENCRYPT_MODE,keyPair.getPublic());
            return cipher.doFinal(code);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public byte[] decrypt(byte[] code){
        try {
            cipher.init(Cipher.DECRYPT_MODE,keyPair.getPrivate());
            return cipher.doFinal(code);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }



    public Key getPublicKey() {
        return keyPair.getPublic();
    }

    public Key getPrivateKey() {
        return keyPair.getPrivate();
    }
}
