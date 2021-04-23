package edu.nytd.xww;

import edu.nytd.xww.encryption.DesEncryption;
import edu.nytd.xww.encryption.RsaEncryption;

/**
 * @author Yanyu
 * @date 2021年4月23日
 */
public class Main {
    public static void main(String[] args) {
        // 明文
        String publicCode = "HelloWorld";
        String enCode;
        String deCode;

//        // 生成GUI 未对接
//        MainGui gui = new BorderFrame();

        // 生成RSA密钥
        RsaEncryption rsaMethod = new RsaEncryption();
        System.out.println(rsaMethod.getPrivateKey());
        System.out.println(rsaMethod.getPublicKey());
        // 进行加密
        enCode = rsaMethod.operate(publicCode,RsaEncryption.ENCRYPT_METHOD);
        System.out.println("RSA加密后:" + enCode);
        // 进行解密
        deCode = rsaMethod.operate(enCode,RsaEncryption.DECRYPT_METHOD);
        System.out.println("RSA解密后:" + deCode);

        System.out.println("");

        // DES加密
        DesEncryption desMethod = new DesEncryption("DES");
        System.out.println(desMethod.getKey());
        // 进行加密
        enCode = desMethod.operate(publicCode,DesEncryption.ENCRYPT_METHOD);
        System.out.println("DES加密后:" + enCode);
        // 进行解密
        deCode = desMethod.operate(enCode,DesEncryption.DECRYPT_METHOD);
        System.out.println("DES解密后:" + deCode);


    }
}
