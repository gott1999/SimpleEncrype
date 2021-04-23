package edu.nytd.xww.utils;

import java.nio.charset.StandardCharsets;

/**
 * @author Yanyu
 * @date 2021年4月23日
 */
public class FormChanger {
    public static String byteToString(byte[] bytes){
        return new String(bytes);
    }

    public static byte[] stringToByte(String code){
        return code.getBytes(StandardCharsets.UTF_8);
    }
}
