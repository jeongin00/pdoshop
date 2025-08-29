package com.example.demo.controller.product.dto;

import com.example.demo.repository.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class ProductSimpleResponseDto {
    private Integer productId;
    private String name;
    private String baseProductName;
    private LocalDateTime createdAt;
    private List<String> imageUrls; // 이미지 URL 리스트


    public static ProductSimpleResponseDto from(Product entity) {
        List<String> imageUrls = entity.getProductImages()
                .stream()
                .map(productImage -> productImage.getImage().getImageUrl())
                .toList();


        return new ProductSimpleResponseDto(
                entity.getId(),
                entity.getName(),
                entity.getBaseProduct().getName(),
                entity.getCreatedAt(),
                imageUrls
        );
    }
}
