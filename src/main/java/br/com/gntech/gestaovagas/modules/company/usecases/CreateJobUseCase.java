package br.com.gntech.gestaovagas.modules.company.usecases;

import br.com.gntech.gestaovagas.modules.company.entities.Job;
import br.com.gntech.gestaovagas.modules.company.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    private final JobRepository jobRepository;

    @Autowired
    public CreateJobUseCase(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Job execute(Job job) {
        return jobRepository.save(job);
    }
}
