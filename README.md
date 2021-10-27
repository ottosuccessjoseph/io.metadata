# School Registration System

The system was designed with microservice architecture in mind. The system comprises of four microservices namely:

- API Gateway
- Identity Service
- Student Service
- Course Service

## API Gateway

The gateway is the entry point to access all services. Requests are routed to the downstream systems as configured. We have routes that are secure and routes that could be accessed by everyone.

```bash
# no end point required
```

## Identity Service

The user management, authentication and authorization of the system is handled by this service. A user can either register as a student or an admin as this determines their usage and accessibility of various end points.

| Name | Verb | End Point | Payload |
| ------------ | ------------- | -------- | ------- |
| SIGNUP | POST  | {api-gateway}/identity/auth/signup | ``` { "username": "student","password": "student","first_name": "Success","last_name": "Otto","email": "student@test.com","role": "STUDENT"} ```
| LOGIN | POST  | {api-gateway}/auth/login | ``` {"username": "student", "password": "student" }```

## Student Service

This service handles students CRUD operations

| Name | Verb | End Point | Payload |
| ------------ | ------------- | -------- | ------- |
| CREATE | POST | {api-gateway}/students | ```{ "first_name": "Success","last_name": "Otto","email": "otto@example.com","username": "otto"} ```
| READ | GET | {api-gateway}/students/{reference} | |
| UPDATE | PUT | {api-gateway/students/{reference} | ```{  "first_name": "test","last_name": "example"} ``` |
| DELETE | DELETE | {api-gateway/students/{reference} | |


## Course Service

The course service is a register for students courses and all courses available. Admin role is required
to consume the filter apis. Two roles in the system are ```STUDENT``` and ```ADMIN```

| Name | Verb | End Point | Payload | Header |
| ------------ | ------------- | -------- | ------- | -----|
| CREATE | POST | {api-gateway}/courses | ``` { "name": "Further Mathematics" } ``` | |
| READ | GET | {api-gateway}/courses/{reference} | | |
| UPDATE | PUT | {api-gateway}/courses/{reference} | ```{ "name": "Further Mathematics" } ``` | |
|REGISTER STUDENT | PUT | {api-gateway}/courses/{reference}/{student_reference} | | |
| FILTER STUDENT BY SPECIFIC COURSE | GET | {api-gateway}/courses/{reference}/students | | Authorization required:  ``` Bearer {token}``` |
| FILTER BY SPECIFIC STUDENT | GET | {api-gateway}/courses/students/{reference} | | Authorization required:  ``` Bearer {token}``` |
| FILTER BY NO STUDENT | GET | {api-gateway}/courses | | Authorization required:  ``` Bearer {token}```|

## INSTALLATION
 The application can be executed in one of two ways:
 
- Docker composer
- Locally running through maven

### Docker Composer
 Git clone https://github.com/ottosuccessjoseph/io.metadata.git. Go into each services folder and run
 ``` mvn clean install``` to generate the jar file and 
```docker build -t [service_name] ``` to build the docker images
 
After generating the docker images and jar files of each services. navigate to 
the folder of docker compose.yml and enter
``` docker-compose up --build ```

### Locally Running through maven

 Another way is to locally run it through your IDE of choice. Also, there're postman
collections in the repository that can be used to test the end points



## Note: 
I was given the task late monday and was rushed to finish in one day. Tried to cover up in two days
