package com.pghome;

import java.util.regex.Pattern;

/**
 * @Auther: tianws
 * @Date: 2018/12/12 10:40
 * @Description:
 */
public class test {

    public static void main(String[] args) {
        String regex = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?";
        boolean matches = Pattern.matches(regex, "qiang.xue@hnair.com");
        System.out.println(matches);
    }

}
