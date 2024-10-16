package br.com.fiap.smartgarbage.dto;

import br.com.fiap.smartgarbage.model.CollectModel;
import br.com.fiap.smartgarbage.utils.CollectStatusEnum;
import br.com.fiap.smartgarbage.utils.CollectTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CollectScheduleDTO(
    Long id,
    @NotBlank(message = "O endereço deve ser preenchido!")
    String address,
    @NotNull(message = "A data de agendamento deve ser preenchida!")
    LocalDateTime scheduledDateTime,
    CollectTypeEnum collectType,
    CollectStatusEnum collectStatus
) {
    public CollectScheduleDTO(CollectModel collectModel) {
        this(
            collectModel.getId(),
            collectModel.getAddress(),
            collectModel.getScheduledDateTime(),
            collectModel.getCollectType(),
            collectModel.getCollectStatus()
        );
    }
}