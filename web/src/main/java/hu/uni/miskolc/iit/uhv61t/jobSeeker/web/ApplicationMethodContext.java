package hu.uni.miskolc.iit.uhv61t.jobSeeker.web;

import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception.PersistenceException;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.service.ApplicationService;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.dao.sqlite.ApplicationDAOImpl;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.service.dao.ApplicationDAO;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.service.impl.ApplicationServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationMethodContext {
    @Bean
    public ApplicationService applicantService () throws ClassNotFoundException, PersistenceException {
        return new ApplicationServiceImpl(applicationDAO());
    }

    @Bean
    public ApplicationDAO applicationDAO () throws PersistenceException, ClassNotFoundException {
        return new ApplicationDAOImpl();
    }
}
