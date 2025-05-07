package com.example.korea_sleepTech_springboot.시험.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductCreateRequestDto {
    private String name;
    private String description;
    private BigDecimal price;
}
