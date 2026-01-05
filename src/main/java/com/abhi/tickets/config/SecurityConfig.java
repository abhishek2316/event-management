package com.abhi.tickets.config;

import com.abhi.tickets.filters.UserProvisioningFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, UserProvisioningFilter userProvisioningFilter) throws Exception {
        http.authorizeHttpRequests(authorize ->
                        authorize
                                // Health check endpoints
                                .requestMatchers("/health", "/health/**").permitAll()

                                // Frontend static resources (if served by Spring Boot)
                                .requestMatchers("/", "/index.html", "/static/**", "/favicon.ico",
                                        "/manifest.json", "/assets/**", "/*.js", "/*.css").permitAll()

                                // Public API endpoints - no authentication required
                                .requestMatchers("/api/v1/published-events/**").permitAll()

                                // Protected API endpoints - require authentication
                                .requestMatchers("/api/v1/events/**").authenticated()
                                .requestMatchers("/api/v1/tickets/**").authenticated()
                                .requestMatchers("/api/v1/ticket-validations").authenticated()

                                // Everything else requires authentication
                                .anyRequest().authenticated())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(Customizer.withDefaults()))
                .addFilterAfter(userProvisioningFilter, BearerTokenAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // Add your frontend URLs here
        configuration.setAllowedOrigins(Arrays.asList(
                "http://88.222.212.114:5173",  // Production frontend
                "http://localhost:5173",        // Development frontend
                "http://88.222.212.114:9090"    // If frontend is served from same domain
        ));

        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}