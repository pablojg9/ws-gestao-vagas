package br.com.gntech.gestaovagas.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * <p> Classe para pegar o token e fazer o replace do Bearer token {@code code}</p>
 *
 * @tag secretKey
 * @version 1.0.0
 * @author @pablojg9
 */
@Service
public class JwtProvider {

    @Value("${security.token.gntech.secret:DEFAULT}")
    private String secretKey;

    public String validateToken(String token) {
        token = token.replace("Bearer ", "");
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        try {
            return JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return "";
        }
    }
}
