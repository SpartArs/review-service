package ru.spartars.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequestDto {
    private long id;
    private String content;
    private long category;
    private MultipartFile file;
//    private String fileName;
    private List<String> tags = new ArrayList<>();
}
