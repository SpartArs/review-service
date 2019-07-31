package ru.spartars.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequestDto {
    private long id;
    private String content;
    private long categoryId;
    private List<String> tags = new ArrayList<>();
}
