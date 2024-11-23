# WattWise - Electricity Billing API

WattWise is a restfull app developed with Spring Boot to manage electricity consumption billing.
It allows:
- Generate e send bills by email.
- Consult and update consumption readings.
- Manage clients information.

This API is ideal for electricity companies that need to automate their billing system

## Technologies used:
- **Java 22**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **PostgreSQL**
- **Spring Security**
- **Spring Mail**
- **Docker**

## Prerequisites
- **Java 17** or newer
- **Maven**
- **Docker**
- **PostgreSQL(docker container)**


## Installation
1. Clone the repository
    ```bash
   git clone https://github.com/eltonmessias/WattWise-backend
   cd WattWise-backend
   
2. compile the project
    ```bash
   ./mvnw clean package
   
3. configure the database in the application.properties file or via environment variables
    ```bash 
   spring.datasource.url=jdbc:postgresql://localhost:5432/mydatabase
   spring.datasource.username=myuser
   spring.datasource.password=mypassword
   
4. Execute the project
    ```bash
   java -jar target/electricity-billing.jar

5. Docker
    ```bash
   docker-compose up --build

6. Access  the API at http://localhost:8080

---

### **Documentation(SWAGGER)**

The API is documented using Swagger. You can access the interactive interface to explore and test endpoints directly in the browser.
- **Swagger documentation URL**: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

