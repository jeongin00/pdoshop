package com.example.demo.repository.option;

import com.example.demo.repository.option.entity.BaseProductOptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseProductOptionGroupRepository extends JpaRepository<BaseProductOptionGroup, Integer> {
}
