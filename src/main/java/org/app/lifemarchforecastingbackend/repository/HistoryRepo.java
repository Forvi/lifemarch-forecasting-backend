package org.app.lifemarchforecastingbackend.repository;

import org.app.lifemarchforecastingbackend.entities.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepo extends JpaRepository<HistoryEntity, Long> {
    List<HistoryEntity> findByNameStartingWith(String prefix);
    List<HistoryEntity> findByNameEndingWith(String suffix);
    List<HistoryEntity> findByNameContainingIgnoreCase(String snippet);
    HistoryEntity findByName(String name);
    List<HistoryEntity> findAllByName(String name);
}
