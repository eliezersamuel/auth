package com.org.auth.dto;

import com.org.auth.entity.UserEntity;
import jakarta.validation.constraints.*;

public record UserDTO(
        @NotBlank(message = "O Campo name não pode estar vazio")
        String name,

        @Email(message = "Preencha um email.")
        @NotBlank(message = "O Campo email não pode estar vazio")
        String email,

        @NotBlank(message = "Preencha sua senha")
        @Size(min = 8, max = 30, message = "Deve possuir entre 8 e 30 caracteres")
        String password
)
{
    public UserEntity toUserEntity(String pass) {
        return new UserEntity(
                name,
                email,
                pass
        );
    }
}
