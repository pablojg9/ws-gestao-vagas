package br.com.gntech.gestaovagas.modules.candidate.usecases;

import br.com.gntech.gestaovagas.exceptions.JobNotFoundException;
import br.com.gntech.gestaovagas.exceptions.UserNotFoundException;
import br.com.gntech.gestaovagas.modules.candidate.entities.ApplyJob;
import br.com.gntech.gestaovagas.modules.candidate.entities.Candidate;
import br.com.gntech.gestaovagas.modules.candidate.repository.ApplyJobRepository;
import br.com.gntech.gestaovagas.modules.candidate.repository.CandidateRepository;
import br.com.gntech.gestaovagas.modules.company.entities.Job;
import br.com.gntech.gestaovagas.modules.company.repository.JobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static br.com.gntech.gestaovagas.modules.stub.entities.factory.CandidateFactoryTest.createCandidate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApplyJobCandidateUseCaseTest {

  @InjectMocks
  private ApplyJobCandidateUseCase applyJobCandidateUseCase;

  @Mock
  private CandidateRepository candidateRepository;

  @Mock
  private JobRepository jobRepository;

  @Mock
  private ApplyJobRepository applyJobRepository;

  private Optional<Candidate> candidateOptional;

  @BeforeEach
  void setUp() {
    candidateOptional = Optional.of(createCandidate());
  }

  @Test
  @DisplayName("should not be able to apply job with candidate not found")
  void should_not_be_able_to_apply_job_with_candidate_not_found() {
    try {
      applyJobCandidateUseCase.execute(null, null);
    } catch (Exception e) {
      assertThat(e).isInstanceOf(UserNotFoundException.class);
    }
  }

  @Test
  @DisplayName("should not be able to apply job with job not found")
  void should_not_be_able_to_apply_job_with_job_not_found() {
    when(candidateRepository.findById(any())).thenReturn(candidateOptional);
    try {
      applyJobCandidateUseCase.execute(candidateOptional.get().getId(), null);
    } catch (Exception e) {
      assertThat(e).isInstanceOf(JobNotFoundException.class);
    }
  }

  @Test
  @DisplayName("should be able to create a new apply job")
  void should_be_able_to_create_a_new_apply_job() {
    UUID idCandidate = UUID.randomUUID();
    UUID idJob = UUID.randomUUID();

    ApplyJob createdApplyJob = ApplyJob.builder()
      .id(UUID.randomUUID())
      .build();

    when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(new Candidate()));
    when(jobRepository.findById(idJob)).thenReturn(Optional.of(new Job()));

    ApplyJob result = applyJobCandidateUseCase.execute(idCandidate, idJob);
    assertThat(result).hasFieldOrProperty("id");
    assertNotNull(createdApplyJob.getId());
  }
}