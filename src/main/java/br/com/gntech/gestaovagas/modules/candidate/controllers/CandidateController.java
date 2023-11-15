package br.com.gntech.gestaovagas.modules.candidate.controllers;

import br.com.gntech.gestaovagas.modules.candidate.entities.Candidate;
import br.com.gntech.gestaovagas.modules.candidate.usecases.CreateCandidateUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    private final CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    public CandidateController(CreateCandidateUseCase createCandidateUseCase) {
        this.createCandidateUseCase = createCandidateUseCase;
    }

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody Candidate candidate) {
        return ResponseEntity.status(HttpStatus.OK).body(createCandidateUseCase.execute(candidate));
    }
}
