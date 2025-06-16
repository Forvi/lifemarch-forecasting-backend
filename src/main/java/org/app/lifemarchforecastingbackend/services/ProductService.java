package org.app.lifemarchforecastingbackend.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.app.lifemarchforecastingbackend.dto.categoryDtos.CategoryDto;
import org.app.lifemarchforecastingbackend.dto.categoryDtos.CategoryMapper;
import org.app.lifemarchforecastingbackend.dto.productDtos.ProductDto;
import org.app.lifemarchforecastingbackend.dto.productDtos.ProductMapper;
import org.app.lifemarchforecastingbackend.entities.CategoryEntity;
import org.app.lifemarchforecastingbackend.entities.ProductEntity;
import org.app.lifemarchforecastingbackend.exceptions.NotFoundException;
import org.app.lifemarchforecastingbackend.exceptions.OperationErrorException;
import org.app.lifemarchforecastingbackend.repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    @PersistenceContext
    private final EntityManager entityManager;

    private final ProductRepo productRepo;
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    private final ProductMapper productMapper;

    // Получить все товары
    public List<ProductDto> getAllProducts() {
        try {
            log.debug("Getting all products...");

            List<ProductDto> products = productRepo
                    .findAll()
                    .stream()
                    .map(productMapper::toDto)
                    .toList();

            log.info("{} products found", products.size());
            return products;
        } catch (OperationErrorException e) {
            log.error("Failed to get products");
            throw new OperationErrorException("Error: " + e.getMessage());
        }
    }

    // Получить товар по подстроке
    public List<ProductDto> getProductByNameSubstring(String name) {
        try {
            log.debug("Getting products with name: {}...", name);

            List<ProductDto> product = productRepo.findByNameContainingIgnoreCase(name)
                    .stream()
                    .map(productMapper::toDto)
                    .toList();

            log.info("Found {} products by {}", product.size(), name);

            return product;
        } catch (OperationErrorException e) {
            log.error("Failed to get product with name: {}", name, e);
            throw new OperationErrorException("Error: " + e.getMessage());
        }
    }

    // Создать товар
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

    // Валидация входных данных на метод создания товара
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

    public void deleteProductById(Long id) {
        try {
            log.debug("Deleting product with id: {}...", id);

            ProductEntity product = productRepo
                    .findById(id)
                    .orElseThrow(() -> new NotFoundException("Product not found"));

            productRepo.delete(product);
            log.info("Deleted product: {}", product.getName());
        } catch (OperationErrorException e) {
            log.error("Failed to delete product with id {}", id, e);
            throw e;
        }
    }

    public ProductDto getProductById(Long id) {
        try {
            log.debug("Getting product with id: {}...", id);

            ProductEntity product = productRepo
                    .findById(id)
                    .orElseThrow(() -> new NotFoundException("Product not found"));

            log.info("Product {} found", product.getName());

            return productMapper.toDto(product);
        } catch (OperationErrorException e) {
            log.error("Failed to get product with id: {}", id, e);
            throw e;
        }
    }

    public List<ProductDto> getProductsByCategory(List<String> categories) {
        try {
            log.debug("Getting products...");
            log.info("Validate list");
            if (categories.isEmpty()) {
                throw new NullPointerException("Categories cannot be empty or null");
            }

            log.info("List is valid");
            return productRepo
                    .findByCategories(categories)
                    .stream()
                    .map(productMapper::toDto)
                    .toList();
        } catch (OperationErrorException e) {
            log.error("Failed to get products", e);
            throw e;
        }
    }

    // Удаляет все товары из таблицы
    public void deleteAllProducts() {
        log.debug("Starting to delete all products...");

        try {
            productRepo.deleteAllInBatch();
            entityManager.clear();
            log.info("All products deleted");
        } catch (OperationErrorException e) {
            log.error("Failed to delete all products", e);
            throw e;
        }
    }
}
