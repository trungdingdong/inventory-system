package com.example.inventory_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Tắt CSRF (Rất quan trọng khi test bằng Postman)
                .csrf(AbstractHttpConfigurer::disable)

                // 2. Cấu hình quyền truy cập
                .authorizeHttpRequests(auth -> auth
                        // Cho phép truy cập tự do vào 2 đường dẫn này
                        .requestMatchers("/api/user/register", "/api/user/login").permitAll()

                        // Các đường dẫn khác bắt buộc phải đăng nhập mới được vào
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}