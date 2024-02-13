package br.com.gntech.gestaovagas.modules.candidate.controllers;

import br.com.gntech.gestaovagas.exceptions.ObjectFoundException;
import br.com.gntech.gestaovagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.gntech.gestaovagas.modules.candidate.entities.Candidate;
import br.com.gntech.gestaovagas.modules.candidate.usecases.CreateCandidateUseCase;
import br.com.gntech.gestaovagas.modules.candidate.usecases.ListAllJobsByFilterUseCase;
import br.com.gntech.gestaovagas.modules.candidate.usecases.ProfileCandidateUseCase;
import br.com.gntech.gestaovagas.modules.company.entities.Job;
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
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/candidate")
@Tag(name = "Candidato", description = "candidate information")
public class CandidateController {

    private final CreateCandidateUseCase createCandidateUseCase;
    private final ProfileCandidateUseCase profileCandidateUseCase;
    private final ListAllJobsByFilterUseCase jobsByFilterUseCase;

    @Autowired
    public CandidateController(CreateCandidateUseCase createCandidateUseCase, ProfileCandidateUseCase profileCandidateUseCase, ListAllJobsByFilterUseCase jobsByFilterUseCase) {
        this.createCandidateUseCase = createCandidateUseCase;
        this.profileCandidateUseCase = profileCandidateUseCase;
        this.jobsByFilterUseCase = jobsByFilterUseCase;
    }

    @PostMapping("/")
    @Operation(summary = "Cadastro de usuario", description = "essa funcao e responsavel para cadastras o candidato")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Candidate.class)
                            )
                    }),
                    @ApiResponse(responseCode = "400", description = "user found",
                            content =  {
                                    @Content(
                                            schema = @Schema(implementation = ObjectFoundException.class)
                                    )
                            }
                    )
            }
    )
    public ResponseEntity<Object> create(@Valid @RequestBody Candidate candidate) {
        return ResponseEntity.status(HttpStatus.OK).body(createCandidateUseCase.execute(candidate));
    }

    @GetMapping(value = "/")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Perfil do candidato", description = "essa funcao e responsavel por buscar o perfil do usuario")
    @SecurityRequirement(name = "jwt_auth")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ProfileCandidateResponseDTO.class)
                            )
                    }),
                    @ApiResponse(responseCode = "400", description = "user candidate not found",
                            content =  {
                               @Content(
                                       schema = @Schema(implementation = UsernameNotFoundException.class)
                               )
                            }
                    )
            }
    )
    public ResponseEntity<Object> get(HttpServletRequest request) {
        Object idCandidate = request.getAttribute("candidate_id");
        try {
            return ResponseEntity
                    .ok()
                    .body(profileCandidateUseCase.execute(UUID.fromString(idCandidate.toString())));
        }catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping("/job")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Listagem de vagas diponiveis para o candidato", description = "Essa funcao e responsavel por listar todas as vagas diponiveis baseada no filtro")
    @ApiResponses(
            @ApiResponse(responseCode = "200", content = {
                    @Content(
                            array = @ArraySchema(schema = @Schema(implementation = Job.class))
                    )
            })
    )
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<List<Job>> findJobByFilter(@RequestParam String filter) {
        return ResponseEntity.ok().body(jobsByFilterUseCase.execute(filter));
    }
}
