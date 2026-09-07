# Post API — Experiment 5 (Spring Boot + MongoDB)

Companion project for "Experiment 5 - Spring Boot REST API Design & Exception Handling."
Covers Experiment 5.1 (REST API design) and Experiment 5.2 (exception handling & logging)
as one running example.

## Requirements

- Java 17+
- Maven 3.8+
- A running MongoDB instance (local, or a MongoDB Atlas connection string)

## Setup

1. Open `src/main/resources/application.properties` and set your MongoDB connection:

   ```
   spring.data.mongodb.uri=mongodb://localhost:27017/postsdb
   ```

   For MongoDB Atlas, replace it with your Atlas URI instead.

2. Build and run:

   ```
   mvn spring-boot:run
   ```

   The API starts on `http://localhost:8080`.

## Endpoints

| Method | Path                          | Description              |
|--------|-------------------------------|---------------------------|
| POST   | /api/posts                    | Create a post             |
| GET    | /api/posts                    | List all posts            |
| GET    | /api/posts/{id}                | Get a post by ID          |
| PUT    | /api/posts/{id}                | Update a post              |
| DELETE | /api/posts/{id}                | Delete a post              |
| GET    | /api/posts/search?keyword=...  | Search posts by content    |

Every response — success or error — is wrapped in the same shape:

```json
{ "status": "success", "message": "...", "data": { ... } }
```

## Quick test

```bash
# Create a post
curl -X POST http://localhost:8080/api/posts \
  -H "Content-Type: application/json" \
  -d '{"content": "My first post"}'

# Trigger a validation error
curl -X POST http://localhost:8080/api/posts \
  -H "Content-Type: application/json" \
  -d '{"content": ""}'

# Trigger a not-found error
curl http://localhost:8080/api/posts/000000000000000000000000
```

Check the console output after each call — every log line for a given request
carries the same correlation ID (also returned as the `X-Correlation-Id` response header).

## Project layout

```
src/main/java/com/example/postapi/
├── PostapiApplication.java     main() class
├── model/Post.java             MongoDB document
├── repository/PostRepository.java
├── dto/PostRequest.java        request DTO + Bean Validation
├── dto/ApiResponse.java        standardized response wrapper
├── service/PostService.java    business logic
├── controller/PostController.java   REST endpoints (CRUD + search)
├── exception/ResourceNotFoundException.java
├── exception/GlobalExceptionHandler.java   @RestControllerAdvice
├── filter/LoggingFilter.java     request timing (servlet level)
├── interceptor/CorrelationInterceptor.java  correlation ID (MVC level)
└── config/
    ├── WebConfig.java           global CORS
    └── InterceptorConfig.java   registers CorrelationInterceptor
```
