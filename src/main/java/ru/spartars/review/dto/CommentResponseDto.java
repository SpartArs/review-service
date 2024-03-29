package ru.spartars.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.spartars.review.entity.CommentEntity;
import ru.spartars.review.utils.DateUtil;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {
    private long id;
    private String text;
    private UserResponseDto author;
    private String date;

    public static CommentResponseDto from(CommentEntity commentEntity) {
        return new CommentResponseDto(
                commentEntity.getId(),
                commentEntity.getText(),
                UserResponseDto.from(commentEntity.getAuthor()),
                DateUtil.formatDate(commentEntity.getCreated())
        );
    }

}
