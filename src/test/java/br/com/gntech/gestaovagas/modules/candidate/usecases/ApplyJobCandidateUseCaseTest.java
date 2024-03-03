package br.com.gntech.gestaovagas.modules.candidate.usecases;

import br.com.gntech.gestaovagas.exceptions.UserNotFoundException;
import br.com.gntech.gestaovagas.modules.candidate.repository.CandidateRepository;
import br.com.gntech.gestaovagas.modules.company.repository.JobRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ApplyJobCandidateUseCaseTest {

    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("should not be able to apply job with candidate not found")
    void should_not_be_able_to_apply_job_with_candidate_not_found() {
        try {
            applyJobCandidateUseCase.execute(null, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(UserNotFoundException.class);
        }
        //
    }
}