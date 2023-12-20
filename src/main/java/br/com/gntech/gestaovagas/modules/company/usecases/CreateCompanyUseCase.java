package br.com.gntech.gestaovagas.modules.company.usecases;

import br.com.gntech.gestaovagas.exceptions.ObjectFoundException;
import br.com.gntech.gestaovagas.modules.company.entities.Company;
import br.com.gntech.gestaovagas.modules.company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    private final CompanyRepository companyRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CreateCompanyUseCase(CompanyRepository companyRepository, PasswordEncoder passwordEncoder) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Company execute(Company company) {
        companyRepository.findByUsernameOrEmail(company.getUsername(), company.getEmail()).ifPresent(x -> {
            throw new ObjectFoundException("username or email company used, try other names.");
        });

        String passwordCrypt = passwordEncoder.encode(company.getPassword());
        company.setPassword(passwordCrypt);

        return this.companyRepository.save(company);
    }
}
