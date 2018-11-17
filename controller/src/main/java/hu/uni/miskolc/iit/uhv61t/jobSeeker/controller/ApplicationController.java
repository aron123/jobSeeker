package hu.uni.miskolc.iit.uhv61t.jobSeeker.controller;

import hu.uni.miskolc.iit.uhv61t.jobSeeker.controller.dto.ApplicationCollectionResponse;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.controller.dto.ErrorResponse;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception.*;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.model.*;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.service.ApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

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

    /**
     * Returns all applications from the database.
     * @return All applications.
     * @throws NoApplicationFoundException
     * @throws PersistenceException
     */
    @GetMapping
    public ApplicationCollectionResponse getAllApplications () throws NoApplicationFoundException, PersistenceException {
        return this.getSuccessResponse(applicationService.listAllApplications());
    }

    /**
     * Returns all applications of the given applicant
     * @param applicantId ID of the applicant.
     * @return Applications of the applicant.
     * @throws NotExistingApplicantException
     * @throws NoApplicationFoundException
     * @throws PersistenceException
     */
    @GetMapping("/applicant/{id}")
    public ApplicationCollectionResponse getApplicationsByApplicant (@PathVariable("id") int applicantId)
            throws NotExistingApplicantException, NoApplicationFoundException, PersistenceException {
        return this.getSuccessResponse(applicationService.listApplicationsByApplicant(applicantId));
    }

    /**
     * Returns all applications to the given job.
     * @param jobId ID of the job.
     * @return All applications to the job.
     * @throws NotExistingJobException
     * @throws NoApplicationFoundException
     * @throws PersistenceException
     */
    @GetMapping("/job/{id}")
    public ApplicationCollectionResponse getApplicationsByJob (@PathVariable("id") int jobId)
            throws NotExistingJobException, NoApplicationFoundException, PersistenceException {
        return this.getSuccessResponse(applicationService.listApplicationsByJob(jobId));
    }

    /**
     * Returns all applications where the applicant's salary demand is between the given interval.
     * @param minimumSalary Minimum salary value.
     * @param maximumSalary Maximum salary value.
     * @return Applications that fulfill the given condition.
     * @throws MalformedSalaryIntervalException
     * @throws NoApplicationFoundException
     * @throws PersistenceException
     */
    @GetMapping("/salaryDemand")
    public ApplicationCollectionResponse getApplicationsBySalaryDemand
            (@RequestParam long minimumSalary, @RequestParam long maximumSalary)
            throws MalformedSalaryIntervalException, NoApplicationFoundException, PersistenceException {
        return this.getSuccessResponse(applicationService.listApplicationsBySalaryDemand(minimumSalary, maximumSalary));
    }

    /**
     * Returns all applications where the required education level is above or equal to the given level.
     * @param educationLevel The education level to search.
     * @return Applications that fulfill the given condition.
     * @throws NoApplicationFoundException
     * @throws PersistenceException
     */
    @GetMapping("/requiredEducationLevel/{level}")
    public ApplicationCollectionResponse getApplicationsByRequiredEducationLevel
            (@PathVariable("level") String educationLevel) throws NoApplicationFoundException, PersistenceException {
        return this.getSuccessResponse(
                applicationService.listApplicationsByRequiredEducationLevel( EducationLevel.valueOf(educationLevel) )
        );
    }

    /**
     * Returns all applications to the given company.
     * @param companyId ID of the company.
     * @return All applications to the company.
     * @throws NotExistingCompanyException
     * @throws NoApplicationFoundException
     * @throws PersistenceException
     */
    @GetMapping("/company/{id}")
    public ApplicationCollectionResponse getApplicationsByCompany (@PathVariable("id") int companyId)
            throws NotExistingCompanyException, NoApplicationFoundException, PersistenceException {
        return this.getSuccessResponse(applicationService.listApplicationsByCompany(companyId));
    }

    /**
     * Handles NoApplicationFoundException exception.
     */
    @ExceptionHandler(NoApplicationFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoApplicationFoundException () {
        ErrorResponse response =  new ErrorResponse();
        response.setSuccess(false);
        response.setError("No application found with the given conditions");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * Creates success response.
     * @param applications Collection of applications.
     * @return Success response object.
     */
    private ApplicationCollectionResponse getSuccessResponse (Collection<Application> applications) {
        ApplicationCollectionResponse response = new ApplicationCollectionResponse();
        response.setSuccess(true);

        for (Application application : applications) {
            response.getData().add(Converter.convertApplicationToDTO(application));
        }

        return response;
    }
}
