package com.example.korea_sleepTech_springboot.dto.user.response;

import com.example.korea_sleepTech_springboot.entity.User;

public class UserSignInRespDto {
    private String token;
    private User user;
    private int exprTime; // expire + time: (토큰) 만료 시간
}
