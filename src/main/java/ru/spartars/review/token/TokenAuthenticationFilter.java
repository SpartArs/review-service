package ru.spartars.review.token;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {
  private final TokenService tokenService;
  private final TokenAuthenticationEntryPoint authenticationEntryPoint;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    var token = request.getHeader("X-Token");

    // не наш клиент
    if (token == null) {
      filterChain.doFilter(request, response);
      return;
    }

    try {
      // TODO: authenticate
      var authenticationRequest = new TokenAuthentication(token, null);
      var authenticationResult = tokenService.authenticate(authenticationRequest);

      SecurityContextHolder.getContext().setAuthentication(authenticationResult);
    } catch (AuthenticationException e) {
      // не удалось аутентифицироваться
      SecurityContextHolder.clearContext();
      authenticationEntryPoint.commence(request, response, e);
      return;
    }

    filterChain.doFilter(request, response);
  }
}
