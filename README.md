# Job seeker
This repo contains my solution to the assignment of Web Application Development Course. Details of the assignment are
explained [here](https://github.com/Benczus/WEBAppDev_webShopExercise).

## Author
- Name: Kiss Áron
- Neptun code: UHV61T

## API Documentation

### Endpoints

#### Advertise new job
- URL Pattern: /api/jobs/advertise
- Method: POST
- URL Parameters: -
- Body Parameters: The body must contain a JSON string with the following fields:
    * ``companyId``: ID of the company what advertises the new job
    * ``description``: Description of the job.
    * ``minimumSalary``: Offered minimum salary.
    * ``maximumSalary``: Offered maximum salary.
    * ``requiredEducationLevel``: The required education level to fill the position (possible values are the following:
    ``"NO_PRIMARY_SCHOOL"``, ``"PRIMARY_SCHOOL"``, ``"SECONDARY_SCHOOL"``, ``"BACHELORS_DEGREE"``, ``"MASTERS_DEGREE"``, ``"DOCTORAL_DEGREE"``)
- Successful Response: [See here](#Job-advertisement)
- Error Response: [Parsing error](#Parsing-error), [Persistence error](#Persistence-error), [Malformed salary interval](#Malformed-salary-interval)
- Request example: 
    ```
    curl -H 'Content-Type:application/json' -d "{\"companyId\": 1,\"description\": \"Example job description\",\"minimumSalary\": 150000,\"maximumSalary\": 200000,\"requiredEducationLevel\":\"DOCTORAL_DEGREE\"}" -i http://localhost:8080/api/jobs/advertise
    HTTP/1.1 200
    Content-Type: application/json;charset=UTF-8
    Transfer-Encoding: chunked
    Date: Sat, 17 Nov 2018 15:38:29 GMT
    
    {"success":true,"job":{"jobId":15,"companyId":1,"description":"Example job description","minimumSalary":150000,"maximumSalary":200000,"requiredEducationLevel":"DOCTORAL_DEGREE"}}
    ```
- Notes: -

#### Read all applications
- URL Pattern: /api/applications
- Method: GET
- URL Parameters: -
- Body Parameters: -
- Successful Response: [See here](#Collection-of-applications)
- Error Response: [No application found](#No-application-found), [Persistence error](#Persistence-error)
- Request example: ``curl -i http://localhost:8080/api/applications``
- Notes: -

#### Read applications of an applicant
- URL Pattern: /api/applications/applicant/:id
- Method: GET
- URL Parameters: 
    * ``id`` (path variable): ID of the applicant
- Body Parameters: -
- Successful Response: [See here](#Collection-of-applications)
- Error Response: [No application found](#No-application-found), [Persistence error](#Persistence-error), 
[Not existing applicant](#Not-existing-applicant)
- Request example: ``curl -i http://localhost:8080/api/applications/applicant/3``
- Notes: -

#### Read applications to a job
- URL Pattern: /api/applications/job/:id
- Method: GET
- URL Parameters: 
    * ``id`` (path variable): ID of the job
- Body Parameters: -
- Successful Response: [See here](#Collection-of-applications)
- Error Response: [No application found](#No-application-found), [Persistence error](#Persistence-error),
[Not existing job](#Not-existing-job)
- Request example: ``curl -i http://localhost:8080/api/applications/job/1``
- Notes: -

#### Read applications between a salary demand interval
- URL Pattern: /api/applications/salaryDemand
- Method: GET
- URL Parameters: 
    * minimumSalary: The minimum salary demand to search.
    * maximumSalary: The maximum salary demand to search.
- Body Parameters: -
- Successful Response: [See here](#Collection-of-applications)
- Error Response: [No application found](#No-application-found), [Persistence error](#Persistence-error), 
[Malformed salary interval](#Malformed-salary-interval)
- Request example: 
- Notes: ``curl -i 'http://localhost:8080/api/applications/salaryDemand?minimumSalary=100000&maximumSalary=200000'``

#### Read applications by minimum education level
- URL Pattern: /api/applications/requiredEducationLevel/:educationLevel
- Method: GET
- URL Parameters: 
    * ``educationLevel`` (path variable): The required education level to apply for the job 
    (one of the following: ``"NO_PRIMARY_SCHOOL"``, ``"PRIMARY_SCHOOL"``, ``"SECONDARY_SCHOOL"``, ``"BACHELORS_DEGREE"``, ``"MASTERS_DEGREE"``, ``"DOCTORAL_DEGREE"``)
- Body Parameters: -
- Successful Response: [See here](#Collection-of-applications)
- Error Response: [No application found](#No-application-found), [Persistence error](#Persistence-error)
- Request example: ``http://localhost:8080/api/applications/requiredEducationLevel/BACHELORS_DEGREE``
- Notes: -

#### Read applications to a company
- URL Pattern: /api/applications/company/:id
- Method: GET
- URL Parameters: 
    * ``id`` (path variable): ID of the company
- Body Parameters: -
- Successful Response: [See here](#Collection-of-applications)
- Error Response: [No application found](#No-application-found), [Persistence error](#Persistence-error), 
[Not existing company](#Not-existing-company)
- Request example: ``curl -i http://localhost:8080/api/applications/company/1``
- Notes: -

### Successful responses

#### Job advertisement
The server responds with the following JSON when the given job is advertised successfully.
``success`` field indicates that the job is added successfully, ``job`` field contains the added job object.

```json
{
    "success": true,
    "job": {
        "jobId": 16,
        "companyId": 1,
        "description": "Example job description",
        "minimumSalary": 400000,
        "maximumSalary": 500000,
        "requiredEducationLevel": "NO_PRIMARY_SCHOOL"
    }
}
```

#### Collection of applications
The server responds with the following JSON when the client calls an endpoint of ``/api/applications``, and applications
are found.

``success`` field indicates that there are records with the given conditions in the database. 

``data`` field is an array, which contains the results of the query, in the following format: 

```json
{
    "success": true,
    "data": [
        {
            "applicationId": 1,
            "applicant": {
                "applicantId": 1,
                "username": "bela1977",
                "name": "Kovacs Bela",
                "email": "kb@example.com",
                "mobileNumber": "06301234567",
                "educationLevel": "NO_PRIMARY_SCHOOL",
                "profession": "DevOp"
            },
            "job": {
                "jobId": 1,
                "companyId": 1,
                "description": "We want employees for doing nothing",
                "minimumSalary": 100000,
                "maximumSalary": 250000,
                "requiredEducationLevel": "SECONDARY_SCHOOL"
            },
            "salaryDemand": 150000,
            "motivationLetter": "Hey Company, I rly wanna work"
        },
        {
            "applicationId": 9,
            .
            .
            .
        },
        .
        .
        .
    ]
}
```

### Error responses

Every error responses have the following structure: 
- ``success`` field have ``false`` value, indicates that the expected action ended in error
- ``error`` field is a string, which contains the error message

#### Persistence error
- Reason: Error occurred while server tried to communicate with the database.
- HTTP status code: 500 (Internal Server Error)

```json
{
    "success": false,
    "error": "Database error occured"
}
```

#### Parsing error
- Reason: Error occurred while server tried to parse the client's request.
- HTTP status code: 400 (Bad Request)

```json
{
    "success": false,
    "error": "Unable to parse input (JSON is expected)"
}
```

#### Malformed salary interval
- Reason: The client sent malformed salary interval to the server (minimum salary is greater than maximum salary).
- HTTP status code: 400 (Bad Request)

```json
{
    "success": false,
    "error": "The given salary interval is malformed"
}
```

#### No application found
- Reason: The server found no application with certain conditions.
- HTTP status code: 404 (Not Found)

```json
{
    "success": false,
    "error": "No application found with the given conditions"
}
```

#### Not existing applicant
- Reason: The server found no applicant with the given ID.
- HTTP status code: 404 (Not Found)

```json
{
    "success": false,
    "error": "No applicant exists with the given ID"
}
```

#### Not existing job
- Reason: The server found no job with the given ID.
- HTTP status code: 404 (Not Found)

```json
{
    "success": false,
    "error": "No job exists with the given ID"
}
```

#### Not existing company
- Reason: The server found no company with the given ID.
- HTTP status code: 404 (Not Found)

```json
{
    "success": false,
    "error": "No company exists with the given ID"
}
```