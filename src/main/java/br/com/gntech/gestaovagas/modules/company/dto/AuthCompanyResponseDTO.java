package br.com.gntech.gestaovagas.modules.company.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthCompanyResponseDTO {

    @JsonProperty("acess_token")
    private String accessToken;

    @JsonProperty("expiresIn")
    private Long expiresIn;

}
