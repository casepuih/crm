package com.example.crm.manager;

import com.example.crm.service.UserService;

public class TokenManager {
    public static String generateToken(UserService userService){
        String token;
        do {
            token = AleatoireManager.generateAZC(64);
        } while (userService.isTokenExiste(token));
        return token;
    }
}
