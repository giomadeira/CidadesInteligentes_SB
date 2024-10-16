package br.com.fiap.smartgarbage.dto;

import br.com.fiap.smartgarbage.model.UserModel;
import br.com.fiap.smartgarbage.utils.UserRolesEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserInsertDTO(
        Long id,
        @NotBlank(message = "Nome do contato é obrigatório!")
        String name,
        @NotBlank(message = "O email é obrigatório!")
        @Email(message = "O email está no formato inválido!")
        String email,
        @NotBlank(message = "A senha é obrigatório!")
        @Size(min = 6, max = 10, message = "Senha deve ter entre 6 e 10 caracteres")
        String password,
        UserRolesEnum role
) {
    public UserInsertDTO(UserModel userModel) {
        this(
                userModel.getId(),
                userModel.getName(),
                userModel.getEmail(),
                userModel.getPassword(),
                userModel.getRole()
        );
    }
}
