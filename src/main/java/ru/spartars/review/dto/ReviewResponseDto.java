package ru.spartars.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.spartars.review.entity.ReviewEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDto {
    private long id;
    private String content;
    private UserResponseDto author;
    private CategoryResponseDto category;
//    private int likes;
    private LocalDateTime created;
    private List<String> tags = new ArrayList<>();

    public static ReviewResponseDto from(ReviewEntity entity) {
        return new ReviewResponseDto(
                entity.getId(),
                entity.getContent(),
                UserResponseDto.from(entity.getAuthor()),
//                entity.getLikes(),
                CategoryResponseDto.from(entity.getCategory()),
                entity.getCreated(),
                entity.getTags()
        );
    }
}
