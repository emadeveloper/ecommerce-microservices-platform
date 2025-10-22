# Application Layer

This layer contains **use cases** and **application services** that orchestrate the domain logic.

## Responsibilities
- Implement use cases
- Orchestrate domain objects
- Define DTOs for inter-layer communication
- Map between domain and DTOs
- Transaction management

## Rules
- ✅ Can use domain layer
- ✅ Can use Spring annotations for DI (@Service, @Transactional)
- ❌ Should NOT know about presentation or infrastructure details
- ❌ Should NOT have HTTP-specific logic

## Structure
- `service/`: Application services
- `dto/`: Data Transfer Objects
- `mapper/`: Domain ↔ DTO mappers
- `usecase/`: Use case interfaces (optional)