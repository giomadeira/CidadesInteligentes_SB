package br.com.fiap.smartgarbage.model;


import br.com.fiap.smartgarbage.utils.CollectStatusEnum;
import br.com.fiap.smartgarbage.utils.CollectTypeEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_COLLECT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CollectModel {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_COLLECT"
    )
    @SequenceGenerator(
            name = "SEQ_COLLECT",
            sequenceName = "SEQ_COLLECT",
            allocationSize = 1
    )
    @Column(name = "collect_id")
    private Long id;
    private String address;

    @Column(name = "scheduled_date_time")
    private LocalDateTime scheduledDateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "collect_type")
    private CollectTypeEnum collectType;

    @Enumerated(EnumType.STRING)
    @Column(name = "collect_status")
    private CollectStatusEnum collectStatus;
}
