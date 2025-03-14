package com.org.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserLoginDTO(
        @Email(message = "Preencha um email.")
        @NotBlank(message = "O Campo email não pode estar vazio")
        String email,

        @NotBlank(message = "Preencha sua senha")
        @Size(min = 8, max = 30, message = "Deve possuir entre 8 e 30 caracteres")
        String password
) {
}
