package com.example.crm.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilter securityFilter(){
        return new SecurityFilter();
    }
    
    @Bean
    public SecurityFilterChain apiSecurity(HttpSecurity http) throws Exception{
        return http.csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/api/**").hasAnyAuthority("ADMIN", "USER") // seulement pour les ADMIN
                        .anyRequest().permitAll() // l'ensemble des request sont autorisé
                    )
                    .addFilterAfter(securityFilter(), UsernamePasswordAuthenticationFilter.class)
                    .build();
    }
}
