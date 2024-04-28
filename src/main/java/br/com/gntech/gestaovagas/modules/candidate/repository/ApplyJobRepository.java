package br.com.gntech.gestaovagas.modules.candidate.repository;

import br.com.gntech.gestaovagas.modules.candidate.entities.ApplyJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ApplyJobRepository extends JpaRepository<ApplyJob, UUID> {
}
