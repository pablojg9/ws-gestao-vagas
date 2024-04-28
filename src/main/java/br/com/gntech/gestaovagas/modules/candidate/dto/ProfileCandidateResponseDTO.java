package br.com.gntech.gestaovagas.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileCandidateResponseDTO {

  private UUID id;

  @Schema(example = "Pablo")
  private String name;

  @Schema(example = "desenvolvedor Java")
  private String description;

  @Schema(example = "pablojg9")
  private String username;

  @Schema(example = "pablo@gmail.com")
  private String email;
}
