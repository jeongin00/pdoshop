package com.example.demo.repository.product;

import com.example.demo.repository.product.entity.Product;
import com.example.demo.repository.product.entity.ReviewStatus;
import com.example.demo.repository.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByUser(User user);

    Page<Product> findAllReviewStatus(ReviewStatus status, Pageable pageable);
}
