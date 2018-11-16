package hu.uni.miskolc.iit.uhv61t.jobSeeker.web;

import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception.PersistenceException;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.service.ApplicantService;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.dao.sqlite.ApplicationDAOImpl;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.service.dao.ApplicationDAO;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.service.impl.ApplicantServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationMethodContext {
    @Bean
    public ApplicantService applicantService () throws ClassNotFoundException, PersistenceException {
        return new ApplicantServiceImpl(applicationDAO());
    }

    @Bean
    public ApplicationDAO applicationDAO () throws PersistenceException, ClassNotFoundException {
        return new ApplicationDAOImpl();
    }
}
