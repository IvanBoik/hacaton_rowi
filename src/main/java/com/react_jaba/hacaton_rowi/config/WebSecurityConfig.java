package com.react_jaba.hacaton_rowi.config;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthenticationConverter) throws Exception {

        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter)));

        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));

        http.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.csrf(AbstractHttpConfigurer::disable);

        http.exceptionHandling(eh -> eh.authenticationEntryPoint((request, response, authException) -> {
            response.addHeader(HttpHeaders.WWW_AUTHENTICATE, "Basic realm=\"Restricted Content\"");
            response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
        }));

        http.authorizeHttpRequests(accessManagement -> accessManagement
                .requestMatchers("/auth**").permitAll()
                .anyRequest().authenticated()
        );

        return http.build();
    }

    private CorsConfigurationSource corsConfigurationSource() {
        final var configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setExposedHeaders(List.of("*"));

        final var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @RequiredArgsConstructor
    static class JwtGrantedAuthoritiesConverter implements Converter<Jwt, Collection<? extends GrantedAuthority>> {

        @Override
        @SuppressWarnings({ "rawtypes", "unchecked" })
        public Collection<? extends GrantedAuthority> convert(Jwt jwt) {
            return Stream.of("$.realm_access.roles", "$.resource_access.*.roles").flatMap(claimPaths -> {
                        Object claim;
                        try {
                            claim = JsonPath.read(jwt.getClaims(), claimPaths);
                        } catch (PathNotFoundException e) {
                            claim = null;
                        }
                        if (claim == null) {
                            return Stream.empty();
                        }
                        if (claim instanceof String claimStr) {
                            return Stream.of(claimStr.split(","));
                        }
                        if (claim instanceof String[] claimArr) {
                            return Stream.of(claimArr);
                        }
                        if (Collection.class.isAssignableFrom(claim.getClass())) {
                            final var iterator = ((Collection) claim).iterator();
                            if (!iterator.hasNext()) {
                                return Stream.empty();
                            }
                            final var firstItem = iterator.next();
                            if (firstItem instanceof String) {
                                return (Stream<String>) ((Collection) claim).stream();
                            }
                            if (Collection.class.isAssignableFrom(firstItem.getClass())) {
                                return (Stream<String>) ((Collection) claim).stream().flatMap(colItem -> ((Collection) colItem).stream()).map(String.class::cast);
                            }
                        }
                        return Stream.empty();
                    })
                    .map(SimpleGrantedAuthority::new)
                    .map(GrantedAuthority.class::cast).toList();
        }
    }

    @Component
    @RequiredArgsConstructor
    static class SpringAddonsJwtAuthenticationConverter implements Converter<Jwt, JwtAuthenticationToken> {

        @Override
        public JwtAuthenticationToken convert(Jwt jwt) {
            final var authorities = new JwtGrantedAuthoritiesConverter().convert(jwt);
            final String username = JsonPath.read(jwt.getClaims(), "preferred_username");
            return new JwtAuthenticationToken(jwt, authorities, username);
        }
    }
}