package org.app.lifemarchforecastingbackend.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.app.lifemarchforecastingbackend.dto.categoryDtos.CategoryDto;
import org.app.lifemarchforecastingbackend.dto.categoryDtos.CategoryMapper;
import org.app.lifemarchforecastingbackend.dto.productDtos.ProductMapper;
import org.app.lifemarchforecastingbackend.entities.CategoryEntity;
import org.app.lifemarchforecastingbackend.entities.ProductEntity;
import org.app.lifemarchforecastingbackend.exceptions.OperationErrorException;
import org.app.lifemarchforecastingbackend.repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepo productRepo;
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public void createProduct(String name, Integer quantityBuy,
                              BigDecimal costPrice, String categoryName) {

        try {
            log.info("Creating new product: {}...", name);

            log.info("Validity check...");
            validateCreateInput(name, quantityBuy, costPrice, categoryName);
            log.info("The data is valid");

            ProductEntity product = new ProductEntity();
            product.setName(name);
            product.setQuantityBuy(quantityBuy);
            product.setCostPrice(costPrice);

            CategoryDto categoryDto = categoryService.createCategory(categoryName);
            CategoryEntity categoryEntity = categoryMapper.toEntity(categoryDto);

            product.setCategory(categoryEntity);

            log.info("Product {} created", name);

            productRepo.save(product);
        } catch (OperationErrorException e) {
            log.error("Failed to create product: {}", name, e);
            throw e;
        }
    }

    private void validateCreateInput(String name, Integer quantityBuy,
                                BigDecimal costPrice, String categoryName) {

        Objects.requireNonNull(name, "Name cannot be null");
        Objects.requireNonNull(categoryName, "Category name cannot be null");

        if (name.isBlank()) {
            throw new OperationErrorException("Name cannot be empty");
        }

        if (quantityBuy == null || quantityBuy < 0) {
            throw new OperationErrorException("Quantity must be positive");
        }

        if (costPrice == null || costPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new OperationErrorException("Cost price must be positive");
        }
    }

}
