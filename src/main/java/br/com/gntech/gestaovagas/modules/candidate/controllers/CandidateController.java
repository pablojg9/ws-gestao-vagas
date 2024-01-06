package br.com.gntech.gestaovagas.modules.candidate.controllers;

import br.com.gntech.gestaovagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.gntech.gestaovagas.modules.candidate.entities.Candidate;
import br.com.gntech.gestaovagas.modules.candidate.usecases.CreateCandidateUseCase;
import br.com.gntech.gestaovagas.modules.candidate.usecases.ProfileCandidateUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    private final CreateCandidateUseCase createCandidateUseCase;
    private final ProfileCandidateUseCase profileCandidateUseCase;

    @Autowired
    public CandidateController(CreateCandidateUseCase createCandidateUseCase, ProfileCandidateUseCase profileCandidateUseCase) {
        this.createCandidateUseCase = createCandidateUseCase;
        this.profileCandidateUseCase = profileCandidateUseCase;
    }

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody Candidate candidate) {
        return ResponseEntity.status(HttpStatus.OK).body(createCandidateUseCase.execute(candidate));
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('CANDIDATE')")
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
}
