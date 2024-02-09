package com.example.crm.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.crm.model.User;
import com.example.crm.manager.JwtManager;
import com.example.crm.service.UserService;

import java.io.IOException;

/**
 * La classe qui sera executer avant la requête
 */
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

        // request.getRequestURI() => URL
        // on va executer que les request qui commencent par /api
        if (!request.getRequestURI().startsWith("/api/")) {
            filterChain.doFilter(request, response);
            return;
        }

        // on va executer que les request qui commencent par /api/
        // on doit vérifier que le token existe
        String token = request.getHeader("Authorization");
        try {
            token = JwtManager.readJwt(token.substring(7));
        } catch (Exception e) {
            //throw new RuntimeException(e);
            return;
        }

        // récupérer l'utilisateur qui contient ce token
        User userConnect = null;
        try {
            userConnect = this.userService.findByToken(token);
            if (userConnect == null) {
                filterChain.doFilter(request, response);
                return;
            }
        } catch (Exception e) {
            return;
        }

        // token et valide => enregistrer l'utilisateur dans le systeme avec ces roles
        Authentication authentication = new UsernamePasswordAuthenticationToken(userConnect, null, userConnect.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
