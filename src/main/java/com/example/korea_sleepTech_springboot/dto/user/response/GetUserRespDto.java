package com.example.korea_sleepTech_springboot.dto.user.response;

import com.example.korea_sleepTech_springboot.entity.User;

public class GetUserRespDto {
    private Long id;
    private String email;

    public GetUserRespDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
    }
}
