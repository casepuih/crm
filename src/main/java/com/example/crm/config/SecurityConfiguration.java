package com.example.crm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private AuthenticationProvider authProvider;
    
    @Bean 
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests((auth) -> auth
                .requestMatchers("/register", "/login").permitAll()
                .requestMatchers("/static/**").permitAll()
                .anyRequest().authenticated() 
            )
            // .formLogin(Customizer.withDefaults())
            .formLogin(form -> form 
                .loginPage("/login")
                .defaultSuccessUrl("/dashboard")
            )
            .logout(form -> form 
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true) // Invalidate HTTP session
                .deleteCookies("JSESSIONID") // Delete cookies
                .permitAll()
            )
            .authenticationProvider(authProvider);
        return http.build();
    }
}
