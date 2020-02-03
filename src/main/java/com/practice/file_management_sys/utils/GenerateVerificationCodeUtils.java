package com.practice.file_management_sys.utils;

import java.util.Random;

/**
 * 生成一个6位随机验证码
 */
public class GenerateVerificationCodeUtils {
    public static String generateCode() {
        Random random = new Random();
        String string = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] ch = new char[6];
        for (int i = 0; i < ch.length; i++) {
            int index = random.nextInt(string.length());
            ch[i] = string.charAt(index);
        }
        return String.valueOf(ch);
    }
}
