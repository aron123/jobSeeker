package hu.uni.miskolc.iit.uhv61t.jobSeeker.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

@Configuration
public class JobSeekerWebAppInitializer implements WebApplicationInitializer {
    public void onStartup (ServletContext servletContext) {
        AnnotationConfigWebApplicationContext annotationConfigCtx = new AnnotationConfigWebApplicationContext();

        annotationConfigCtx.register(JobContext.class);
        annotationConfigCtx.register(ApplicationContext.class);
        annotationConfigCtx.setServletContext(servletContext);

        ServletRegistration.Dynamic servletRegistration = servletContext.addServlet(
                "dispatcher", new DispatcherServlet(annotationConfigCtx));

        servletRegistration.setLoadOnStartup(1);
        servletRegistration.addMapping("/");
    }
}