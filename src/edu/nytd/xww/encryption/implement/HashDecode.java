package edu.nytd.xww.encryption.implement;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

/**
 * @author Yanyu
 */
public class HashDecode {

    /**
     * 生成彩虹表 支持3个字母的大小写
     * @return 彩虹表表
     */
    public List<String> getMyRainbowTable(){
        List<String> rainbowTable = new ArrayList<>();
        byte[] temp = new byte[3];
        for (int i = 65; i <= 122; i++) {
            for (int j = 65; j <= 122; j++) {
                for (int k = 65; k <= 122; k++) {
                    if(i >= 91 && i <= 96){ continue; }
                    temp[0] = (byte) i;
                    temp[1] = (byte) j;
                    temp[2] = (byte) k;
                    rainbowTable.add(new String(temp));
                }
            }
        }
        return rainbowTable;
    }

    /**
     * 解码匹配
     * 并没有对算法进行优化,只是暴力遍历
     * @param words 彩虹表
     * @param hash MD5码
     */
    public void decode(List<String> words, String[] hash) {
        for (String word : words) {
            for (String h : hash) {
                if (generate(word).equals(h)) {
                    System.out.println(word + " 的Hash是 \"" + h + "\"");
                }
            }
        }
    }

    /**
     * 计算MD5
     * @param str 字符串
     * @return 可读的字符串
     */
    public String generate(String str) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        // 没有直接Base64编码, 首先按位 与3E 之后加上64
        byte[] bytes = md.digest();
        for (int i = 0; i < bytes.length; i++) {
            // 不限制你想做什么操作, 这只是混淆, 甚至不做都行
            // 但是要尽量考虑到与操作后空余的空间怎么利用
            bytes[i] = (byte) ((bytes[i] & 0x3E) + 64);
        }
        // 如果不编码可能会导致某些特定字符解码、加密出现乱码
        return new String(Base64.getEncoder().encode(bytes));
    }


    public static void main(String[] args) {
        // 初始化
        HashDecode hashDecode = new HashDecode();

        // 初始化要破解的字符串数组
        List<String> list = new ArrayList<>();
        list.add(hashDecode.generate("aaa"));
        list.add(hashDecode.generate("aab"));
        list.add(hashDecode.generate("aba"));
        list.add(hashDecode.generate("baa"));
        list.add(hashDecode.generate("xww"));
        list.add(hashDecode.generate("mei"));
        list.add(hashDecode.generate("hat"));
        String[] h = list.toArray(new String[0]);

        // 生成彩虹表
        var rainbowTable = hashDecode.getMyRainbowTable();
        // 开始破解
        hashDecode.decode(rainbowTable, h);
    }
}
