package ru.spartars.review.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spartars.review.dto.ReviewRequestDto;
import ru.spartars.review.dto.ReviewResponseDto;
import ru.spartars.review.entity.CategoryEntity;
import ru.spartars.review.entity.ReviewEntity;
import ru.spartars.review.entity.UserEntity;
import ru.spartars.review.exception.AuthorNotFoundException;
import ru.spartars.review.exception.ReviewNotFoundException;
import ru.spartars.review.repository.ReviewRepository;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    @Setter(onMethod_ = @Autowired)
    private AuthorService authorService;

    @Setter(onMethod_ = @Autowired)
    private CategoryService categoryService;

    @Value("${upload.path}")
    private String uploadPath;

    public List<ReviewResponseDto> findRecent() {
        return reviewRepository.findTop10ByOrderByCreatedDesc()
                .stream()
                .map(ReviewResponseDto::from)
                .collect(Collectors.toList())
                ;
    }


    public ReviewResponseDto findById(long id) {
        return reviewRepository.findById(id)
                .map(ReviewResponseDto::from)
                .orElseThrow(ReviewNotFoundException::new)
                ;
    }

    public void save(ReviewRequestDto dto, UserEntity user) throws IOException {
        String resultFilename = null;

        if (dto.getFile() != null && !dto.getFile().getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            resultFilename = uuidFile + "." + dto.getFile().getOriginalFilename();

            dto.getFile().transferTo(new File(uploadPath + "/" + resultFilename));
            resultFilename = uploadPath + "/" + resultFilename;
        }

        if (dto.getId() != 0) {
            reviewRepository.findById(dto.getId())
                    .stream()
                    .peek(o -> {
                        o.setContent(dto.getContent());
                        o.setTags(dto.getTags());
                    })
                    .findFirst()
                    .orElseThrow(ReviewNotFoundException::new);
            return;
        }


        CategoryEntity category = categoryService.findCategoryById(dto.getCategory());
        reviewRepository.save(new ReviewEntity(
                0L,
                dto.getContent(),
                user,
                category,
                resultFilename,
                LocalDateTime.now(),
                dto.getTags()
        ));
    }

    public void removeById(long id) {
        reviewRepository.deleteById(id);
    }

    public List<ReviewResponseDto> findRecentByAuthorId(long authorId) {
        // проблемы:
        // authorId - 404
        // 1. 404
        // 2. []
        // em.query().set...Result(0).setMaxResult(count)
        if (!authorService.isAuthorExistsById(authorId)) {
            throw new AuthorNotFoundException();
        }

        return reviewRepository
                .findPageByAuthorId(authorId, PageRequest.of(0, 50, Sort.by(Sort.Direction.DESC, "created")))
                .stream()
                .map(ReviewResponseDto::from)
                .collect(Collectors.toList())
                ;
    }
}
