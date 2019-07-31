package ru.spartars.review.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.spartars.review.dto.UserProfileResponseDto;
import ru.spartars.review.entity.UserEntity;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserRestController {

    @GetMapping("/profile")
    public UserProfileResponseDto userProfile(@AuthenticationPrincipal UserEntity entity) {
        return UserProfileResponseDto.from(entity);
    }
}
