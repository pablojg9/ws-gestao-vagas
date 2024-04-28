package br.com.gntech.gestaovagas.modules.company.usecases;

import br.com.gntech.gestaovagas.exceptions.CompanyNotFoundException;
import br.com.gntech.gestaovagas.modules.company.entities.Job;
import br.com.gntech.gestaovagas.modules.company.repository.CompanyRepository;
import br.com.gntech.gestaovagas.modules.company.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

  private final JobRepository jobRepository;

  private final CompanyRepository companyRepository;

  @Autowired
  public CreateJobUseCase(final JobRepository jobRepository, final CompanyRepository companyRepository) {
    this.jobRepository = jobRepository;
    this.companyRepository = companyRepository;
  }

  public Job execute(Job job) {
    companyRepository.findById(job.getCompanyId())
      .orElseThrow(() -> new CompanyNotFoundException("company is a not found"));

    return jobRepository.save(job);
  }
}
