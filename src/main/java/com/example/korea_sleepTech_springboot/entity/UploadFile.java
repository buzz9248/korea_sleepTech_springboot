package com.example.korea_sleepTech_springboot.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "uploadFiles")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originalName;
    private String fileName;
    private String filePath;
    private String fileType;
    private long fileSize;

    private Long targetId;
    private String targetType; // "POST" or "COMMUNITY" 등
}
