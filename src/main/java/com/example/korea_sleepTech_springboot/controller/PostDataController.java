package com.example.korea_sleepTech_springboot.controller;

import com.example.korea_sleepTech_springboot.dto.file.PostReqDto;
import com.example.korea_sleepTech_springboot.dto.file.PostRespDto;
import com.example.korea_sleepTech_springboot.dto.response.ResponseDto;
import com.example.korea_sleepTech_springboot.service.PostDataService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post-datas")
public class PostDataController {

    private final PostDataService postDataService;

    // 1) 게시물 등록 API (파일 업로드 기능 포함)
    // consumes로 multipart 설정 필수
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    // @RequestPart
    // : HTTP request Body에 multipart/form-data 가 포함되어 있는 경우에 사용하는 어노테이션
    // - MultipartResolver가 동작하여 분리된 데이터들을 역직렬화
    public ResponseEntity<ResponseDto<PostRespDto>> createPost(@RequestPart("data") @Valid PostReqDto dto, // JSON 형식 데이터
                                                               @RequestPart(value = "file", required = false) MultipartFile file) throws IOException { // 업로드 파일 (선택)

        ResponseDto<PostRespDto> response = postDataService.createPost(dto, file);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



    // 2) 게시물 단건 조회 API




    // 3) 게시물 수정 API




    // 4) 게시물 삭제 API




}
