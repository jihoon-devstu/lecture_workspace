package com.sinse.customlogindb.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordCreator {

    public String  getCryptPassword(String pwd) {
        //salt 를 적용하여 비밀번호를 암호화 시켜주는 객체
        //내부적으로 slat를 사용하므로 , 같은 문자열 일지라도 , 매번 생성할 때 마다 암호화 결과물은 매번 바뀐다.

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String result = encoder.encode(pwd);
        return result;
    }
}
