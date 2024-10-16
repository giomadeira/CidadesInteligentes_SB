package br.com.fiap.smartgarbage.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDTO(
        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "O e-mail não é válido")
        String email,

        @NotBlank(message = "A senha é obrigatório")
        @Size(min = 6, max = 10, message = "Senha deve ter entre 6 e 10 caracteres")
        String password
) {
}
