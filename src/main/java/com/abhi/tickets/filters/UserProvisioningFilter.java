package com.abhi.tickets.filters;


import com.abhi.tickets.domain.User;
import com.abhi.tickets.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
// @RequiredArgsConstructor creates a constructor ONLY for required fields.
public class UserProvisioningFilter extends OncePerRequestFilter {
    private UserRepository userRepository;

    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null
                && authentication.isAuthenticated()
                && authentication.getPrincipal() instanceof Jwt jwt) {
            UUID keyCloackId = UUID.fromString(jwt.getSubject());
            if(!userRepository.existsById(keyCloackId)){
                User user = new User();
                user.setId(keyCloackId);
                user.setName(jwt.getClaimAsString("name"));
                user.setEmail(jwt.getClaimAsString("email"));

                userRepository.save(user);
            }

        }

        filterChain.doFilter(request, response);

    }
}
