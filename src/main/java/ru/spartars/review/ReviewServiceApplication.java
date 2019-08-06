package ru.spartars.review;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import ru.spartars.review.entity.*;
import ru.spartars.review.repository.*;

import javax.servlet.MultipartConfigElement;
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

            var category1 = new CategoryEntity(
                    0L,
                    "Category-item"
            );

            var category2 = new CategoryEntity(
                    0L,
                    "Category-it"
            );

            categoryRepository.save(category1);
            categoryRepository.save(category2);



            var vasyaReview = new ReviewEntity(
                    0L,
                    "Vasya review",
                    vasya,
                    category1,
                    "https://c.dns-shop.ru/thumb/st1/fit/600/374/f67c67b370c460ff797ec657bae263e3/78a7f0df1589613b4e49a58f5adfa16fefef7cb20d70901b39c13e44af58b8de.jpg",
                    LocalDateTime.now(),
                    List.of("vasya", "tags")
            );

            reviewRepository.save(vasyaReview);


            reviewRepository.save(new ReviewEntity(
                    0L,
                    "Petya review",
                    petya,
                    category2,
                    "https://i2.rozetka.ua/goods/10292348/68512101_images_10292348175.jpg",
                    LocalDateTime.now(),
                    List.of("petya", "tags")
            ));

            commentRepository.save(new CommentEntity(
                    0L,
                    "CommentByPetya",
                    vasyaReview,
                    petya
            ));

        };
    }

}
