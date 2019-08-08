package ru.spartars.review.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.spartars.review.dto.CommentResponseDto;
import ru.spartars.review.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class CommentRestController {
    private final CommentService commentService;

    @GetMapping("/comments/{reviewId}")
    public List<CommentResponseDto> findRecentByReviewId(@PathVariable long reviewId) {
        return commentService.findByReviewId(reviewId);
    }
}
