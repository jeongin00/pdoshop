package com.example.demo.repository.option.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OptionValues {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "option_group_id")
    private OptionGroup optionGroup;

    public static OptionValues create(String name, OptionGroup optionGroup){
        return new OptionValues(
                null,
                name,
                optionGroup
        );
    }


}
