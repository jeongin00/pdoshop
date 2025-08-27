package com.example.demo.repository.product.entity;

import com.example.demo.repository.user.entity.User;
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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String name;   // 상품명


    // Enum 타입 컬럼 default approved
    @Enumerated(EnumType.STRING)
    @Column(name = "review_status",nullable = false)
    private ReviewStatus reviewStatus;  // 리뷰상태

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by")
    private User user;

    private LocalDateTime createdAt; // 등록일
    private LocalDateTime updatedAt; // 업데이트 시간


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "base_product_id")
    private BaseProduct baseProduct;


    public static Product create(String name ,User user, BaseProduct baseProduct){
        return new Product(
                null,
                name,
                ReviewStatus.APPROVED,
                user,
                LocalDateTime.now(),
                LocalDateTime.now(),
                baseProduct
        );
    }











}
