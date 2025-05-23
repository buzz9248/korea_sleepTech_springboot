package com.example.korea_sleepTech_springboot.dto.file;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommunityRespDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private List<FileRespDto> files;
}
