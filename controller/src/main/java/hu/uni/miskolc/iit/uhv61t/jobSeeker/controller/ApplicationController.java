package hu.uni.miskolc.iit.uhv61t.jobSeeker.controller;


import hu.uni.miskolc.iit.uhv61t.jobSeeker.controller.dto.ErrorResponse;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.service.ApplicantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class provides controllers for applications.
 */
@RestController
@RequestMapping(path = "/api/applications", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApplicationController {
    private ApplicantService applicantService;

    public ApplicationController(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    //TODO
}
