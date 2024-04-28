package br.com.gntech.gestaovagas.modules.candidate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthCandidateResponseDTO {

  @JsonProperty("access_token")
  private String accessToken;

  @JsonProperty("expire_in")
  private Long expireIn;
}
