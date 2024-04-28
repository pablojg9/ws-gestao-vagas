package br.com.gntech.gestaovagas.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobDTO {

    @Schema(example = "vaga para desenvolvedor backend Java (Spring Boot)", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;
    @Schema(example = "Gympass, cursos de 10% de desconto, plano de saude, day off", requiredMode = Schema.RequiredMode.REQUIRED)
    private String benefits;
    @Schema(example = "Junior", requiredMode = Schema.RequiredMode.REQUIRED)
    private String level;
}
