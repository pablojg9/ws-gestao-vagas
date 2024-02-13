package br.com.gntech.gestaovagas.modules.company.controller;

import br.com.gntech.gestaovagas.modules.company.dto.CreateJobDTO;
import br.com.gntech.gestaovagas.modules.company.entities.Job;
import br.com.gntech.gestaovagas.modules.company.usecases.CreateJobUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/company/job")
public class JobController {

    private final CreateJobUseCase createJobUseCase;

    @Autowired
    public JobController(CreateJobUseCase createJobUseCase) {
        this.createJobUseCase = createJobUseCase;
    }

    @PostMapping
    @PreAuthorize("hasRole('COMPANY')")
    @Tag(name = "Vagas", description = "job information")
    @Operation(summary = "Cadastro de vagas", description = "endpoint para fazer a criacao de um vaga")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = Job.class)
                            )
                    }
            )
    )
    @SecurityRequirement(name = "jwt_auth")
    public Job create(@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request) {
        Object companyId = request.getAttribute("companyId");
        Job jobBuilder = Job.builder()
                .benefits(createJobDTO.getBenefits())
                .companyId(UUID.fromString(companyId.toString()))
                .description(createJobDTO.getDescription())
                .level(createJobDTO.getLevel())
                .build();

        return this.createJobUseCase.execute(jobBuilder);

    }
}
