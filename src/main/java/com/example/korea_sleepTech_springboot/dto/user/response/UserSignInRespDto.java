package com.example.korea_sleepTech_springboot.dto.user.response;

import com.example.korea_sleepTech_springboot.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserSignInRespDto {
    private String token;
    private User user;
    private int exprTime; // expire + time: (토큰) 만료 시간
}
