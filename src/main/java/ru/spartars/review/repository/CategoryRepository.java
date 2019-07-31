package ru.spartars.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spartars.review.entity.CategoryEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByTitle(String title);

}
