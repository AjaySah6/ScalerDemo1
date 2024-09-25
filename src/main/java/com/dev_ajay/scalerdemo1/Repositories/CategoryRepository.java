package com.dev_ajay.scalerdemo1.Repositories;

import com.dev_ajay.scalerdemo1.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByTitle(String title);
    Category save(Category category);
    Optional<Category> findById(Long id);
}
