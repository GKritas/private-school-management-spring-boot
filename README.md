# Private School Management App
This is a simple REST API for a private school management app connected with MySQL database. The application has four main entities: Course, Student, Trainer, and Assignments. Each entity has its own repository, service, and controller to manage CRUD operations.

## Getting Started
1. Clone the repository

  `$ git clone https://github.com/GKritas/private-school-management-spring-boot.git`

2. Create a new MySQL database named private-school-db:

  `$ mysql -u <username> -p`
  
  `$ CREATE DATABASE private-school-db;`

3. Update application.properties with your MySQL database credentials:
  
  `spring.datasource.url=jdbc:mysql://localhost:3306/private-school-db`
  
  `spring.datasource.username=<your-username>`
  
  `spring.datasource.password=<your-password>`

4. Build and run the application using Maven:

  `$ cd private-school-management-spring-boot`
  
  `$ mvn spring-boot:run`

5. Use an API testing tool (e.g., Postman) to test the endpoints.


## Endpoints
### Course
- GET /api/courses: Retrieve all courses
- GET /api/courses/{id}: Retrieve a course by ID
- POST /api/courses: Create a new course
- PUT /api/courses/{id}: Update a course by ID
- DELETE /api/courses/{id}: Delete a course by ID

### Student
- GET /api/students: Retrieve all students
- GET /api/students/{id}: Retrieve a student by ID
- POST /api/students: Create a new student
- PUT /api/students/{id}: Update a student by ID
- DELETE /api/students/{id}: Delete a student by ID

### Trainer
- GET /api/trainers: Retrieve all trainers
- GET /api/trainers/{id}: Retrieve a trainer by ID
- POST /api/trainers: Create a new trainer
- PUT /api/trainers/{id}: Update a trainer by ID
- DELETE /api/trainers/{id}: Delete a trainer by ID

### Assignments
- GET /api/assignments: Retrieve all assignments
- GET /api/assignments/{id}: Retrieve an assignment by ID
- POST /api/assignments: Create a new assignment
- PUT /api/assignments/{id}: Update an assignment by ID
- DELETE /api/assignments/{id}: Delete an assignment by ID


## Built With
- Spring Boot
- Spring Data JPA
- MySQL
