package hu.uni.miskolc.iit.uhv61t.jobSeeker.controller;

import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception.MalformedSalaryIntervalException;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception.PersistenceException;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.model.Job;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.service.JobService;
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

    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
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
        Job addedJob = jobService.advertiseJob(Converter.convertJobRequestToJob(jobRequest));
        return this.getSuccessResponse(addedJob);
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
