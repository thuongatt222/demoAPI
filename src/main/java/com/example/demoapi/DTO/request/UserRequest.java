package com.example.demoapi.DTO.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest implements Serializable {

    @NotBlank(message = "dung bo qua")
    private String name;

    @NotBlank(message = "dung bo qua")
    private String email;

    @NotBlank(message = "dung bo qua")
    private String password;
}
