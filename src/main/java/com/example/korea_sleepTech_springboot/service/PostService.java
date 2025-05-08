package com.example.korea_sleepTech_springboot.service;

import com.example.korea_sleepTech_springboot.dto.request.PostCreateReqDto;
import com.example.korea_sleepTech_springboot.dto.response.PostDetailRespDto;
import com.example.korea_sleepTech_springboot.dto.response.PostListRespDto;
import com.example.korea_sleepTech_springboot.dto.response.ResponseDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    public ResponseDto<PostDetailRespDto> createPost(@Valid PostCreateReqDto dto);
    ResponseDto<PostDetailRespDto> getPostById(Long id);
    ResponseDto<List<PostListRespDto>> getAllPosts();
}
