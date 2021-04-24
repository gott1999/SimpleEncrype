package edu.nytd.xww.encryption.implement;

import edu.nytd.xww.encryption.EncryptionMethod;

import java.security.KeyPair;

/**
 * @author Yanyu
 */
public class EccEncryption implements EncryptionMethod {
    /**
     * 默认构造方法
     * 生成
     */
    public EccEncryption(){
        createKey();
    }

    @Override
    public void createKey() {

    }

    @Override
    public byte[] encrypt(byte[] code) {
        return new byte[0];
    }

    @Override
    public byte[] decrypt(byte[] code) {
        return new byte[0];
    }
}
