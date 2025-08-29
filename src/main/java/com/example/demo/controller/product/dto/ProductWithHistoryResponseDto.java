package com.example.demo.controller.product.dto;


import com.example.demo.repository.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class ProductWithHistoryResponseDto {
    private Integer productId;
    private String name;
    private String baseProductName;
    private LocalDateTime createdAt;
    private List<String> imageUrls; // 이미지 URL 리스트
    private List<ProductHistoryResponseDto> reviewHistories;// 여기가 일단 그게 와야해 무엇이? 바로 히스토리 list가 와야겠지


    public static ProductWithHistoryResponseDto from(Product entity) {
        List<String> imageUrls = entity.getProductImages()
                .stream()
                .map(productImage -> productImage.getImage().getImageUrl())
                .toList();

        List<ProductHistoryResponseDto> reviewHistories = entity.getReviewHistories()
                .stream()
                .map(ProductHistoryResponseDto::from)
                .toList();
        return new ProductWithHistoryResponseDto(
                entity.getId(),
                entity.getName(),
                entity.getBaseProduct().getName(),
                entity.getCreatedAt(),
                imageUrls,
                reviewHistories
        );
    }
}
