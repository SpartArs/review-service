package ru.spartars.review.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
        // TODO: глянуть оптимизацию
        @NamedQuery(
                name = "isReviewAuthor", // желательно в Constants
                query = "SELECT COUNT(e) FROM ReviewEntity e WHERE e.id = :memoId AND e.author.id = :userId"
        )
})
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String content;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UserEntity author;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CategoryEntity category;
    private String file;
    private LocalDateTime created;
    @ElementCollection
    private List<String> tags = new ArrayList<>();
}
