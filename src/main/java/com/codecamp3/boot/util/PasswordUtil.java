package com.codecamp3.boot.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {
    
    public static String encryptPassword(String password) {
       BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
       return encoder.encode(password);
   }

}