package br.com.gntech.gestaovagas.modules.candidate.entities;

import br.com.gntech.gestaovagas.modules.company.entities.Job;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "APPLY_JOBS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyJob {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "candidate_id", insertable = false, updatable = false)
  private Candidate candidate;

  @ManyToOne
  @JoinColumn(name = "job_id", insertable = false, updatable = false)
  private Job job;

  @Column(name = "candidate_id")
  private UUID candidateId;

  @Column(name = "job_id")
  private UUID jobId;

  @CreationTimestamp
  private LocalDateTime created_at;
}
