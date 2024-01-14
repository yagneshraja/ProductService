package com.productservice.productservice.repositories;

import com.productservice.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public interface ProductRepository extends JpaRepository<Product,Long> {
    Product save(Product product);
    Optional<Product> findById(Long id);
    List<Product> findAll();

    @Override
    void delete(Product entity);
}
