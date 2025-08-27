package com.example.demo.repository.product;

import com.example.demo.repository.product.entity.ProductReviewHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductReviewHistoryRepository extends JpaRepository<ProductReviewHistory, Integer> {
}
