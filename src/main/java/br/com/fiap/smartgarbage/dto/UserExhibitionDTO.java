package br.com.fiap.smartgarbage.dto;

import br.com.fiap.smartgarbage.model.UserModel;

public record UserExhibitionDTO(
        Long id,
        String name,
        String email
) {
    public UserExhibitionDTO(UserModel userModel) {
        this(
            userModel.getId(),
            userModel.getName(),
            userModel.getEmail()
        );
    }
}
