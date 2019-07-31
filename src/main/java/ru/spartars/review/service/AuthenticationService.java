package ru.spartars.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spartars.review.dto.AuthenticationTokenRequestDto;
import ru.spartars.review.dto.AuthenticationTokenResponseDto;
import ru.spartars.review.repository.UserRepository;
import ru.spartars.review.token.TokenEntity;
import ru.spartars.review.token.TokenRepository;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationTokenResponseDto authenticate(AuthenticationTokenRequestDto dto) {
        var userEntity = userRepository.findByUsername(dto.getUsername()).orElseThrow(() -> new UsernameNotFoundException(dto.getUsername()));

        if (!passwordEncoder.matches(dto.getPassword(), userEntity.getPassword())) {
            throw new BadCredentialsException(dto.getUsername());
        }

        var token = UUID.randomUUID().toString();
        var tokenEntity = new TokenEntity(token, userEntity);
        tokenRepository.save(tokenEntity);

        return new AuthenticationTokenResponseDto(token);
    }
}
