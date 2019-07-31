package ru.spartars.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spartars.review.dto.CategoryRequestDto;
import ru.spartars.review.dto.CategoryResponseDto;
import ru.spartars.review.entity.CategoryEntity;
import ru.spartars.review.exception.CategoryNotFoundException;
import ru.spartars.review.repository.CategoryRepository;


@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public void save(CategoryRequestDto dto) {
        if (dto.getId() != 0) {
            categoryRepository.findById(dto.getId())
                    .stream()
                    .peek(o -> {
                        // TODO: Обновится ли entity?
                        o.setTitle(dto.getTitle());
                    })
                    .findFirst()
                    .orElseThrow(CategoryNotFoundException::new);
            return;
        }

        categoryRepository.save(new CategoryEntity(
                0L,
                dto.getTitle()
        ));
    }

    public CategoryEntity findCategoryById(long id) {
        return categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);
    }

    public CategoryResponseDto findCategoryResponseDtoById(long id) {
        return categoryRepository.findById(id).map(CategoryResponseDto::from)
                .orElseThrow(CategoryNotFoundException::new);
    }

    public CategoryEntity findCategoryByTitle(String title) {
        return categoryRepository.findByTitle(title)
                .orElseThrow(CategoryNotFoundException::new);
    }


    public void removeById(long id) {
        categoryRepository.deleteById(id);
    }
}
