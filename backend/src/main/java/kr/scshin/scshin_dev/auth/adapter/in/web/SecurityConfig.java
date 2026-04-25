package kr.scshin.scshin_dev.auth.adapter.in.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/backoffice/**").hasRole("ADMIN")
                        .anyRequest().permitAll()
                )

                .formLogin(form -> form
                        .loginPage("/backoffice/login")
                        .defaultSuccessUrl("/backoffice")
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutSuccessUrl("/backoffice/login")
                        .invalidateHttpSession(true)
                );

        return http.build();
    }
}