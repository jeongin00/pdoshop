package com.example.demo.controller.product.dto;

import com.example.demo.repository.product.entity.ReviewStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewStatusUpdateRequestDto {
    private ReviewStatus reviewStatus; // 변경 상태
    private String desc; // 변경 사유
}
