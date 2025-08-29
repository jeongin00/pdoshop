package com.example.demo.controller.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProductCreateRequestDto {
    private Integer baseProductId; // 베이스 제품 ID

    // size 검증위치
    private String name; // 상품 이름
    private List<String> imageUrls;


}
