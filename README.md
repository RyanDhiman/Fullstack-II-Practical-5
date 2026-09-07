# Spring Boot REST API – Experiments 2.1.1 & 2.1.2

A Spring Boot REST API project developed for the Full Stack practical experiments. The project demonstrates RESTful API design, MongoDB integration, validation, standardized responses, global exception handling, structured logging, and correlation IDs.

---

## Experiments Covered

### Experiment 2.1.1 – Spring Boot REST API Design

This experiment focuses on designing and implementing RESTful APIs using Spring Boot with:

- RESTful CRUD operations
- MongoDB database integration
- Bean Validation
- Standardized API responses
- Search functionality
- CORS configuration
- Layered architecture using Controller, Service, and Repository layers

### Experiment 2.1.2 – Global Exception Handling & Structured Logging

This experiment extends the REST API with:

- Global exception handling using `@RestControllerAdvice`
- Custom exceptions
- Validation error handling
- Request logging
- Request execution-time logging
- HTTP status logging
- Correlation IDs
- MDC-based request tracking
- `X-Correlation-Id` response headers

---

## Technologies Used

- Java 17+
- Spring Boot 3.3.0
- Spring Data MongoDB
- MongoDB
- Maven
- Jakarta Bean Validation
- SLF4J / Logback
- Postman
- IntelliJ IDEA

---

## Requirements

- Java 17 or higher
- Maven 3.8+
- MongoDB running locally or MongoDB Atlas
- Postman for API testing
- IntelliJ IDEA or any Java IDE

---

## Project Structure

```text
src/
├── main/
│   ├── java/
│   │   └── com/example/postapi/
│   │       ├── config/
│   │       │   ├── InterceptorConfig.java
│   │       │   └── WebConfig.java
│   │       │
│   │       ├── controller/
│   │       │   └── PostController.java
│   │       │
│   │       ├── dto/
│   │       │   ├── ApiResponse.java
│   │       │   └── PostRequest.java
│   │       │
│   │       ├── exception/
│   │       │   ├── GlobalExceptionHandler.java
│   │       │   └── ResourceNotFoundException.java
│   │       │
│   │       ├── filter/
│   │       │   └── LoggingFilter.java
│   │       │
│   │       ├── interceptor/
│   │       │   └── CorrelationInterceptor.java
│   │       │
│   │       ├── model/
│   │       │   └── Post.java
│   │       │
│   │       ├── repository/
│   │       │   └── PostRepository.java
│   │       │
│   │       ├── service/
│   │       │   └── PostService.java
│   │       │
│   │       └── PostapiApplication.java
│   │
│   └── resources/
│       └── application.properties
│
└── test/
    └── java/
        └── com/example/postapi/
            └── PostapiApplicationTests.java
