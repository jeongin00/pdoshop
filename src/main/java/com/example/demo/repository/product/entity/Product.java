package com.example.demo.repository.product.entity;

import com.example.demo.repository.image.entity.ProductImage;
import com.example.demo.repository.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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
    @Column(name = "review_status", nullable = false)
    private ReviewStatus reviewStatus;  // 리뷰상태

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by")
    private User user;

    private LocalDateTime createdAt; // 등록일
    private LocalDateTime updatedAt; // 업데이트 시간 추가 수정필요


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "base_product_id")
    private BaseProduct baseProduct;

    // 지금 image가 없어서 문제 발생
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.PERSIST) // mappedBy에는 클래스 필드명
    private List<ProductImage> productImages = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    private List<ProductReviewHistory> reviewHistories = new ArrayList<>();

    public void addReviewHistory(ProductReviewHistory history) {
        reviewHistories.add(history);
        history.setProduct(this);
    }

    public static Product create(String name, User user, BaseProduct baseProduct) {
        Product product = new Product(
                null,
                name,
                ReviewStatus.APPROVED,
                user,
                LocalDateTime.now(),
                LocalDateTime.now(),
                baseProduct,
                new ArrayList<>(),
                new ArrayList<>()  // ProductReviewHistory
        );

        ProductReviewHistory reviewHistory = ProductReviewHistory.create(
                "상품 등록",
                product
        );

        product.addReviewHistory(reviewHistory);

        return product;
    }


}
