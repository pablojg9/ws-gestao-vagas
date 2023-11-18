package br.com.gntech.gestaovagas.security.chain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfigChain {

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
                            .requestMatchers("/company/").permitAll();
                        auth.anyRequest().authenticated();
                });
        return httpSecurity.build();
    }
}
