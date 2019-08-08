package ru.spartars.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spartars.review.dto.CommentResponseDto;
import ru.spartars.review.exception.CommentNotFoundException;
import ru.spartars.review.repository.CommentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    CommentRepository commentRepository;

    public List<CommentResponseDto> findByReviewId(long reviewId) {
        return commentRepository.findByReviewId(reviewId)
                .stream()
                .map(CommentResponseDto::from)
                .collect(Collectors.toList())
                ;
    }
}
