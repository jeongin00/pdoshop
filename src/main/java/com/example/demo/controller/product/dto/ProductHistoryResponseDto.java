package com.example.demo.controller.product.dto;

import com.example.demo.repository.product.entity.ProductReviewHistory;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ProductHistoryResponseDto {
    private String reviewStatus;
    private String desc;
    private LocalDateTime createdAt;


    public static ProductHistoryResponseDto from(ProductReviewHistory entity) {
        return new ProductHistoryResponseDto(
                entity.getReviewStatus().name(), // Enum -> String으로
                entity.getDesc(),
                entity.getCreatedAt() // 생성일시 = 업데이트
        );
    }
}


/*
               null,
                reviewStatus,
                desc,
                LocalDateTime.now(),
                product
*/
