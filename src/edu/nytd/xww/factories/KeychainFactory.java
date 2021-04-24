package edu.nytd.xww.factories;

import edu.nytd.xww.pojo.Keychain;

import javax.crypto.KeyGenerator;
import java.security.*;
import java.util.Base64;

/**
 * @author Yanyu
 */
public class KeychainFactory {
    /**
     * 56位DES
     */
    public static final int DES = 1;

    /**
     * 168位3DES
     */
    public static final int DES3 = 2;

    /**
     * 168位AES
     */
    public static final int AES = 3;

    /**
     * 168位3DES
     */
    public static final int RC4 = 4;

    /**
     * 128位IDEA
     * 签名不一致暂时停用
     */
    public static final int IDEA = 5;

    /**
     * RSA
     */
    public static final int RSA = 6;

    /**
     * DSA
     * 签名不一致暂时停用
     */
    public static final int DSA = 7;

    /**
     * 512位DH
     * 签名不一致暂时停用
     */
    public static final int DH = 8;

    /**
     * 256位ECC
     * 签名不一致暂时停用
     */
    public static final int EC = 9;

    /**
     * 生成一个钥匙串
     * @param method 加密方法
     * @return 钥匙串
     *
     * <h2>RSA支持1024和2048位</h2>
     * <h2>DH,ECC,DSA,IDEA 由于签名不一致</h2><h1>不要使用!!!</h1>
     */
    public static Keychain createKeychain(int method, int length) {
        switch (method){
            // 对称加密
            case DES:
                return createKey("DES", 56);
            case DES3:
                return createKey("DESede", 168);
            case AES:
                return createKey("AES",128);
            case RC4:
                return createKey("ARCFOUR", 128);
            case IDEA:
                return createKey("IDEA",128);
            // 非对称加密
            case RSA:
                return createKeyPair("RSA",length);
            case DSA:
                return createKeyPair("DSA",length);
            case EC:
                return createKeyPair("EC",length);
            case DH:
                return createKeyPair("DH",length);
            default:
                try {
                    throw new NoSuchAlgorithmException("No Such Algorithm!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
        return null;
    }

    /**
     * 创建对称加密钥匙串
     * @param algorithm 算法
     * @param length 位数
     * @return 钥匙串
     */
    private static Keychain createKey(String algorithm, int length) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
            keyGenerator.init(length, new SecureRandom());
            Key key = keyGenerator.generateKey();
            System.out.println(algorithm + "密钥:" + new String(Base64.getEncoder().encode(key.getEncoded())));
            // 构造一个新的钥匙串，只有对称加密的密钥
            return new Keychain.Builder()
                    .setKey(key)
                    .build();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建非对称加密钥匙串
     * @param algorithm 算法
     * @param length 位数
     * @return 钥匙串
     */
    private static Keychain createKeyPair(String algorithm, int length){
        try{
            KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance(algorithm);
            keyGenerator.initialize(length,new SecureRandom());
            KeyPair keyPair = keyGenerator.generateKeyPair();
            // 构造一个新的钥匙串，只有自己的公私钥
            return new Keychain.Builder()
                    .setPrivateKeyOfMine(keyPair.getPrivate())
                    .setPublicKeyOfMine(keyPair.getPublic())
                    .build();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
