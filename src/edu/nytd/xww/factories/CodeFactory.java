package edu.nytd.xww.factories;

import edu.nytd.xww.encryption.EncryptionMethod;
import edu.nytd.xww.encryption.implement.DigitalSignature;
import edu.nytd.xww.encryption.implement.SymmetricEncryption;
import edu.nytd.xww.pojo.Keychain;
import edu.nytd.xww.utils.Base64Encode;
import edu.nytd.xww.encryption.implement.AsymmetricEncryption;

import java.security.NoSuchAlgorithmException;

/**
 * @author Yanyu
 * @date 2021年4月24日
 * 加密算法构造器
 */
public class CodeFactory {
    /**
     * 56位DES
     */
    public static final int DES = 1;

    /**
     * 168位3DES
     */
    public static final int DES3 = 1;

    /**
     * 168位AES
     */
    public static final int AES = 1;

    /**
     * 168位3DES
     */
    public static final int RC4 = 1;

    /**
     * 128位IDEA
     * 签名不一致暂时停用
     */
    public static final int IDEA = 1;

    /**
     * RSA
     */
    public static final int RSA = 2;

    /**
     * 1024位DSA
     * 签名不一致暂时停用
     */
    public static final int DSA = 2;

    /**
     * 512位DH
     * 签名不一致暂时停用
     */
    public static final int DH = 2;

    /**
     * 256位ECC
     * 签名不一致暂时停用
     */
    public static final int EC = 2;

    /**
     * 数字签名
     * SHA1withRSA
     */
    public  static final int SHA1_WITH_RSA = 3;

    private EncryptionMethod encryption;

    /**
     * 创建加密构造器
     * @param method 加密方法
     *            1   DES,DES3,AES,RC4
     *            2   RSA,DH,EC
     *            3   DSA
     */
    public CodeFactory(int method){
        switch (method){
            // 对称加密
            case 1:
                encryption = new SymmetricEncryption();
                break;
            // 非对称加密
            case 2:
                encryption = new AsymmetricEncryption();
                break;
            // 数字签名
            case 3:
                encryption = new DigitalSignature();
                break;
            default:
                try {
                    throw new NoSuchAlgorithmException("No Such Algorithm!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    /**
     * 加密二进制编码
     * @param input 输入
     * @param keychain 钥匙串
     * @return 输出
     */
    private String encrypt(byte[] input, Keychain keychain){
        byte[] holder = encryption.encrypt(input, keychain);
        return new String(Base64Encode.encode(holder));
    }

    /**
     * 解密二进制编码
     * @param input 输入
     * @param keychain 钥匙串
     * @return 输出
     */
    private String decrypt(byte[] input, Keychain keychain){
        byte[] holder = Base64Encode.decode(input);
        return new String(encryption.decrypt(holder, keychain));
    }

    /**
     * 加密字符串
     * @param input 输入
     * @param keychain 钥匙串
     * @return 输出
     */
    public String encrypt(String input, Keychain keychain){
        return encrypt(input.getBytes(), keychain);
    }

    /**
     * 解密字符串
     * @param input 输入
     * @param keychain 钥匙串
     * @return 输出
     */
    public String decrypt(String input, Keychain keychain){
        return decrypt(input.getBytes(), keychain);
    }

}
