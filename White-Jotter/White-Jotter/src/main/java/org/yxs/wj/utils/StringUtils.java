package org.yxs.wj.utils;

import java.util.Random;

/**
 * @author: yang-xiansen
 * @Date: 2020/08/07 21:58
 * @Description: 生成随机字符串 todo 后期可用common中的工具类代替
 */
public class StringUtils {
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
