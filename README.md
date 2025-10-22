# 🛒 E-Commerce Microservices Platform

Full-featured e-commerce platform built with microservices architecture, showcasing modern backend development practices.

## Architecture Overview

This project implements a microservices architecture with the following services:

- **User Service**: User management and authentication
- **Product Service**: Product catalog and inventory
- **Order Service**: Order processing and management
- **Payment Service**: Payment processing integration
- **Notification Service**: Email and notification handling
- **API Gateway**: Entry point and request routing

## Tech Stack

### Backend
- **Language**: Java 17
- **Framework**: Spring Boot 3.2.x
- **Build Tool**: Maven

### Databases
- **PostgreSQL**: User and Order data
- **MongoDB**: Product catalog
- **Redis**: Caching and sessions

### Message Broker
- **Apache Kafka**: Event-driven communication

### Infrastructure
- **Docker**: Containerization
- **Kubernetes**: Orchestration
- **AWS**: Cloud deployment (ECS/EKS)

### Observability
- **Grafana**: Metrics visualization
- **Prometheus**: Metrics collection
- **Loki**: Log aggregation

### CI/CD
- **GitHub Actions**: Automated testing and deployment

## Architecture Principles

- **Clean Architecture / Hexagonal Architecture**
- **Domain-Driven Design (DDD)**
- **SOLID Principles**
- **Event-Driven Architecture**
- **API-First Design**

## Getting Started

### Prerequisites
- Java 17+
- Docker Desktop
- Maven 3.8+

### Quick Start
```bash
# Clone repository
git clone https://github.com/emadeveloper/ecommerce-microservices-platform.git

# Start infrastructure
docker-compose up -d

# Run services (instructions per service in their README)
```

## Documentation

- [Architecture Decision Records](docs/architecture/)
- [API Documentation](docs/api/)
- [Setup Guide](docs/setup/)

## Testing Strategy

- Unit Tests (JUnit 5 + Mockito)
- Integration Tests (TestContainers)
- E2E Tests (REST Assured)
- Contract Tests (Spring Cloud Contract)

## Contributing

This is a portfolio project. Feel free to explore and learn from it!

## 📄 License

MIT

---

**Author**: Emanuel Martínez 
**Portfolio**: https://portfolio-emanuel-martinez-1.netlify.app/
**LinkedIn**: www.linkedin.com/in/emanuel-david-martinez
