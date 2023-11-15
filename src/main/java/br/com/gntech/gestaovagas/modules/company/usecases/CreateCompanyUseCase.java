package br.com.gntech.gestaovagas.modules.company.usecases;

import br.com.gntech.gestaovagas.exceptions.ObjectFoundException;
import br.com.gntech.gestaovagas.modules.company.entities.Company;
import br.com.gntech.gestaovagas.modules.company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    private final CompanyRepository companyRepository;

    @Autowired
    public CreateCompanyUseCase(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company execute(Company company) {
        companyRepository.findByUsernameOrEmail(company.getUsername(), company.getEmail()).ifPresent(x -> {
            throw new ObjectFoundException("username or email company used, try other names.");
        });

        return this.companyRepository.save(company);
    }
}
