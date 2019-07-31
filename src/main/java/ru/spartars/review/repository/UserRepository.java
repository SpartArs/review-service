package ru.spartars.review.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import ru.spartars.review.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    // derived queries
    boolean existsById(long id);
    boolean existsByUsername(String username);
}
