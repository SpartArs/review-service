package ru.spartars.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spartars.review.dto.RegistrationRequestDto;
import ru.spartars.review.entity.UserEntity;
import ru.spartars.review.exception.UsernameAlreadyExistsException;
import ru.spartars.review.repository.UserRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RegistrationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(RegistrationRequestDto dto) {

        var userOptional = userRepository.findByUsername(dto.getUsername());
        if (userOptional.isEmpty()) {
            var user = new UserEntity(
                    0L,
                    dto.getName(),
                    dto.getUsername(),
                    passwordEncoder.encode(dto.getPassword()),
                    List.of(new SimpleGrantedAuthority("ROLE_USER")),
                    true,
                    true,
                    true,
                    false
            );
            userRepository.save(user);
        } else {
//            if (userOptional.get().isEnabled()) {
                throw new UsernameAlreadyExistsException(dto.getUsername());
//            }
        }
    }
}
