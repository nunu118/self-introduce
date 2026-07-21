package com.yeonwoo.self_introduce.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yeonwoo.self_introduce.exception.ErrorResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;

import java.io.IOException;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class SecurityConfig {
    private final ObjectMapper objectMapper;

    // Spring Security Filter Chain 설정
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                // REST API는 session 기반이 아니므로 비활성화
                .csrf(csrf -> csrf.disable())
                // session 생성, 사용 안함
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // HTTP Basic 인증 활성화 (관리자 페이지 보호)
                .httpBasic(Customizer.withDefaults())
                // CORS 정책 적용
                .cors(Customizer.withDefaults())
                // URL 접근 권한 설정
                .authorizeHttpRequests(auth -> auth
                        // Preflight 요청 허용
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        // 조회(GET)는 누구나 가능
                        .requestMatchers(HttpMethod.GET, "/**").permitAll()
                        // 관리자 페이지는 ADMIN 권한만 접근 가능
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        // 정적 리소스는 누구나 접근 가능
                        .requestMatchers(
                                "/",
                                "/css/**",
                                "/js/**",
                                "/images/**",
                                "/favicon.ico",
                                "/error"
                        ).permitAll()
                        // 나머지 인증 필요
                        .anyRequest().authenticated()
                )

                // 인증(Authentication), 인가(Authorization) 실패 처리
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(
                                (request, response, e) ->
                                        writeError(response, 401, "Unauthorized")
                        )
                        .accessDeniedHandler(
                                (request, response, e) ->
                                        writeError(response, 403, "Forbidden")
                        )
                )
                // HTTP Security Header 설정
                .headers(headers -> headers
                        // CSP(Content Security Policy) 적용
                        .contentSecurityPolicy(csp ->
                                csp.policyDirectives("default-src 'self';"))
                        // Clickjacking 방지
                        .frameOptions(frame ->
                                frame.sameOrigin())
                        // Referrer 정보 외부 노출 방지
                        .referrerPolicy(referrer ->
                                referrer.policy(
                                        ReferrerPolicyHeaderWriter.ReferrerPolicy.NO_REFERRER))
                        // Cache-Control Header 적용
                        .cacheControl(Customizer.withDefaults())
                );

        return http.build();
    }

    // Security 예외 응답(JSON) 생성
    private void writeError(
            HttpServletResponse response,
            int status,
            String message
    ) throws IOException {

        response.setStatus(status);
        response.setContentType("application/json;charset=UTF-8");

        objectMapper.writeValue(
                response.getWriter(),
                new ErrorResponse(status, message)
        );
    }

}