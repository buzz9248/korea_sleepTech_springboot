package com.example.korea_sleepTech_springboot.service;

import com.example.korea_sleepTech_springboot.dto.response.ResponseDto;
import com.example.korea_sleepTech_springboot.dto.user.request.UserUpdateReqDto;
import com.example.korea_sleepTech_springboot.dto.user.response.GetUserRespDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ResponseDto<GetUserRespDto> getUserInfo(String userEmail);

    ResponseDto<GetUserRespDto> updateUserInfo(String userEmail, @Valid UserUpdateReqDto dto);

    ResponseDto<Void> deleteUser(String userEmail);
}
