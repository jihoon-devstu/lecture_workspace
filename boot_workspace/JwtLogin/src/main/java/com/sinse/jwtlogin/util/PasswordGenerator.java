package com.sinse.jwtlogin.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static String convertPassword(String password) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String result = encoder.encode(password);

        return result;
    }

    public static void main(String[] args) {
        System.out.println(convertPassword("1234"));
    }


}
