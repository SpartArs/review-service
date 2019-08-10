package ru.spartars.review.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.spartars.review.dto.RegistrationRequestDto;
import ru.spartars.review.service.RegistrationService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/registration")
@RequiredArgsConstructor
public class RegistrationRestController {
    private final RegistrationService registrationService;

    @PostMapping
    public void registration(@RequestBody RegistrationRequestDto registrationRequestDto) {
        registrationService.register(registrationRequestDto);
    }
}
