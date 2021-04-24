package edu.nytd.xww;

import edu.nytd.xww.factories.CodeFactory;
import edu.nytd.xww.factories.KeychainFactory;
import edu.nytd.xww.pojo.Keychain;

/**
 * @author Yanyu
 * @date 2021年4月23日
 */
public class Main {
    public static void main(String[] args) {
        // 明文
        String publicCode = "HelloWorld!";
        String enCode;
        String deCode;

        Keychain keychain = KeychainFactory.createKeychain(KeychainFactory.DES,56);
        CodeFactory codeFactory = new CodeFactory(CodeFactory.DES);
        enCode = codeFactory.encrypt(publicCode,keychain);
        deCode = codeFactory.decrypt(enCode,keychain);

        System.out.println("原文:" + enCode);
        System.out.println("密文:" + enCode);
        System.out.println("明文:" + deCode);

    }
}
