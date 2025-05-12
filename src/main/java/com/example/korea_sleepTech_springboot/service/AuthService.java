package com.example.korea_sleepTech_springboot.service;

import com.example.korea_sleepTech_springboot.dto.response.ResponseDto;
import com.example.korea_sleepTech_springboot.dto.user.request.UserSignInReqDto;
import com.example.korea_sleepTech_springboot.dto.user.request.UserSignUpReqDto;
import com.example.korea_sleepTech_springboot.dto.user.response.UserSignInRespDto;
import com.example.korea_sleepTech_springboot.dto.user.response.UserSignUpRespDto;
import jakarta.validation.Valid;

public interface AuthService {
    ResponseDto<UserSignUpRespDto> signup(@Valid UserSignUpReqDto dto);

    ResponseDto<UserSignInRespDto> login(@Valid UserSignInReqDto dto);
}
