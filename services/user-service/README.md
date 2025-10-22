# User Service

Microservice responsible for user management and authentication.

## Responsibilities

- User registration
- User profile management
- User authentication (JWT)
- User status management (active, inactive, deleted)

## Architecture

This service follows **Hexagonal Architecture (Ports & Adapters)** with Clean Architecture principles.

### Layers
- **Domain**: Pure business logic
- **Application**: Use cases and orchestration
- **Infrastructure**: Technical implementations (JPA, PostgreSQL)
- **Presentation**: REST API controllers

## Tech Stack

- Java 17
- Spring Boot 3.2.x
- PostgreSQL
- Spring Data JPA
- Lombok
- MapStruct (coming soon)

## Running locally

### Prerequisites
- Java 17+
- Docker
- Maven

### Steps
```bash
# Start PostgreSQL
docker-compose up -d postgres

# Run application
./mvnw spring-boot:run
```

## 🧪 Testing
```bash
# Unit tests
./mvnw test

# Integration tests
./mvnw verify
```

## 📝 API Documentation

Once running, access Swagger UI at: `http://localhost:8080/swagger-ui.html`

## 🔗 Endpoints

### Users
- `POST /api/v1/users` - Create user
- `GET /api/v1/users/{id}` - Get user by ID
- `PUT /api/v1/users/{id}` - Update user
- `DELETE /api/v1/users/{id}` - Delete user
- `GET /api/v1/users` - List users (paginated)

## 🗄️ Database Schema
```sql
CREATE TABLE users (
    id UUID PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    phone_number VARCHAR(20),
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);
```

## 📊 Metrics & Health

- Health: `http://localhost:8080/actuator/health`
- Metrics: `http://localhost:8080/actuator/metrics`