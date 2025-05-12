package com.example.korea_sleepTech_springboot.dto.user.response;

import com.example.korea_sleepTech_springboot.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserSignUpRespDto {
    User user;
}
