package ru.spartars.review.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.spartars.review.dto.CategoryResponseDto;
import ru.spartars.review.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class CategoryRestController {
    private final CategoryService categoryService;

    @GetMapping("/categories")
    public List<CategoryResponseDto> findAll() {
        return categoryService.findAllCategories();
    }
}
