package hu.uni.miskolc.iit.uhv61t.jobSeeker.web;

import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception.PersistenceException;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.service.JobService;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.dao.sqlite.JobDAOImpl;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.service.dao.JobDAO;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.service.impl.JobServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobMethodContext {
    @Bean
    public JobService companyService () throws ClassNotFoundException, PersistenceException {
        return new JobServiceImpl(jobDAO());
    }

    @Bean
    public JobDAO jobDAO () throws PersistenceException {
        return new JobDAOImpl();
    }
}
