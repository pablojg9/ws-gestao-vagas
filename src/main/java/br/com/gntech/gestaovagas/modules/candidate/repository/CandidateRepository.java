package br.com.gntech.gestaovagas.modules.candidate.repository;

import br.com.gntech.gestaovagas.modules.candidate.entities.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, UUID> {

  Optional<Candidate> findByUsernameOrEmail(String username, String email);

  Optional<Candidate> findByUsername(String username);

}
