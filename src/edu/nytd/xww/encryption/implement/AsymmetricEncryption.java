package edu.nytd.xww.encryption.implement;

import edu.nytd.xww.encryption.EncryptionMethod;
import edu.nytd.xww.pojo.Keychain;

import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;

/**
 * @author Yanyu
 * @date 2021年4月22日
 * RSA加密
 */
public class AsymmetricEncryption implements EncryptionMethod {

    @Override
    public byte[] encrypt(byte[] code, Keychain keychain){
        try {
            PrivateKey privateKey = keychain.getPrivateKeyOfMine();
            Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return cipher.doFinal(code);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public byte[] decrypt(byte[] code, Keychain keychain){
        try {
            PublicKey publicKey = keychain.getPublicKeyOfOpponent();
            Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return cipher.doFinal(code);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
