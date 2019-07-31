package ru.spartars.review.token;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AbstractAuthenticationToken;

@Getter
@Setter
public class TokenAuthentication extends AbstractAuthenticationToken {
  private final Object principal;
  private final Object credentials;

  public TokenAuthentication(Object principal, Object credentials) {
    super(null);
    this.principal = principal;
    this.credentials = credentials;
  }
}
