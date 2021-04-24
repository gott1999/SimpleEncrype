package edu.nytd.xww.helpers;

import edu.nytd.xww.encryption.EncryptionMethod;
import edu.nytd.xww.utils.Base64Encode;
import edu.nytd.xww.encryption.implement.DesEncryption;
import edu.nytd.xww.encryption.implement.RsaEncryption;

/**
 * @author Yanyu
 * @date 2021年4月24日
 * 构造器
 */
public class CodeHelper {

    private final EncryptionMethod encryption;

    public CodeHelper(String method){
        switch (method){
            case "DES":
                encryption = new DesEncryption();
                break;
            case "RSA":
                encryption = new RsaEncryption();
                break;
            default:
                System.out.println("不支持的加密方法,已默认构造RSA!");
                encryption = new DesEncryption();
                break;
        }
    }


    /**
     * 加密二进制编码
     * @param input 输入
     * @return 输出
     */
    public String encrypt(byte[] input){
        byte[] holder = encryption.encrypt(input);
        return new String(Base64Encode.encode(holder));
    }

    /**
     * 解密二进制编码
     * @param input 输入
     * @return 输出
     */
    public String decrypt(byte[] input){
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
