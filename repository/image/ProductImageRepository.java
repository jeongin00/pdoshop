package com.example.demo.repository.image;

import com.example.demo.repository.image.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepository extends JpaRepository<Image,Integer> {
}
