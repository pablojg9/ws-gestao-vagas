package br.com.gntech.gestaovagas.modules.candidate.controllers;

import br.com.gntech.gestaovagas.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.gntech.gestaovagas.modules.candidate.dto.AuthCandidateResponseDTO;
import br.com.gntech.gestaovagas.modules.candidate.usecases.AuthCandidadeUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class AuthCandidateController {

    private final AuthCandidadeUseCase authCandidadeUseCase;

    @Autowired
    public AuthCandidateController(AuthCandidadeUseCase authCandidadeUseCase) {
        this.authCandidadeUseCase = authCandidadeUseCase;
    }


    @PostMapping("/auth")
    public ResponseEntity<Object> auth(@RequestBody AuthCandidateRequestDTO candidateRequestDTO) {
        try {
            AuthCandidateResponseDTO authCandidateResponseDTO = authCandidadeUseCase.execute(candidateRequestDTO);
            return ResponseEntity.status(HttpStatus.OK).body(authCandidateResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
