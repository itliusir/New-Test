package com.itliusir.test.str;

import lombok.extern.slf4j.Slf4j;

/**
 * 字符串匹配最长长度
 *
 * @author liugang
 * @since 2019/3/6
 */
@Slf4j
public class TestStrMatchLength {

    public static void main(String[] args) {
        int length = maxSubstring("abcaabbccddxxyyzz", "abcxyyzz");
        log.info("length:{}", length);
    }

    private static int maxSubstring(String str1, String str2) {
        boolean found = false;
        int length = 0;
        int maxLength = str1.length() < str2.length() ? str2.length() : str1.length();
        for (int i = maxLength; i >= 1; i--) {
            for (int j = 0; j <= str1.length() - 1; j++) {
                String str;
                if (j > i) {
                    str = str1.substring(i, j);
                } else {
                    str = str1.substring(j, i);
                }
                if (str2.contains(str)) {
                    if (str.length() > length) {
                        length = str.length();
                    }
                    log.info("maxLength:{}", i);
                    log.info("value:{}", str);
                    found = true;
                }
            }
        }
        return length;
    }
}
