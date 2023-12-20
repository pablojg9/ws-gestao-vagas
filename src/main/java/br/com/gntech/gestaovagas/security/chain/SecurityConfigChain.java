package br.com.gntech.gestaovagas.security.chain;

import br.com.gntech.gestaovagas.security.filter.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfigChain {

    private final SecurityFilter securityFilter;

    @Autowired
    public SecurityConfigChain(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    /**
     * <h3>enabling security for endpoints.</h3>
     * @param httpSecurity Get HttpSecurity to disable all security and leave only the desired endpoints.
     * @see SecurityConfigChain class that makes the entire application secure
     * @author pablojg9
     * @Version 1.0.0
     * **/
    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                        auth.requestMatchers("/candidate/").permitAll()
                                .requestMatchers("/company/").permitAll()
                                .requestMatchers("/auth/company").permitAll()
                                .requestMatchers("/candidate/auth").permitAll();
                        auth.anyRequest().authenticated();
                }).addFilterBefore(securityFilter, BasicAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
