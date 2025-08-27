package com.example.demo.repository.option;

import com.example.demo.repository.option.entity.OptionValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionValuesRepository extends JpaRepository<OptionValues, Integer> {
}
