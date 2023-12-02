package com.example.api.filter;

import com.example.api.configuration.JwtConfig;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtConfig jwtConfig;
    private JwtParser jwtParser;

    @PostConstruct
    private void init() {
        jwtParser = Jwts.parser()
                .verifyWith(jwtConfig.getSecretKey())
                .build();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final var authHeader = request.getHeader(jwtConfig.getAuthHeader());

        if (authHeader == null || !authHeader.startsWith(jwtConfig.getTokenPrefix())) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            final var jwtToken = authHeader.replace(jwtConfig.getTokenPrefix(), "");
            final var parsedJwt = jwtParser.parseSignedClaims(jwtToken);
            final var payload = parsedJwt.getPayload();

            if (payload.getExpiration().before(new Date())) {
                throw new ExpiredJwtException(parsedJwt.getHeader(), payload, "Token has expired");
            }

            final var username = payload.getSubject();
            final var authorities = ((Collection<?>) payload.get("authorities")).stream()
                    .map(authority -> (GrantedAuthority) () -> (String) authority)
                    .toList();
            final var authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception exception) {
            SecurityContextHolder.clearContext();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);
    }
}
