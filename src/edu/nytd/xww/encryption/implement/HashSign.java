package edu.nytd.xww.encryption.implement;

import edu.nytd.xww.encryption.EncryptionMethod;
import edu.nytd.xww.pojo.Keychain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Yanyu
 * 直接调用
 */
public class HashSign{

    public static final String MD2 = "MD2";
    public static final String MD5 = "MD5";
    public static final String SHA1 = "SHA-1";
    public static final String SHA224 = "SHA-224";
    public static final String SHA256 = "SHA-256";
    public static final String SHA384 = "SHA-384";
    public static final String SHA512 = "SHA-512";

    public static String create(String code, String method){
        switch (method){
            case "MD2":
                return sign(code, MD2);
            case "MD5":
                return sign(code, MD5);
            case "SHA1":
                return sign(code, SHA1);
            case "SHA224":
                return sign(code, SHA224);
            case "SHA256":
                return sign(code, SHA256);
            case "SHA384":
                return sign(code, SHA384);
            case "SHA512":
                return sign(code, SHA512);
            default:
                try {
                    throw new NoSuchAlgorithmException("No such case");
                }catch (Exception e){
                    e.printStackTrace();
                }
                return null;
        }
    }


    private static String sign(String code, String method) {
        try {
            // 指定算法
            MessageDigest digest = MessageDigest.getInstance(method);
            digest.update(code.getBytes());
            // 获取字节数组
            byte[] messageDigest = digest.digest();
            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            // 字节数组转换为 十六进制
            for (byte b : messageDigest) {
                String Hex = Integer.toHexString(b & 0xFF);
                if (Hex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(Hex);
            }
            return hexString.toString().toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
