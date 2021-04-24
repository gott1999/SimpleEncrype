package edu.nytd.xww.encryption;

import edu.nytd.xww.pojo.Keychain;

/**
 * @author Yanyu
 * @date 2021年4月24日
 * 抽取了接口
 */
public interface EncryptionMethod {

    /**
     * 加密
     * @param code 明文
     * @param keychain 钥匙串
     * @return 密文
     * 只会加密，不再进行额外操作
     */
    byte[] encrypt(byte[] code, Keychain keychain);

    /**
     * 解密
     * @param code 密文
     * @param keychain 钥匙串
     * @return 明文
     * 确保输入的没有别的加密或者编码
     */
     byte[] decrypt(byte[] code, Keychain keychain);

}
