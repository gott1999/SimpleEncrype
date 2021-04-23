package edu.nytd.xww.encryption.implment;

import edu.nytd.xww.encryption.EncryptMethod;
import edu.nytd.xww.utils.FormChanger;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author Yanyu
 * @date 2021年4月23日
 * 兼容DES,2DES,3DES + Base64
 */
public class DesEncryption implements EncryptMethod {

    /**
     * 方法号：加密
     */
    public static final int ENCRYPT_METHOD = 0;

    /**
     * 方法号：解密
     */
    public static final int DECRYPT_METHOD = 1;

    /**
     * 算法
     */
    private String algorithm;

    /**
     * 密钥
     */
    private Key key;

    /**
     * 构造方法,创造56位DES
     */
    public DesEncryption(String algorithm){
        switch (algorithm){
            case "DES":
                createEncryption("DES");
                break;
            case "2DES":
                createEncryption("2DES");
                break;
            case "3DES":
                createEncryption("3DES");
                break;
            default:
                break;
        }
    }

    /**
     * 选择Des系列加密
     * @param algorithm 算法
     * 长度
     */
    private void createEncryption(String algorithm){
        this.algorithm = algorithm;
        createDesKey();
    }

    /**
     * 生成公私钥
     * DES密钥长度56
     */
    private void createDesKey(){
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
            keyGenerator.init(56, new SecureRandom());
            key = keyGenerator.generateKey();
            System.out.println(key.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 加密
     * @param code 明文
     * @return 密文
     */
    private String encrypt(String code){
        try {
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] bytes = Base64.getEncoder().encode(cipher.doFinal(code.getBytes(StandardCharsets.UTF_8)));
            return FormChanger.byteToString(bytes);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     * @param code 密文
     * @return 明文
     */
    private String decrypt(String code){
        try {
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(Base64.getDecoder().decode(code.getBytes())));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 对外开放的操作方法
     * @param code 操作码
     * @param method 操作符号
     *        如下:
     *        ENCRYPT_METHOD 加密
     *        DECRYPT_METHOD 解密
     */
    public String operate(String code, int method){
        switch (method){
            case DECRYPT_METHOD:
                return decrypt(code);
            case ENCRYPT_METHOD:
                return encrypt(code);
            default:
                return null;
        }
    }

    public Key getKey() {
        return key;
    }
}
