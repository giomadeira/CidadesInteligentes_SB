package br.com.fiap.smartgarbage.repository;


import br.com.fiap.smartgarbage.model.CollectModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CollectRepository extends JpaRepository<CollectModel, Long> {
    public Optional<CollectModel> findByAddress(String address);

    public Optional<List<CollectModel>> findByScheduledDateTimeBetween(LocalDateTime start, LocalDateTime end);

    public Optional<List<CollectModel>> findByCollectType(String collectType);

    public Optional<List<CollectModel>> findByCollectStatus(String collectStatus);
}
