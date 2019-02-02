package com.itliusir.test.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5Utils {

    public static void main(String[] args) throws Exception {
        String aaa = "admin";
        System.out.println("md5加密结果32 bit------------->:" + encode(aaa, "").toUpperCase());
    }

    private static String encode(String strSrc, String key) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(strSrc.getBytes("UTF8"));
            StringBuilder result = new StringBuilder(32);
            byte[] temp;
            temp = md5.digest(key.getBytes("UTF8"));
            for (byte b : temp) {
                result.append(Integer.toHexString(
                        (0x000000ff & b) | 0xffffff00).substring(6));
            }
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
