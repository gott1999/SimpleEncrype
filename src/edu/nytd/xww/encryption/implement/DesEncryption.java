package edu.nytd.xww.encryption.implement;

import edu.nytd.xww.encryption.EncryptionMethod;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.SecureRandom;

/**
 * @author Yanyu
 * @date 2021年4月23日
 * DES加密
 */
public class DesEncryption implements EncryptionMethod {

    /**
     * 算法
     */
    private static final String ALGORITHM = "DES";

    /**
     * 密钥
     */
    private Key key;

    /**
     * 默认构造方法
     * 生成56位DES
     */
    public DesEncryption(){
        createKey();
    }


    @Override
    public void createKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
            keyGenerator.init(56, new SecureRandom());
            key = keyGenerator.generateKey();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public byte[] encrypt(byte[] code){
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
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
            Cipher cipher = Cipher.getInstance(ALGORITHM);
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
