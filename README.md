Job seeker
==========
This repo contains my solution to the assignment of Web Application Development Course. Details of the assignment are
explained [here](https://github.com/Benczus/WEBAppDev_webShopExercise).

# 1. Author
- Name: Kiss Áron
- Neptun code: UHV61T

# 2.  API Documentation

## 2.1 Endpoints

### 2.1.1 Advertise new job
- URL Pattern: /api/jobs/advertise
- Method: POST
- URL Parameters: -
- Body Parameters: The body must contain a JSON string with the following fields:
    * ``companyId``: ID of the company what advertises the new job (number)
    * ``description``: Description of the job (string)
    * ``minimumSalary``: Offered minimum salary (number)
    * ``maximumSalary``: Offered maximum salary (number)
    * ``requiredEducationLevel``: The required education level to fill the position (possible values are the following:
    ``"NO_PRIMARY_SCHOOL"``, ``"PRIMARY_SCHOOL"``, ``"SECONDARY_SCHOOL"``, ``"BACHELORS_DEGREE"``, ``"MASTERS_DEGREE"``, ``"DOCTORAL_DEGREE"``)
- Successful Response: [See here](#221-Job-advertisement)
- Error Response: [Parsing error](#232-Parsing-error), [Persistence error](#231-Persistence-error), [Malformed salary interval](#233-Malformed-salary-interval)
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

### 2.1.2 Read all applications
- URL Pattern: /api/applications
- Method: GET
- URL Parameters: -
- Body Parameters: -
- Successful Response: [See here](#222-Collection-of-applications)
- Error Response: [No application found](#234-No-application-found), [Persistence error](#231-Persistence-error)
- Request example: ``curl -i http://localhost:8080/api/applications``
- Notes: -

### 2.1.3 Read applications of an applicant
- URL Pattern: /api/applications/applicant/:id
- Method: GET
- URL Parameters: 
    * ``id`` (path variable): ID of the applicant (number)
- Body Parameters: -
- Successful Response: [See here](#222-Collection-of-applications)
- Error Response: [No application found](#234-No-application-found), [Persistence error](#231-Persistence-error), 
[Not existing applicant](#235-Not-existing-applicant)
- Request example: ``curl -i http://localhost:8080/api/applications/applicant/3``
- Notes: -

### 2.1.4 Read applications to a job
- URL Pattern: /api/applications/job/:id
- Method: GET
- URL Parameters: 
    * ``id`` (path variable): ID of the job (number)
- Body Parameters: -
- Successful Response: [See here](#222-Collection-of-applications)
- Error Response: [No application found](#234-No-application-found), [Persistence error](#231-Persistence-error),
[Not existing job](#236-Not-existing-job)
- Request example: ``curl -i http://localhost:8080/api/applications/job/1``
- Notes: -

### 2.1.5 Read applications between a salary demand interval
Returns all applications, where the applicant's salary demand is between the given interval.

- URL Pattern: /api/applications/salaryDemand
- Method: GET
- URL Parameters: 
    * minimumSalary: The minimum salary demand to search (number)
    * maximumSalary: The maximum salary demand to search (number)
- Body Parameters: -
- Successful Response: [See here](#222-Collection-of-applications)
- Error Response: [No application found](#234-No-application-found), [Persistence error](#231-Persistence-error), 
[Malformed salary interval](#233-Malformed-salary-interval)
- Request example: ``curl -i 'http://localhost:8080/api/applications/salaryDemand?minimumSalary=100000&maximumSalary=200000'``
- Notes: -

### 2.1.6 Read applications by minimum education level
Returns all applications, where the job's required education level is equal or above the given level.

- URL Pattern: /api/applications/requiredEducationLevel/:educationLevel
- Method: GET
- URL Parameters: 
    * ``educationLevel`` (path variable): The required education level to apply for the job 
    (one of the following: ``"NO_PRIMARY_SCHOOL"``, ``"PRIMARY_SCHOOL"``, ``"SECONDARY_SCHOOL"``, ``"BACHELORS_DEGREE"``, ``"MASTERS_DEGREE"``, ``"DOCTORAL_DEGREE"``)
- Body Parameters: -
- Successful Response: [See here](#222-Collection-of-applications)
- Error Response: [No application found](#234-No-application-found), [Persistence error](#231-Persistence-error)
- Request example: ``curl -i http://localhost:8080/api/applications/requiredEducationLevel/BACHELORS_DEGREE``
- Notes: -

### 2.1.7 Read applications to a company
- URL Pattern: /api/applications/company/:id
- Method: GET
- URL Parameters: 
    * ``id`` (path variable): ID of the company (number)
- Body Parameters: -
- Successful Response: [See here](#222-Collection-of-applications)
- Error Response: [No application found](#234-No-application-found), [Persistence error](#231-Persistence-error), 
[Not existing company](#237-Not-existing-company)
- Request example: ``curl -i http://localhost:8080/api/applications/company/1``
- Notes: -

## 2.2 Successful responses

### 2.2.1 Job advertisement
The server responds with the following JSON when the given job is advertised successfully.

HTTP status code: 200 (OK)

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

### 2.2.2 Collection of applications
The server responds with the following JSON when the client calls an endpoint of ``/api/applications``, and applications
are found.

HTTP status code: 200 (OK)

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
                "educationLevel": "BACHELORS_DEGREE",
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

## 2.3 Error responses
Every error responses have the following structure: 
- ``success`` field have ``false`` (boolean) value, indicates that the expected action ended in error
- ``error`` field is a string, which contains the error message

### 2.3.1 Persistence error
- Reason: Error occurred while server tried to communicate with the database.
- HTTP status code: 500 (Internal Server Error)

```json
{
    "success": false,
    "error": "Database error occured"
}
```

###  2.3.2 Parsing error
- Reason: Error occurred while server tried to parse the client's request.
- HTTP status code: 400 (Bad Request)

```json
{
    "success": false,
    "error": "Unable to parse input (JSON is expected)"
}
```

###  2.3.3 Malformed salary interval
- Reason: The client sent malformed salary interval to the server (minimum salary is greater than maximum salary).
- HTTP status code: 400 (Bad Request)

```json
{
    "success": false,
    "error": "The given salary interval is malformed"
}
```

###  2.3.4 No application found
- Reason: The server found no application with certain conditions.
- HTTP status code: 404 (Not Found)

```json
{
    "success": false,
    "error": "No application found with the given conditions"
}
```

### 2.3.5 Not existing applicant
- Reason: The server found no applicant with the given ID.
- HTTP status code: 404 (Not Found)

```json
{
    "success": false,
    "error": "No applicant exists with the given ID"
}
```

### 2.3.6 Not existing job
- Reason: The server found no job with the given ID.
- HTTP status code: 404 (Not Found)

```json
{
    "success": false,
    "error": "No job exists with the given ID"
}
```

### 2.3.7 Not existing company
- Reason: The server found no company with the given ID.
- HTTP status code: 404 (Not Found)

```json
{
    "success": false,
    "error": "No company exists with the given ID"
}
```