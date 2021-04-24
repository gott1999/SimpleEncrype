package edu.nytd.xww.encryption;

/**
 * @author Yanyu
 * @date 2021年4月24日
 * 抽取了接口
 */
public interface EncryptionMethod {

    /**
     * 生成密钥
     * @param algorithm 密钥算法
     * @param length 密钥长度
     */
    void createKey(String algorithm, int length);

    /**
     * 加密
     * @param code 明文
     * @return 密文
     * 只会加密，不再进行额外操作
     */
    byte[] encrypt(byte[] code);

    /**
     * 解密
     * @param code 密文
     * @return 明文
     * 确保输入的没有别的加密或者编码
     */
     byte[] decrypt(byte[] code);

}
