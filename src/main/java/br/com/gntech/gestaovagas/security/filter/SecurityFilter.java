package br.com.gntech.gestaovagas.security.filter;

import br.com.gntech.gestaovagas.providers.JwtProvider;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    private static final String PREFIX_ROLE = "ROLE_";

    @Autowired
    public SecurityFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //SecurityContextHolder.getContext().setAuthentication(null);
        String authorization = request.getHeader("Authorization");

        if (request.getRequestURI().startsWith("/company")) {
            Optional.ofNullable(authorization).ifPresent(x -> {
                DecodedJWT token = jwtProvider.validateToken(authorization);

                if (token == null) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }

                List<Object> roles = token.getClaim("roles").asList(Object.class);
                List<SimpleGrantedAuthority> simpleGrantedAuthority = roles.stream()
                        .map(role -> new SimpleGrantedAuthority(PREFIX_ROLE + role.toString().toUpperCase()))
                        .toList();

                request.setAttribute("companyId", token.getSubject());

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        token.getSubject(),
                        null,
                        simpleGrantedAuthority
                );
                SecurityContextHolder.getContext().setAuthentication(auth);
            });
        }
        filterChain.doFilter(request, response);
    }
}
