package ru.spartars.review;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.spartars.review.entity.CategoryEntity;
import ru.spartars.review.entity.CommentEntity;
import ru.spartars.review.entity.ReviewEntity;
import ru.spartars.review.entity.UserEntity;
import ru.spartars.review.repository.CategoryRepository;
import ru.spartars.review.repository.CommentRepository;
import ru.spartars.review.repository.ReviewRepository;
import ru.spartars.review.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class ReviewServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReviewServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner first(
            UserRepository userRepository,
            ReviewRepository reviewRepository,
            CategoryRepository categoryRepository,
            CommentRepository commentRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            var vasya = new UserEntity(
                    0L,
                    "Vasiliy",
                    "vasya@localhost",
                    passwordEncoder.encode("secret"),
                    List.of(new SimpleGrantedAuthority("ROLE_USER")),
                    true,
                    true,
                    true,
                    true
            );

            var petya = new UserEntity(
                    0L,
                    "Petr",
                    "petya@localhost",
                    passwordEncoder.encode("secret"),
                    List.of(new SimpleGrantedAuthority("ROLE_USER")),
                    true,
                    true,
                    true,
                    true
            );

            userRepository.save(vasya);
            userRepository.save(petya);

            var category = new CategoryEntity(
                    0L,
                    "Category"
            );

            categoryRepository.save(category);

            var vasyaReview = new ReviewEntity(
                    0L,
                    "Vasya review",
                    vasya,
                    category,
                    LocalDateTime.now(),
                    List.of("vasya", "tags")
            );

            reviewRepository.save(vasyaReview);


            reviewRepository.save(new ReviewEntity(
                    0L,
                    "Petya review",
                    petya,
                    category,
                    LocalDateTime.now(),
                    List.of("petya", "tags")
            ));

            commentRepository.save(new CommentEntity(
                    0L,
                    "CommentByPetya",
                    vasyaReview,
                    petya
            ));


//      emailService.sendSimpleMessage("...", "...", "Verification message", "Link must be here");
//      emailService.sendMimeMessage("...", "...", "Verification message", "<a href='http://localhost/verification'>Link must be here</a>");
        };
    }

}
