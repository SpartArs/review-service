package ru.spartars.review.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import ru.spartars.review.token.TokenAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final TokenAuthenticationFilter tokenFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors()
                .and()
                .addFilterAfter(this.tokenFilter, BasicAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/authentication").anonymous()
                .antMatchers(HttpMethod.POST, "/api/registration", "/api/registration/confirm").anonymous()
//                .antMatchers(HttpMethod.POST, "/api/registration", "/api/registration/confirm").access("!isAuthenticated()")
                .antMatchers(HttpMethod.POST, "/api/recovery", "/api/recovery/confirm").anonymous()
//                .antMatchers(HttpMethod.POST, "/api/reviews/{id}").access("@permissionService.hasPermission(#id)")
                .antMatchers("/api/reviews/recent/condensed").permitAll()
                .antMatchers("/api/users/profile").authenticated()
                .antMatchers("/api/**").hasRole("USER")
                .anyRequest().authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
