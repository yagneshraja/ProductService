package com.productservice.productservice.repositories;

import com.productservice.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category save(Category category);

    Optional<Category> findCategoryByName(String category);
}
