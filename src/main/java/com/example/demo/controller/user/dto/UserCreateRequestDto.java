package com.example.demo.controller.user.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserCreateRequestDto {

    @NotNull(message = "아이디를 입력해 주세요")
    @Size(min = 5, max = 10, message = "아이디는 최소 5자, 최대 10자 입니다.")
    private String loginId;

    @NotNull(message = "비밀번호를 입력해 주세요")
    @Size(min = 8, message = " 비밀번호는 최소 8자 입니다.")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z]).*$",
            message = "비밀번호는 대문자와 소문자를 각각 1개 이상 포함해야 합니다."
    )

    // (?=.*[a-z])  소문자 1개 이상 포함
    // (?=.*[A-Z])  대문자 1개 이상 포함

    private String password;
    @NotNull(message = "이름을 입력해 주세요")
    private String name;
}
