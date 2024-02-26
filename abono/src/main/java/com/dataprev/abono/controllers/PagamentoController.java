package com.dataprev.abono.controllers;

import com.dataprev.abono.models.Pagamento;
import com.dataprev.abono.repositories.PagamentoCriteriaRepository;
import com.dataprev.abono.repositories.criterias.PagamentoCriteria;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value="/api/pagamento")
public class PagamentoController {
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job;
    @Autowired
    private PagamentoCriteriaRepository pagamentoCriteriaRepository;

//    @Autowired
//    public PagamentoController(JobLauncher jobLauncher, Job job) {
//        this.jobLauncher = jobLauncher;
//        this.job = job;
//    }

    @GetMapping
    public List<Pagamento> getPagamentoAnoBase(@RequestParam(required = false) String anoBase, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy/MM/dd") Date inicioIntervalo, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy/MM/dd") Date fimIntervalo) {
        return pagamentoCriteriaRepository.findAllPagamentosWithCriteria(anoBase, inicioIntervalo, fimIntervalo);
    }

    @PostMapping
    public void gerarPagamentoAbonoReport() {
        JobParameters jobParameters = new JobParametersBuilder()
                .toJobParameters();
        try {
            jobLauncher.run(job, jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }
}
