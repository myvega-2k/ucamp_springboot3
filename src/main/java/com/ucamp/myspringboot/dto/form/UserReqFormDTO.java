package com.ucamp.myspringboot.dto.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserReqFormDTO {
    private Long id;

    @NotEmpty(message = "Name은 필수 입력항목입니다.")
    private String name;

    @NotBlank(message = "Email은 필수 입력항목입니다.")
    @Email(message = "Email 형식에 맞지 않습니다.")
    private String email;
}
