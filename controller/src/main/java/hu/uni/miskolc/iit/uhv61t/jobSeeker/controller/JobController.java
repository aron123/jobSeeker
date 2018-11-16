package hu.uni.miskolc.iit.uhv61t.jobSeeker.controller;

import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception.MalformedSalaryIntervalException;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception.PersistenceException;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.model.Company;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.model.EducationLevel;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.model.Job;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.controller.dto.*;

/**
 * This class provides controller methods for Job objects.
 */
@RestController
@RequestMapping(path = "/api/jobs", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class JobController extends BaseController {

    private CompanyService companyService;

    public JobController(CompanyService companyService) {
        this.companyService = companyService;
    }

    /**
     * Adds new job to the database.
     * @param jobRequest DTO with data of the new job.
     * @return JobResponse DTO object with the added job.
     * @throws PersistenceException
     * @throws MalformedSalaryIntervalException
     */
    @PostMapping(path = "/advertise", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody JobResponse advertiseJob (@RequestBody JobRequest jobRequest)
            throws PersistenceException, MalformedSalaryIntervalException {
        Job addedJob = companyService.advertiseJob(Converter.convertJobRequestToJob(jobRequest));
        return this.getSuccessResponse(addedJob);
   }

    /**
     * Handles MalformedSalaryIntervalException exception.
     * @return Response entity with the appropriate error message and status.
     */
   @ExceptionHandler(MalformedSalaryIntervalException.class)
   public ResponseEntity<ErrorResponse> handleMalformedSalaryIntervalException () {
        return this.getErrorResponseEntity("The given salary interval is malformed", HttpStatus.BAD_REQUEST);
   }

    /**
     * Creates JobResponse object from the given Job object.
     * @param job The job to add to response.
     * @return The successful response.
     */
   private JobResponse getSuccessResponse (Job job) {
       JobResponse response = new JobResponse();
       response.setSuccess(true);
       response.setJob(Converter.convertJobToDTO(job));
       return response;
   }
}
