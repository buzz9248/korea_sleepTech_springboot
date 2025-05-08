package com.example.korea_sleepTech_springboot.service;

import com.example.korea_sleepTech_springboot.dto.request.CommentCreateReqDto;
import com.example.korea_sleepTech_springboot.dto.request.CommentUpdateReqDto;
import com.example.korea_sleepTech_springboot.dto.response.CommentRespDto;
import com.example.korea_sleepTech_springboot.dto.response.ResponseDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {

    ResponseDto<CommentRespDto> createComment(@Valid CommentCreateReqDto dto);
    ResponseDto<CommentRespDto> updateComment(Long id, @Valid CommentUpdateReqDto dto);
    ResponseDto<Void> deleteComment(Long id);
}
