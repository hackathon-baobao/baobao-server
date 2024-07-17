package com.baobao.baobaoserver.security;

import com.baobao.baobaoserver.common.exception.GlobalExceptionCode;
import com.baobao.baobaoserver.security.security.ErrorResponseSender;
import com.baobao.baobaoserver.security.security.jwt.JwtExceptionFilter;
import com.baobao.baobaoserver.security.security.jwt.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
class SecurityConfig {
    private static final String MEMBER = "MEMBER";
    private static final String ADMIN = "ADMIN";

    private final JwtFilter tokenFilter;
    private final JwtExceptionFilter tokenExceptionFilter;
    private final ErrorResponseSender errorResponseSender;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(httpSecurityCorsConfigurer -> corsConfigurationSource())
                .addFilterAfter(tokenFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(tokenExceptionFilter, JwtFilter.class)
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers(POST, "/login").permitAll()
                                .anyRequest().authenticated()
                )
                .exceptionHandling(exceptionHandler ->
                        exceptionHandler
                                .accessDeniedHandler((req, res, e) -> errorResponseSender.send(res, GlobalExceptionCode.INVALID_ROLE))
                                .authenticationEntryPoint((req, res, e) -> errorResponseSender.send(res, GlobalExceptionCode.ENDPOINT_NOT_FOUND)));
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

}
