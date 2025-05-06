package org.app.lifemarchforecastingbackend.repository;

import org.app.lifemarchforecastingbackend.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<CategoryEntity, Long> {
}
