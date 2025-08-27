package com.example.demo.repository.product.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductReviewHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String reviewStatus;
    private String desc;
    private LocalDateTime createdAt; // 생성일시

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    public static ProductReviewHistory create( String reviewStatus, String desc, Product product ){
        return new ProductReviewHistory(
                null,
                reviewStatus,
                desc,
                LocalDateTime.now(),
                product
        );
    }
}
