package edu.nytd.xww;

import edu.nytd.xww.factories.CodeFactory;
import edu.nytd.xww.factories.KeychainFactory;
import edu.nytd.xww.pojo.Keychain;

import java.security.Key;
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
     * 消息队列
     */
    private static final Queue<String> STRING_QUEUE = new LinkedList<>();

    public static void main(String[] args) {
        // 模拟发送方
        EXECUTOR.submit(() -> {
            // 待发送的消息
            String msg = "Hello";
            System.out.println("待发送的消息:" + msg);

            // 创建56位DES钥匙串
            Keychain keychain = KeychainFactory.createKeychain(KeychainFactory.RC4,56);

            // 加密工厂 明文->密文
            CodeFactory codeFactory = new CodeFactory(CodeFactory.RC4);
            String encode = codeFactory.encrypt(msg,keychain);
            System.out.println("发送的密文:" + encode);

            // 发送密钥
            assert keychain != null;
            KEY_QUEUE.offer(keychain.getKey());

            // 发送密文
            STRING_QUEUE.offer(encode);
        });

        // 模拟接受方
        EXECUTOR.submit(()->{
            Keychain keychain;
            String enCode;
            String deCode;
            // 约定的算法
            CodeFactory codeFactory = new CodeFactory(CodeFactory.RC4);
            // 接收方查询是否有消息,如果没有进入等待
            while (KEY_QUEUE.isEmpty() || STRING_QUEUE.isEmpty()){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //根据获取的密钥成成钥匙串
            keychain = new Keychain.Builder().setKey(KEY_QUEUE.poll()).build();
            enCode = STRING_QUEUE.poll();

            // 进行解密
            assert enCode != null;
            deCode = codeFactory.decrypt(enCode,keychain);
            System.out.println("接收的密文:" + deCode);
        });

    }
}
