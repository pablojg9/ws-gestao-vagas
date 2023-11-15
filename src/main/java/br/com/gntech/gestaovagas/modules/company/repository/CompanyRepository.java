package br.com.gntech.gestaovagas.modules.company.repository;

import br.com.gntech.gestaovagas.modules.company.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {

    Optional<Company> findByUsernameOrEmail(String username, String email);
}
