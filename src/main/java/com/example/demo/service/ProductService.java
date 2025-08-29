package com.example.demo.service;

import com.example.demo.controller.product.dto.ProductCreateRequestDto;
import com.example.demo.controller.product.dto.ProductSimpleResponseDto;
import com.example.demo.controller.product.dto.ProductWithHistoryResponseDto;
import com.example.demo.controller.product.dto.ReviewStatusUpdateRequestDto;
import com.example.demo.repository.image.ImageRepository;
import com.example.demo.repository.image.ProductImageRepository;
import com.example.demo.repository.image.entity.Image;
import com.example.demo.repository.image.entity.ProductImage;
import com.example.demo.repository.product.BaseProductRepository;
import com.example.demo.repository.product.ProductRepository;
import com.example.demo.repository.product.ProductReviewHistoryRepository;
import com.example.demo.repository.product.entity.BaseProduct;
import com.example.demo.repository.product.entity.Product;
import com.example.demo.repository.product.entity.ProductReviewHistory;
import com.example.demo.repository.product.entity.ReviewStatus;
import com.example.demo.repository.user.UserRepository;
import com.example.demo.repository.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final BaseProductRepository baseProductRepository;
    private final ImageRepository imageRepository;
    private final ProductImageRepository productImageRepository;
    private final UserRepository userRepository;
    private final ProductReviewHistoryRepository productReviewHistoryRepository;

    @Transactional
    public ProductSimpleResponseDto save(ProductCreateRequestDto request, Integer userId) {
        // User 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("데이터베이스내 유저정보없음"));

        // baseproduct

        BaseProduct baseProduct = baseProductRepository.findById(request.getBaseProductId())
                .orElseThrow(() -> new RuntimeException("데이터베이스네 베이스프로덕트 없음"));


        Product product = Product.create(
                request.getName(),
                user,
                baseProduct
        );

        Product savedProduct = productRepository.save(product);

        // 이미지 객체 생성

        List<Image> images = request.getImageUrls()
                .stream()
                .map(Image::create)
                .toList();

        List<Image> savedImages = imageRepository.saveAll(images);

        // productImage 중간테이블

        List<ProductImage> productImages = savedImages
                .stream()
                .map(image -> ProductImage.create(
                        product,
                        image
                ))
                .toList();

        List<ProductImage> savedProductImages = productImageRepository.saveAll(productImages);

        // product set

        savedProduct.setProductImages(savedProductImages);

        return ProductSimpleResponseDto.from(savedProduct);
    }

    @Transactional
    public List<ProductWithHistoryResponseDto> findProductsByUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("해당 유저가 데이터베이스 내 존재하지 않습니다."));

        List<Product> products = productRepository.findAllByUser(user);
        return products.stream()
                .map(ProductWithHistoryResponseDto::from) // product->ProductWithHistoryResponseDto.from(product)
                .toList();
    }

    @Transactional
    public List<ProductSimpleResponseDto> findApprovedProducts(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Product> approvedProducts = productRepository.findAllReviewStatus(ReviewStatus.APPROVED, pageable);

        return approvedProducts.getContent()
                .stream()
                .map(ProductSimpleResponseDto::from)
                .toList();
    }


    @Transactional
    public ProductWithHistoryResponseDto updateReviewStatus(Integer id, ReviewStatusUpdateRequestDto request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("데이터베이스에 상품이 존재하지 않습니다."));

        product.setReviewStatus(request.getReviewStatus());

        ProductReviewHistory history = ProductReviewHistory.create(
                request.getDesc(),
                product
        );


        history.setReviewStatus(request.getReviewStatus());

        ProductReviewHistory savedHistory = productReviewHistoryRepository.save(history);

        product.addReviewHistory(savedHistory);

        return ProductWithHistoryResponseDto.from(product);
    }
}
