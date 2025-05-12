package com.example.korea_sleepTech_springboot.service.implementations;

import com.example.korea_sleepTech_springboot.dto.response.ResponseDto;
import com.example.korea_sleepTech_springboot.dto.user.request.UserSignInReqDto;
import com.example.korea_sleepTech_springboot.dto.user.request.UserSignUpReqDto;
import com.example.korea_sleepTech_springboot.dto.user.response.UserSignInRespDto;
import com.example.korea_sleepTech_springboot.dto.user.response.UserSignUpRespDto;
import com.example.korea_sleepTech_springboot.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public ResponseDto<UserSignUpRespDto> signup(UserSignUpReqDto dto) {
        return null;
    }

    @Override
    public ResponseDto<UserSignInRespDto> login(UserSignInReqDto dto) {
        return null;
    }
}
