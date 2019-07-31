package ru.spartars.review.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.spartars.review.dto.ReviewRequestDto;
import ru.spartars.review.dto.ReviewResponseDto;
import ru.spartars.review.entity.UserEntity;
import ru.spartars.review.repository.ReviewRepository;
import ru.spartars.review.service.ReviewService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewRestController {
    private final ReviewService reviewService;

    @GetMapping("/recent")
    public List<ReviewResponseDto> findRecent() {
        return reviewService.findRecent();
    }

    @GetMapping("/{id}")
    public ReviewResponseDto findById(@PathVariable long id) {
        return reviewService.findById(id);
    }

    @GetMapping("/authors/{authorId}/recent")
    public List<ReviewResponseDto> findRecentByAuthorId(@PathVariable long authorId) {
        return reviewService.findRecentByAuthorId(authorId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204 -> DELETE/POST
    // INSERT - проблем нет
    // UPDATE - проблемы есть:
    // @AuthenticationPrincipal -> principal -> UserEntity
    // SecurityContextHolder.getContext().getPrincipal()
    // Request, Session, ...
    // Domain Objects:
    // 1. Service Level
    // 2. AccessDecisionVoter <->
    // 3. ACL
    @PreAuthorize("hasRole('ADMIN') || #dto.id == 0 || @permissionService.isMemoAuthor(#dto.id, #user.id)")
    public void save(@RequestBody ReviewRequestDto dto, @AuthenticationPrincipal UserEntity user) {
        reviewService.save(dto, user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204 -> DELETE/POST
    @PreAuthorize("hasRole('ADMIN') || @permissionService.isMemoAuthor(#id, #user.id)")
    public void removeById(@PathVariable long id, @AuthenticationPrincipal UserEntity user) {
        reviewService.removeById(id);
    }




}
