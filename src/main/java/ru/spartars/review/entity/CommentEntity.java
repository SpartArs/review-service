package ru.spartars.review.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String text;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ReviewEntity review;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UserEntity author;
    private LocalDateTime created;

}
