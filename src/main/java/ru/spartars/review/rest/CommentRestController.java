package ru.spartars.review.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.spartars.review.dto.CommentRequestDto;
import ru.spartars.review.dto.CommentResponseDto;
import ru.spartars.review.entity.UserEntity;
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

    @PostMapping("comments/add")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void save(@AuthenticationPrincipal UserEntity user, @ModelAttribute CommentRequestDto dto) {
        commentService.save(dto, user);
    }
}
