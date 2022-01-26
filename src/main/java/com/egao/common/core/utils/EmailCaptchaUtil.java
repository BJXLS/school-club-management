package com.egao.common.core.utils;

import java.util.Random;

public class EmailCaptchaUtil {
    private static final char[] chars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
            'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
            'v', 'w', 'x', 'y', 'z' };

    private static final int charsLength = chars.length;
    private static final int numberLength = 10;

    public static String getCaptcha(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {

            sb.append(String.valueOf(chars[random.nextInt(charsLength)]));
        }
        return sb.toString();
    }

    public static String getNumberCaptcha(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            sb.append(String.valueOf(chars[random.nextInt(numberLength)]));
        }
        return sb.toString();
    }
}
