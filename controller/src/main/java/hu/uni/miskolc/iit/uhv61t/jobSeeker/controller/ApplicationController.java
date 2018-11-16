package hu.uni.miskolc.iit.uhv61t.jobSeeker.controller;

import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.service.ApplicationService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class provides controllers for applications.
 */
@RestController
@RequestMapping(path = "/api/applications", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApplicationController extends BaseController {
    private ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    //TODO
}
