package ru.spartars.review.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spartars.review.dto.CommentRequestDto;
import ru.spartars.review.dto.CommentResponseDto;
import ru.spartars.review.entity.CommentEntity;
import ru.spartars.review.entity.ReviewEntity;
import ru.spartars.review.entity.UserEntity;
import ru.spartars.review.exception.ReviewNotFoundException;
import ru.spartars.review.repository.CommentRepository;
import ru.spartars.review.repository.ReviewRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    @Setter(onMethod_ = @Autowired)
    private CommentRepository commentRepository;

    @Setter(onMethod_ = @Autowired)
    private ReviewRepository reviewRepository;

    public CommentResponseDto save(CommentRequestDto dto, UserEntity user) {
        ReviewEntity reviewEntity = reviewRepository.findById(dto.getReviewId()).orElseThrow(ReviewNotFoundException::new);
        CommentEntity commentEntity = commentRepository.save(
                new CommentEntity(
                        0L,
                        dto.getCommentText(),
                        reviewEntity,
                        user,
                        LocalDateTime.now()
                )
        );
        return CommentResponseDto.from(commentEntity);
    }

    public List<CommentResponseDto> findByReviewId(long reviewId) {
        return commentRepository.findByReviewId(reviewId)
                .stream()
                .map(CommentResponseDto::from)
                .collect(Collectors.toList())
                ;
    }
}
