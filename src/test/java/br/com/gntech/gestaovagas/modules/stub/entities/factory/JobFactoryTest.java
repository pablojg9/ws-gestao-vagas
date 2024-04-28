package br.com.gntech.gestaovagas.modules.stub.entities.factory;

import br.com.gntech.gestaovagas.modules.company.entities.Job;

import java.time.LocalDateTime;
import java.util.UUID;

public class JobFactoryTest {

    public static Job createJob() {
        Job job = new Job();
        job.setId(UUID.randomUUID());
        job.setCompany(CompanyFactoryTest.createCompany());
        job.setCompanyId(CompanyFactoryTest.createCompany().getId());
        job.setBenefits("Gympass");
        job.setLevel("Pleno");
        job.setCreatedAt(LocalDateTime.now());
        return job;
    }
}
