package com.example.korea_sleepTech_springboot.dto.file;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostRespDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private FileRespDto file;
}
