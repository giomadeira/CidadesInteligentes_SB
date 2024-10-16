package br.com.fiap.smartgarbage.dto;

import br.com.fiap.smartgarbage.model.CollectModel;
import br.com.fiap.smartgarbage.utils.CollectStatusEnum;
import br.com.fiap.smartgarbage.utils.CollectTypeEnum;

import java.time.LocalDateTime;

public record CollectExhibitionDTO(
    Long id,
    String address,
    LocalDateTime scheduledDateTime,
    CollectTypeEnum collectType,
    CollectStatusEnum collectStatus
) {
    public CollectExhibitionDTO(CollectModel collectModel) {
        this(
            collectModel.getId(),
            collectModel.getAddress(),
            collectModel.getScheduledDateTime(),
            collectModel.getCollectType(),
            collectModel.getCollectStatus()
        );
    }


}
