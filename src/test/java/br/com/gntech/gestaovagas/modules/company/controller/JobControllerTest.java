package br.com.gntech.gestaovagas.modules.company.controller;

import br.com.gntech.gestaovagas.modules.company.dto.CreateJobDTO;
import br.com.gntech.gestaovagas.modules.company.entities.Company;
import br.com.gntech.gestaovagas.modules.company.repository.CompanyRepository;
import br.com.gntech.gestaovagas.modules.utils.UtilsTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static br.com.gntech.gestaovagas.modules.utils.UtilsTest.objectToJson;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class JobControllerTest {

  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext applicationContext;

  @Autowired
  private CompanyRepository companyRepository;

  @Value("${security.token.gntech.secret}")
  private String secret;

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders
      .webAppContextSetup(applicationContext)
      .apply(SecurityMockMvcConfigurers.springSecurity())
      .build();
  }

  @Test
  @DisplayName("should be able to create a new job")
  void should_be_able_to_create_a_new_job() throws Exception {

    Company company = Company.builder()
      .username("pablojunior")
      .email("pablojunior@gmail.com")
      .password("pablojunior12345")
      .webSite("https://www.pablojunior.com")
      .name("Pablo Junior")
      .description("descricao")
      .build();

    companyRepository.saveAndFlush(company);

    CreateJobDTO createJobDTO = CreateJobDTO.builder()
      .benefits("BENEFITS-TEST")
      .description("DESCRIPTION-TEST")
      .level("LEVEL-TEST")
      .build();

    mockMvc.perform(MockMvcRequestBuilders.post("/company/job")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectToJson(createJobDTO)).header("Authorization", UtilsTest.generateToken(company.getId(), secret)))
      .andExpect(status().isOk());
  }

  @Test
  @DisplayName("should be a able to create a new job if company not found")
  void should_be_a_able_to_create_a_new_job_if_company_not_found() throws Exception {
    CreateJobDTO createJobDTO = CreateJobDTO.builder()
      .benefits("BENEFITS-TEST")
      .description("DESCRIPTION-TEST")
      .level("LEVEL-TEST")
      .build();

    mockMvc.perform(MockMvcRequestBuilders
        .post("/company/job")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectToJson(createJobDTO))
        .header("Authorization", UtilsTest.generateToken(UUID.randomUUID(), secret)))
      .andExpect(status().is4xxClientError());
  }
}