package edu.nytd.xww;

import edu.nytd.xww.factories.CodeFactory;

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

        CodeFactory codeFactory = new CodeFactory(CodeFactory.EC);
        enCode = codeFactory.encrypt(publicCode);
        deCode = codeFactory.decrypt(enCode);
        System.out.println("密文:" + enCode);
        System.out.println("解密:" + deCode);
    }
}
