package br.com.gntech.gestaovagas;

import br.com.gntech.gestaovagas.modules.candidate.usecases.CreateCandidateUseCase;
import br.com.gntech.gestaovagas.modules.company.entities.Company;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class WsGestaoVagasApplicationTests {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Test
    void contextLoads() {
    }

    @Test
    private void createCompanyIsNullOrReturnCompany(Company company) {
        assertNull(company);
    }

}
