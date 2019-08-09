package ru.spartars.review.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.spartars.review.dto.ReviewResponseDto;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class SearchRestController {

    @GetMapping("search")
    public List<ReviewResponseDto> searchByTitle(@RequestParam String searchText) {
        return Collections.emptyList();
    }
}
