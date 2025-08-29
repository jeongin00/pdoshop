package com.example.demo.controller.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class ProductListResponse {
    private Integer id;
    private String name;
    private String reviewStatus;
    private List<String> imageUrls;
    private LocalDateTime updateAt; // 업데이트만 가져오기
}

/*
       return new Product(
                null,
                name,
                ReviewStatus.APPROVED,
                user,
                LocalDateTime.now(),
                LocalDateTime.now(),
                baseProduct
        );
*/
