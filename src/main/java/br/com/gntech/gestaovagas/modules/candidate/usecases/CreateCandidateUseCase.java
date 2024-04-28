package br.com.gntech.gestaovagas.modules.candidate.usecases;

import br.com.gntech.gestaovagas.exceptions.ObjectFoundException;
import br.com.gntech.gestaovagas.modules.candidate.entities.Candidate;
import br.com.gntech.gestaovagas.modules.candidate.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

  private final PasswordEncoder passwordEncoder;

  private final CandidateRepository candidateRepository;

  @Autowired
  public CreateCandidateUseCase(CandidateRepository candidateRepository, PasswordEncoder passwordEncoder) {
    this.candidateRepository = candidateRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public Candidate execute(Candidate candidate) {
    candidateRepository.findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail()).ifPresent(x -> {
      throw new ObjectFoundException("user found");
    });

    String passwordCrypt = passwordEncoder.encode(candidate.getPassword());
    candidate.setPassword(passwordCrypt);
    return candidateRepository.save(candidate);
  }
}
