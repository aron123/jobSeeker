package hu.uni.miskolc.iit.uhv61t.jobSeeker.web;

import hu.uni.miskolc.iit.uhv61t.jobSeeker.controller.ApplicationController;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
@ComponentScan
public class ApplicationContext {
    private final ApplicationService applicationService;

    @Autowired
    public ApplicationContext (ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @Bean
    public ApplicationController applicationController () {
        return new ApplicationController(applicationService);
    }
}
