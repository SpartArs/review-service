package ru.spartars.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import ru.spartars.review.entity.UserEntity;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponseDto {
    private long id;
    private String name;
    private String username;
    Collection<GrantedAuthority> authorities;

    public static UserProfileResponseDto from(UserEntity entity) {
        return new UserProfileResponseDto(
                entity.getId(),
                entity.getName(),
                entity.getUsername(),
                entity.getAuthorities()
        );
    }
}