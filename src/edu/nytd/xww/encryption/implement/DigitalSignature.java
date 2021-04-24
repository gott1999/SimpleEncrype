package edu.nytd.xww.encryption.implement;

import edu.nytd.xww.encryption.EncryptionMethod;
import edu.nytd.xww.pojo.Keychain;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author Yanyu
 */
public class DigitalSignature implements EncryptionMethod {

    private Boolean verified = false;

    @Override
    public byte[] encrypt(byte[] code, Keychain keychain) {
        try {
            PrivateKey privateKey = keychain.getPrivateKeyOfMine();
            KeyFactory keyFactory = KeyFactory.getInstance(privateKey.getAlgorithm());
            PKCS8EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
            PrivateKey key = keyFactory.generatePrivate(encodedKeySpec);
            Signature signature = Signature.getInstance("SHA1withRSA");
            signature.initSign(key, new SecureRandom());
            signature.update(code);
            return signature.sign();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public byte[] decrypt(byte[] code, Keychain keychain) {
        try{
            PublicKey publicKey = keychain.getPublicKeyOfOpponent();
            KeyFactory keyFactory = KeyFactory.getInstance(publicKey.getAlgorithm());
            X509EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(publicKey.getEncoded());
            PublicKey key = keyFactory.generatePublic(encodedKeySpec);
            Signature signature = Signature.getInstance("SHA1withRSA");
            signature.initVerify(key);
            signature.update(code);
            verified = signature.verify(code);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Boolean getVerified() {
        return verified;
    }
}
