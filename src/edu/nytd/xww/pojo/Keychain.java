package edu.nytd.xww.pojo;

import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author Yanyu
 * 钥匙
 */
public class Keychain {
    /**
     * 一把钥匙
     */
    private Key key;

    /**
     * 自己的私钥
     */
    private PrivateKey privateKeyOfMine;

    /**
     * 自己的公钥
     */
    private PublicKey publicKeyOfMine;

    /**
     * 对方的公钥
     */
    private PublicKey publicKeyOfOpponent;

    /**
     * 钥匙串构造器
     * 可以给钥匙串挂上：对称加密的钥匙、自己的私钥、自己的公钥、对方的公钥。
     *
     * <h2>钥匙串构造后自己的私钥、自己的公钥不可更改，但是对方的公钥可以。</h2>
     */
    public static class Builder {
        private Key key;
        private PrivateKey myPrivateKey;
        private PublicKey myPublicKey;
        private PublicKey opponentPublicKey;

        public Builder setKey(Key val) {
            this.key = val;
            return this;
        }
        public Builder setPrivateKeyOfMine(PrivateKey val) {
            this.myPrivateKey = val;
            return this;
        }

        public Builder setPublicKeyOfMine(PublicKey val) {
            this.myPublicKey = val;
            return this;
        }

        public Builder setPublicKeyOfOpponent(PublicKey val) {
            this.opponentPublicKey = val;
            return this;
        }

        public Keychain build(){
            Keychain keychain = new Keychain();
            keychain.key = key;
            keychain.privateKeyOfMine = myPrivateKey;
            keychain.publicKeyOfMine = myPublicKey;
            keychain.publicKeyOfOpponent = opponentPublicKey;
            return keychain;
        }
    }


    public void setPublicKeyOfOpponent(PublicKey publicKeyOfOpponent) {
        this.publicKeyOfOpponent = publicKeyOfOpponent;
    }

    public Key getKey() {
        return key;
    }

    public PrivateKey getPrivateKeyOfMine() {
        return privateKeyOfMine;
    }

    public PublicKey getPublicKeyOfMine() {
        return publicKeyOfMine;
    }

    public PublicKey getPublicKeyOfOpponent() {
        return publicKeyOfOpponent;
    }
}
