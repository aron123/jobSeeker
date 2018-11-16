package hu.uni.miskolc.iit.uhv61t.jobSeeker.web;

import hu.uni.miskolc.iit.uhv61t.jobSeeker.controller.JobController;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
@ComponentScan
public class JobContext {
    private final CompanyService companyService;

    @Autowired
    public JobContext(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Bean
    public JobController jobController () {
        return new JobController(companyService);
    }
}
