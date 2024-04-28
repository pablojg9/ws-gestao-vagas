package br.com.gntech.gestaovagas.modules.stub.entities.factory;

import br.com.gntech.gestaovagas.modules.company.entities.Company;

import java.time.LocalDateTime;
import java.util.UUID;

public class CompanyFactoryTest {

    public static Company createCompany() {
        Company company = new Company();
        company.setId(UUID.randomUUID());
        company.setName("GNTECH");
        company.setEmail("gntech@gmail.com");
        company.setPassword("1234567");
        company.setWebSite("gntech.com.br");
        company.setCreatedAt(LocalDateTime.now());
        company.setDescription("Empresa de tech");
        return company;
    }
}
