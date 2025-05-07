package com.example.korea_sleepTech_springboot.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentRespDto {
    private Long id;
    private Long postId;
    private String content;
    private String commenter;
}
