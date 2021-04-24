package edu.nytd.xww.encryption.implement;

import edu.nytd.xww.encryption.EncryptionMethod;
import edu.nytd.xww.pojo.Keychain;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.util.Base64;

/**
 * @author Yanyu
 * @date 2021年4月23日
 * 兼容DES、3DES、AES加密
 */
public class SymmetricEncryption implements EncryptionMethod {

    @Override
    public byte[] encrypt(byte[] code, Keychain keychain){
        try {
            Key key = keychain.getKey();
            Cipher cipher = Cipher.getInstance(key.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(code);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public byte[] decrypt(byte[] code, Keychain keychain){
        try {
            Key key = keychain.getKey();
            Cipher cipher = Cipher.getInstance(key.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, keychain.getKey());
            return cipher.doFinal(code);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
