package edu.nytd.xww.encryption.implement;

import edu.nytd.xww.encryption.EncryptionMethod;

import javax.crypto.Cipher;
import java.security.*;

/**
 * @author Yanyu
 * @date 2021年4月22日
 * RSA加密
 */
public class RsaEncryption implements EncryptionMethod {

    /**
     * 算法
     */
    private static final String ALGORITHM = "RSA";

    /**
     * 密钥对
     */
    private KeyPair keyPair;

    /**
     * 默认构造方法
     * 生成1024位的密钥对
     */
    public RsaEncryption() {
        createKey();
    }

    @Override
    public void createKey(){
        try{
            KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance(ALGORITHM);
            keyGenerator.initialize(1024,new SecureRandom());
            keyPair = keyGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte[] encrypt(byte[] code){
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
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
            Cipher cipher = Cipher.getInstance(ALGORITHM);
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
