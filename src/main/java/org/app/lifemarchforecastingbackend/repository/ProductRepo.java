package org.app.lifemarchforecastingbackend.repository;

import org.app.lifemarchforecastingbackend.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByNameStartingWith(String prefix);
    List<ProductEntity> findByNameContaining(String prefix);
    List<ProductEntity> findByNameContainingIgnoreCase(String prefix);
}
