package ru.spartars.review.repository;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.spartars.review.entity.ReviewEntity;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    List<ReviewEntity> findTop10ByOrderByCreatedDesc();

    @Query("SELECT e FROM ReviewEntity e WHERE e.author.id = :authorId")
        // в параметрах компиляции нужно выставить флаг -parameter
    List<ReviewEntity> findPageByAuthorId(@Param("authorId") long authorId, Pageable pageable);
}
