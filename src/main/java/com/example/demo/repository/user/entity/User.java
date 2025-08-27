package com.example.demo.repository.user.entity;


import com.example.demo.repository.product.entity.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String loginid;
    private String password;
    private String name;
    private LocalDateTime createdAt;


    @OneToMany(fetch = FetchType.LAZY, mappedBy="user")
    private List<Product> products = new ArrayList<>();

    public static User create(String loginid, String password, String name){
        return new User(
                null,
                loginid,
                password,
                name,
                LocalDateTime.now(),
                new ArrayList<>()
        );
    }




}
