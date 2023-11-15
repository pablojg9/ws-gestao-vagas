package br.com.gntech.gestaovagas.modules.company.controller;

import br.com.gntech.gestaovagas.modules.company.entities.Job;
import br.com.gntech.gestaovagas.modules.company.usecases.CreateJobUseCase;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job")
public class JobController {

    private final CreateJobUseCase createJobUseCase;

    @Autowired
    public JobController(CreateJobUseCase createJobUseCase) {
        this.createJobUseCase = createJobUseCase;
    }

    @PostMapping
    public Job create(@Valid @RequestBody Job job) {
        return this.createJobUseCase.execute(job);
    }
}
