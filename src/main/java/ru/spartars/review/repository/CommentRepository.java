package ru.spartars.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.spartars.review.entity.CommentEntity;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    @Query("SELECT e FROM CommentEntity e WHERE e.review.id = :reviewId")
    List<CommentEntity> findByReviewId(@Param("reviewId") long reviewId);

    @Query("SELECT e FROM CommentEntity e WHERE e.author.id = :authorId")
    List<CommentEntity> findByAuthorId(@Param("authorId") long authorId);
}
