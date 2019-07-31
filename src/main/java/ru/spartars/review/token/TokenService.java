package ru.spartars.review.token;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// FIXME: не забывайте экстрактить интерфейс
@Service
@Transactional
@RequiredArgsConstructor
public class TokenService implements AuthenticationManager {
  private final TokenRepository repository;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    var token = (String) authentication.getPrincipal();

    if (token == null) {
      throw new TokenException("Token can't be null");
    }

    var tokenEntity = repository.findById(token).orElseThrow(() -> new TokenException("Token invalid"));
    var userEntity = tokenEntity.getUser();

    return new UsernamePasswordAuthenticationToken(
        userEntity,
        userEntity.getPassword(),
        userEntity.getAuthorities()
    );

  }
}
