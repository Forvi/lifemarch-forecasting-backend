package org.app.lifemarchforecastingbackend.repository;

import org.app.lifemarchforecastingbackend.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<CategoryEntity, Long> {
    List<CategoryEntity> findByNameStartingWith(String prefix);
    List<CategoryEntity> findByNameEndingWith(String suffix);
    List<CategoryEntity> findByNameContainingIgnoreCase(String snippet);
}
