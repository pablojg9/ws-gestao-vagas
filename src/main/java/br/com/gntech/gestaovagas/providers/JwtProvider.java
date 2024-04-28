package br.com.gntech.gestaovagas.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * <p> Classe para pegar o token e fazer o replace do Bearer token {@code code}</p>
 *
 * @author @pablojg9
 * @version 1.0.0
 * @tag secretKey
 */
@Service
public class JwtProvider {

  @Value("${security.token.gntech.secret:DEFAULT}")
  private String secretKey;

  public DecodedJWT validateToken(String token) {
    token = token.replace("Bearer ", "");
    Algorithm algorithm = Algorithm.HMAC256(secretKey);

    try {
      return JWT.require(algorithm)
        .build()
        .verify(token);

    } catch (JWTVerificationException e) {
      e.printStackTrace();
      return null;
    }
  }
}
