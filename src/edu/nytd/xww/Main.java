package edu.nytd.xww;

import edu.nytd.xww.encryption.implement.HashSign;
import edu.nytd.xww.factories.CodeFactory;
import edu.nytd.xww.factories.KeychainFactory;
import edu.nytd.xww.pojo.Keychain;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.PublicKey;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * @author Yanyu
 * @date 2021年4月23日
 */
public class Main {
    /**
     * 线程池
     */
    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(2);
    /**
     * 密钥队列
     */
    private static final Queue<Key> KEY_QUEUE = new LinkedList<>();
    /**
     * 公钥队列
     */
    private static final Queue<PublicKey> PUBLIC_KEY_QUEUE = new LinkedList<>();
    /**
     * 消息队列
     */
    private static final Queue<String> STRING_QUEUE = new LinkedList<>();

    /**
     * Hash队列
     * @param args
     */
    private static final Queue<String> HASH_QUEUE = new LinkedList<>();

    public static void main(String[] args) {
        // 待发送的消息
        String msg = "Hello";
        System.out.println("待发送的消息:" + msg);
        // 模拟发送方
        EXECUTOR.submit(() -> {
            HASH_QUEUE.offer(HashSign.create(msg, "SHA256"));
            // 创建56位DES钥匙串 对称加密
//            Keychain keychain = KeychainFactory.createKeychain(KeychainFactory.DES,56);
            // 创建512位RSA钥匙串 非对称加密
            Keychain keychain = KeychainFactory.createKeychain(KeychainFactory.RSA,512);

            // 加密工厂 明文->密文
//            CodeFactory codeFactory = new CodeFactory(CodeFactory.DES);
            CodeFactory codeFactory = new CodeFactory(CodeFactory.RSA);
//            CodeFactory codeFactory = new CodeFactory(CodeFactory.SHA1_WITH_RSA);

            String encode = codeFactory.encrypt(msg,keychain);
            System.out.println("发送的密文:" + encode);

            // 确保密钥不为空
            assert keychain != null;

            // 放入钥匙 对称加密用
//            KEY_QUEUE.offer(keychain.getKey());

            // 放入公钥 非对称加密用
            PUBLIC_KEY_QUEUE.offer(keychain.getPublicKeyOfMine());

            // 发送密文
            STRING_QUEUE.offer(encode);
        });


        // 模拟接受方

        EXECUTOR.submit(()->{
            Keychain keychain;
            String enCode;
            String deCode;
            // 约定的算法
//            CodeFactory codeFactory = new CodeFactory(CodeFactory.DES);
            CodeFactory codeFactory = new CodeFactory(CodeFactory.RSA);
//            CodeFactory codeFactory = new CodeFactory(CodeFactory.SHA1_WITH_RSA);
            // 接收方查询是否有消息,如果没有进入等待
//            while (KEY_QUEUE.isEmpty() || STRING_QUEUE.isEmpty()){
            while (PUBLIC_KEY_QUEUE.isEmpty() || STRING_QUEUE.isEmpty()){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 根据获取的密钥 构成钥匙串 对称加密用
//            keychain = new Keychain.Builder().setKey(KEY_QUEUE.poll()).build();
            // 根据获取的公钥 构成钥匙串 非对称加密用
            keychain = new Keychain.Builder().setPublicKeyOfOpponent(PUBLIC_KEY_QUEUE.poll()).build();
            // 添加验证信息, 仅用于目前的验证
//            keychain.setMsg(msg);
            // 获取密文
            enCode = STRING_QUEUE.poll();
            // 进行解密
            assert enCode != null;
            deCode = codeFactory.decrypt(enCode,keychain);
            System.out.println("Hash验证" + HASH_QUEUE.poll().equals(HashSign.create(deCode, "SHA256")));
            System.out.println("验证结果:" + deCode);
        });

    }
}
