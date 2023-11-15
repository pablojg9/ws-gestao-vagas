package br.com.gntech.gestaovagas.modules.company.controller;

import br.com.gntech.gestaovagas.modules.company.entities.Company;
import br.com.gntech.gestaovagas.modules.company.usecases.CreateCompanyUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CreateCompanyUseCase createCompanyUseCase;

    @Autowired
    public CompanyController(CreateCompanyUseCase createCompanyUseCase) {
        this.createCompanyUseCase = createCompanyUseCase;
    }

    @PostMapping
    public ResponseEntity<Company> createCompany(@Valid @RequestBody Company company) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createCompanyUseCase.execute(company));
    }
}
