package com.example.demo.controller.user;


import com.example.demo.controller.product.dto.ProductWithHistoryResponseDto;
import com.example.demo.controller.user.dto.UserCreateRequestDto;
import com.example.demo.controller.user.dto.UserResponseDto;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserController {
    UserService userService; // FieldDefaults 로 인해 private final 불필요
    ProductService productService;

    @PostMapping("")
    public ResponseEntity<UserResponseDto> create(@RequestBody @Valid UserCreateRequestDto request) {
        UserResponseDto user = userService.save(request);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/myproduct")
    public ResponseEntity<List<ProductWithHistoryResponseDto>> getMyProducts() {
        Integer userId = 1;
        List<ProductWithHistoryResponseDto> products = productService.findProductsByUser(userId);
        return ResponseEntity.ok(products);
    }
}
