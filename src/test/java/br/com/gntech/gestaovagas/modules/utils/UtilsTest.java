package br.com.gntech.gestaovagas.modules.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class UtilsTest {

  public static String objectToJson(Object object) {
    try {
      ObjectWriter objectMapper = new ObjectMapper().writer().withDefaultPrettyPrinter();
      return objectMapper.writeValueAsString(object);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static String generateToken(UUID uuid, String secret) {
    Algorithm algorithm = Algorithm.HMAC256(secret);
    Instant expiresIn = Instant.now().plus(Duration.ofHours(2));

    return JWT.create().withIssuer("gntech")
      .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
      .withSubject(uuid.toString())
      .withExpiresAt(expiresIn)
      .withClaim("roles", List.of("COMPANY"))
      .sign(algorithm);
  }
}
