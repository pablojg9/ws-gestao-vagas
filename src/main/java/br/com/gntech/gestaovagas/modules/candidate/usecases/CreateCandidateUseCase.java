package br.com.gntech.gestaovagas.modules.candidate.usecases;

import br.com.gntech.gestaovagas.exceptions.ObjectFoundException;
import br.com.gntech.gestaovagas.modules.candidate.entities.Candidate;
import br.com.gntech.gestaovagas.modules.candidate.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

    private final CandidateRepository candidateRepository;

    @Autowired
    public CreateCandidateUseCase(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public Candidate execute(Candidate candidate) {
        candidateRepository.findByUsernameOrOrEmail(candidate.getUsername(), candidate.getEmail()).ifPresent(x -> {
            throw new ObjectFoundException("user found");
        });

        return candidateRepository.save(candidate);
    }
}
