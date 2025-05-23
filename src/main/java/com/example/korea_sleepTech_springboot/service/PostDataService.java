package com.example.korea_sleepTech_springboot.service;

import com.example.korea_sleepTech_springboot.dto.file.PostReqDto;
import com.example.korea_sleepTech_springboot.dto.file.PostRespDto;
import com.example.korea_sleepTech_springboot.dto.response.ResponseDto;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PostDataService {
    ResponseDto<PostRespDto> createPost(@Valid PostReqDto dto, MultipartFile file) throws IOException;

}
