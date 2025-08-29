package com.example.demo.repository.product.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductReviewHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    // Enum 타입 컬럼 default approved
    @Enumerated(EnumType.STRING)
    @Column(name = "review_status", nullable = false)
    private ReviewStatus reviewStatus;  // 리뷰
    private String desc;
    private LocalDateTime createdAt; // 생성일시

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    public static ProductReviewHistory create(String desc, Product product) {
        return new ProductReviewHistory(
                null,
                ReviewStatus.APPROVED,
                desc,
                LocalDateTime.now(),
                product
        );
    }
}
