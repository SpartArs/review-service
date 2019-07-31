package ru.spartars.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.spartars.review.entity.CategoryEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDto {
    private long id;
    private String title;

    public static CategoryResponseDto from(CategoryEntity categoryEntity) {
        return new CategoryResponseDto(
                categoryEntity.getId(),
                categoryEntity.getTitle()
        );
    }
}
