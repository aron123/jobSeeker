package hu.uni.miskolc.iit.uhv61t.jobSeeker.web;

import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception.PersistenceException;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.service.CompanyService;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.dao.sqlite.ApplicationDAOImpl;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.dao.sqlite.JobDAOImpl;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.service.dao.ApplicationDAO;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.service.dao.JobDAO;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.service.impl.CompanyServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class JobMethodContext {
    @Bean
    public CompanyService companyService () throws ClassNotFoundException, PersistenceException {
        return new CompanyServiceImpl(jobDAO(), applicationDAO());
    }

    @Bean
    public JobDAO jobDAO () throws PersistenceException {
        return new JobDAOImpl();
    }

    @Bean
    public ApplicationDAO applicationDAO () throws PersistenceException, ClassNotFoundException {
        return new ApplicationDAOImpl();
    }
}
