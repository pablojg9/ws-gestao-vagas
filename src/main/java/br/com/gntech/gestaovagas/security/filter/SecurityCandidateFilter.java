package br.com.gntech.gestaovagas.security.filter;

import br.com.gntech.gestaovagas.providers.JwtCandidateProvider;
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
import java.util.List;
import java.util.Optional;

@Component
public class SecurityCandidateFilter extends OncePerRequestFilter {

    private final JwtCandidateProvider jwtCandidateProvider;
    private static final String ROLE_PREFIX = "ROLE_";

    @Autowired
    public SecurityCandidateFilter(JwtCandidateProvider jwtCandidateProvider) {
        this.jwtCandidateProvider = jwtCandidateProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       //SecurityContextHolder.getContext().setAuthentication(null);
        String authorization = request.getHeader("Authorization");

        if (request.getRequestURI().startsWith("/candidate")) {
            Optional.ofNullable(authorization).ifPresent(x -> {
                DecodedJWT decodedJWT = jwtCandidateProvider.validateToken(authorization);

                if (decodedJWT == null) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }

                request.setAttribute("candidate_id", decodedJWT.getSubject());
                List<Object> roles = decodedJWT.getClaim("roles").asList(Object.class);

                List<SimpleGrantedAuthority> grantedAuthorities = roles.stream()
                        .map(role -> new SimpleGrantedAuthority(ROLE_PREFIX + role.toString().toUpperCase()))
                        .toList();

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(decodedJWT.getSubject(), null, grantedAuthorities);
                SecurityContextHolder.getContext().setAuthentication(auth);
            });
        }

        filterChain.doFilter(request, response);
    }
}
