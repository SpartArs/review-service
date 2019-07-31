package ru.spartars.review.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spartars.review.repository.UserRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorService {
    private final UserRepository repository;

    @Setter(onMethod_ = @Autowired)
    private ReviewService reviewService;

    public boolean isAuthorExistsById(long id) {
        return repository.existsById(id);
    }
}
