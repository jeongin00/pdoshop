package com.example.demo.repository.option.entity;

import com.example.demo.repository.product.entity.BaseProduct;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BaseProductOptionGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "base_product_id")
    private BaseProduct baseProduct;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "option_group_id")
    private OptionGroup optionGroup;

    public static BaseProductOptionGroup create(BaseProduct baseProduct, OptionGroup optionGroup){
        return new BaseProductOptionGroup(
                null,
                baseProduct,
                optionGroup
        );
    }
}
