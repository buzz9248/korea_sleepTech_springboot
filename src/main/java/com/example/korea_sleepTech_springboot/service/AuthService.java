package com.example.korea_sleepTech_springboot.service;

import com.example.korea_sleepTech_springboot.dto.auth.PasswordResetReqDto;
import com.example.korea_sleepTech_springboot.dto.response.ResponseDto;
import com.example.korea_sleepTech_springboot.dto.user.request.UserSignInReqDto;
import com.example.korea_sleepTech_springboot.dto.user.request.UserSignUpReqDto;
import com.example.korea_sleepTech_springboot.dto.user.response.UserSignInRespDto;
import com.example.korea_sleepTech_springboot.dto.user.response.UserSignUpRespDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface AuthService {
    ResponseDto<UserSignUpRespDto> signup(@Valid UserSignUpReqDto dto);

    ResponseDto<UserSignInRespDto> login(@Valid UserSignInReqDto dto);

    Mono<ResponseEntity<String>> resetPassword(@Valid PasswordResetReqDto dto);
}
