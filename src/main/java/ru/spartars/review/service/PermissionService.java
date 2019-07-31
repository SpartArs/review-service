package ru.spartars.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@Transactional
@RequiredArgsConstructor
public class PermissionService {
    private final EntityManager em;

    public boolean isReviewAuthor(long reviewId, long userId) {
        var result = em.createNamedQuery("isReviewAuthor", Long.class)
                .setParameter("reviewId", reviewId)
                .setParameter("userId", userId)
                .getSingleResult();

        return result == 1;
    }
}
