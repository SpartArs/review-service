package ru.spartars.review.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.spartars.review.dto.AuthenticationTokenRequestDto;
import ru.spartars.review.dto.AuthenticationTokenResponseDto;
import ru.spartars.review.service.AuthenticationService;

@RestController
@RequestMapping("/api/authentication")
@RequiredArgsConstructor
public class AuthenticationRestController {
    private final AuthenticationService service;

    @PostMapping
    public AuthenticationTokenResponseDto authenticate(@RequestBody AuthenticationTokenRequestDto dto) {
        return service.authenticate(dto);
    }
}
