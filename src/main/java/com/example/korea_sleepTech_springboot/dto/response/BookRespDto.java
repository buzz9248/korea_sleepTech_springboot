package com.example.korea_sleepTech_springboot.dto.response;

import com.example.korea_sleepTech_springboot.entity.C_Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRespDto {
    private String writer;
    private String title;
//    private String content;
    private C_Category category;
}
