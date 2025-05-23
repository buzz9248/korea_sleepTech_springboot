package com.example.korea_sleepTech_springboot.dto.file;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileRespDto {
    private String originalName;
    private String fileName;
    private String filePath;
    private String fileType;
    private long fileSize;
}
