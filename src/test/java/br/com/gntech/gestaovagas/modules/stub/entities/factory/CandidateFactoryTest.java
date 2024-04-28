package br.com.gntech.gestaovagas.modules.stub.entities.factory;

import br.com.gntech.gestaovagas.modules.candidate.entities.Candidate;

import java.time.LocalDateTime;
import java.util.UUID;

public class CandidateFactoryTest {

    public static Candidate createCandidate() {
        Candidate candidate = new Candidate();
        candidate.setId(UUID.randomUUID());
        candidate.setName("Pablo Junior");
        candidate.setEmail("pablo@gmail.com");
        candidate.setUsername("pablojg9");
        candidate.setDescription("Desenvolvedor Java");
        candidate.setCurriculum("Sim");
        candidate.setCreatedAt(LocalDateTime.now());
        return candidate;
    }
}