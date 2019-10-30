package ru.spartars.review;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
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

            var category1 = new CategoryEntity(
                    0L,
                    "Компьютеры"
            );

            var category2 = new CategoryEntity(
                    0L,
                    "Бытовая техника"
            );

            var category3 = new CategoryEntity(
                    0L,
                    "Автомобили"
            );

            categoryRepository.save(category1);
            categoryRepository.save(category2);
            categoryRepository.save(category3);




        };
    }

    @Bean
    public LocalValidatorFactoryBean validatorFactoryBean(MessageSource messageSource) {
        var bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource);
        return bean;
    }

}
