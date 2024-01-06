package br.com.gntech.gestaovagas.modules.candidate.usecases;

import br.com.gntech.gestaovagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.gntech.gestaovagas.modules.candidate.entities.Candidate;
import br.com.gntech.gestaovagas.modules.candidate.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    private final CandidateRepository candidateRepository;

    @Autowired
    public ProfileCandidateUseCase(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public ProfileCandidateResponseDTO execute(UUID idCandidate) {
        Candidate candidate = candidateRepository.findById(idCandidate)
                .orElseThrow(() -> new UsernameNotFoundException("user candidate not found"));

        return ProfileCandidateResponseDTO
                .builder()
                .id(candidate.getId())
                .description(candidate.getDescription())
                .email(candidate.getEmail())
                .name(candidate.getName())
                .username(candidate.getUsername())
                .build();
    }
}
