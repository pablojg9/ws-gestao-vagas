package br.com.gntech.gestaovagas.modules.candidate.usecases;

import br.com.gntech.gestaovagas.exceptions.JobNotFoundException;
import br.com.gntech.gestaovagas.exceptions.UserNotFoundException;
import br.com.gntech.gestaovagas.modules.candidate.entities.ApplyJob;
import br.com.gntech.gestaovagas.modules.candidate.repository.ApplyJobRepository;
import br.com.gntech.gestaovagas.modules.candidate.repository.CandidateRepository;
import br.com.gntech.gestaovagas.modules.company.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplyJobCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    public ApplyJob execute(UUID idCandidate, UUID idJob) {
        candidateRepository.findById(idCandidate)
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));

        jobRepository.findById(idJob)
                .orElseThrow(() -> new JobNotFoundException("Job Not found"));

        ApplyJob applyJob = ApplyJob
                .builder()
                .candidateId(idCandidate)
                .jobId(idJob)
                .build();
        applyJobRepository.save(applyJob);

        return applyJob;
    }
}
