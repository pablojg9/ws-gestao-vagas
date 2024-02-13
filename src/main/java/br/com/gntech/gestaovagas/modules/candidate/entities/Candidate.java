package br.com.gntech.gestaovagas.modules.candidate.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "CANDIDATE")
@Data
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Schema(example = "Pablo", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotBlank
    @Pattern(regexp = "\\S+", message = "O campo [username] nao deve conter espaco")
    @Schema(example = "pablojg9", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @Email(message = "o campo deve conter um e-mail valido")
    @Schema(example = "pablo@gmail.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Length(min = 10, max = 100)
    @Schema(example = "senha", minLength = 10, maxLength = 100, requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    @Schema(example = "Desenvolvedor Java, Spring Boot, Spring data JPA, Docker, PostgreSQL", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
