package com.practice.file_management_sys.utils;

import org.springframework.util.Base64Utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncriptionUtil {

    /**
     * 利用MD5进行加密
     * @param str 待加密的字符串
     * @return newStr 加密后的字符串
     */
    public static String EncoderByMD5(String str) {
        MessageDigest md5;
        String newStr = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            newStr = Base64Utils.encodeToString(md5.digest(str.getBytes("utf-8")));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return newStr;
    }

}
