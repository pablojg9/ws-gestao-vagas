package br.com.gntech.gestaovagas.security.encoder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * <h3>class to encrypt password</h3>
 *
 * @author pablojg9
 * @version 1.0.0
 * */
@Configuration
public class PasswordBCryptEncoder {

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
