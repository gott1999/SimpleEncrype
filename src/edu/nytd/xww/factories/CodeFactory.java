package edu.nytd.xww.factories;

import edu.nytd.xww.encryption.EncryptionMethod;
import edu.nytd.xww.encryption.implement.SymmetricEncryption;
import edu.nytd.xww.utils.Base64Encode;
import edu.nytd.xww.encryption.implement.AsymmetricEncryption;

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
     * 1024位RSA
     */
    public static final int RSA1024 = 6;

    /**
     * 2048位RSA
     */
    public static final int RSA2048 = 7;

    /**
     * 1024位DSA
     * 签名不一致暂时停用
     */
    public static final int DSA = 8;

    /**
     * 512位DH
     * 签名不一致暂时停用
     */
    public static final int DH = 9;

    /**
     * 256位ECC
     * 签名不一致暂时停用
     */
    public static final int EC = 10;

    private final EncryptionMethod encryption;

    /**
     * 创建加密构造器
     * @param method 加密方法
     *               DES,DES3,AES,RC4
     *               RSA1024,RSA2048,DSA,DH,EC
     */
    public CodeFactory(int method){
        switch (method){
            // 对称加密
            case DES:
                encryption = new SymmetricEncryption("DES", 56);
                break;
            case DES3:
                encryption = new SymmetricEncryption("DESede", 168);
                break;
            case AES:
                encryption = new SymmetricEncryption("AES", 128);
                break;
            case RC4:
                encryption = new SymmetricEncryption("ARCFOUR", 128);
                break;
//            case IDEA:
//                encryption = new SymmetricEncryption("IDEA",128);
//                break;
            // 非对称加密
            case RSA1024:
                encryption = new AsymmetricEncryption("RSA", 1024);
                break;
            case RSA2048:
                encryption = new AsymmetricEncryption("RSA", 2048);
                break;
//            case DSA:
//                encryption = new AsymmetricEncryption("DES", 1024);
//                break;
//            case DH:
//                encryption = new AsymmetricEncryption("DiffieHellman", 1024);
//                break;
//            case EC:
//                encryption = new AsymmetricEncryption("EC", 256);
//                break;
            default:
                System.out.println("不支持的加密方法,已默认构造DES!");
                encryption = new SymmetricEncryption("DES", 56);
                break;
        }
    }

    /**
     * 加密二进制编码
     * @param input 输入
     * @return 输出
     */
    private String encrypt(byte[] input){
        byte[] holder = encryption.encrypt(input);
        return new String(Base64Encode.encode(holder));
    }

    /**
     * 解密二进制编码
     * @param input 输入
     * @return 输出
     */
    private String decrypt(byte[] input){
        byte[] holder = Base64Encode.decode(input);
        return new String(encryption.decrypt(holder));
    }

    /**
     * 加密字符串
     * @param input 输入
     * @return 输出
     */
    public String encrypt(String input){
        return encrypt(input.getBytes());
    }

    /**
     * 解密字符串
     * @param input 输入
     * @return 输出
     */
    public String decrypt(String input){
        return decrypt(input.getBytes());
    }

}
