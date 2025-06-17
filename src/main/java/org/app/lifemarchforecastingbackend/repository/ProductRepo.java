package org.app.lifemarchforecastingbackend.repository;

import org.app.lifemarchforecastingbackend.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface ProductRepo extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByNameStartingWith(String prefix);
    List<ProductEntity> findByNameContaining(String prefix);
    List<ProductEntity> findByNameContainingIgnoreCase(String prefix);

    @Query("SELECT p FROM ProductEntity p JOIN p.category c WHERE c.name IN :categoryNames")
    List<ProductEntity> findByCategories(@Param("categoryNames") List<String> categoryNames);
}
