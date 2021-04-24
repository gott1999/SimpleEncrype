package edu.nytd.xww.utils;

import java.util.Base64;

/**
 * @author Yanyu
 * @date 2021年4月24日
 * 转换
 *      Base 和 二进制编码
 */
public class Base64Encode {

    /**
     * 二进制编码 -> Base64编码
     * @param input 输入
     * @return 输出
     */
    public static byte[] encode(byte[] input){
        return Base64.getEncoder().encode(input);
    }

    /**
     * Base64编码 -> 二进制编码
     */
    public static byte[] decode(byte[] input){
        return Base64.getDecoder().decode(input);
    }

}
