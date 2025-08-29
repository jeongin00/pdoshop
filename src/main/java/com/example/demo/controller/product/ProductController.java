package com.example.demo.controller.product;


import com.example.demo.controller.product.dto.ProductCreateRequestDto;
import com.example.demo.controller.product.dto.ProductSimpleResponseDto;
import com.example.demo.controller.product.dto.ProductWithHistoryResponseDto;
import com.example.demo.controller.product.dto.ReviewStatusUpdateRequestDto;
import com.example.demo.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProductController {
    ProductService productService;

    @PostMapping("")
    public ResponseEntity<ProductSimpleResponseDto> create(@RequestBody ProductCreateRequestDto request) {
        Integer exampleUserId = 1;
        ProductSimpleResponseDto product = productService.save(request, exampleUserId);
        return ResponseEntity.ok(product);
    }

    @GetMapping("")
    public ResponseEntity<List<ProductSimpleResponseDto>> getApprovedProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<ProductSimpleResponseDto> products = productService.findApprovedProducts(page, size);
        return ResponseEntity.ok(products);
    }

    @PatchMapping("/{id}/reviewStatus")
    public ResponseEntity<ProductWithHistoryResponseDto> updateReviewStatus(
            @PathVariable Integer id,
            @RequestBody ReviewStatusUpdateRequestDto request
    ) {
        ProductWithHistoryResponseDto updated = productService.updateReviewStatus(id, request);
        return ResponseEntity.ok(updated);

    }

}
