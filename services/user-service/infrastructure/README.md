# Infrastructure Layer

This layer contains **technical implementations** of interfaces defined in the domain layer.

## Responsibilities
- Implement repository interfaces using JPA
- Configure external dependencies (DB, Kafka, etc.)
- Define JPA entities
- Handle technical exceptions

## Rules
- ✅ Can use domain and application layers
- ✅ Framework-specific code (Spring Data, JPA, etc.)
- ✅ Database-specific implementations
- ❌ Should NOT contain business logic

## Structure
- `persistence/`: Database implementations
    - `entity/`: JPA entities
    - `repository/`: JPA repository implementations
    - `mapper/`: Entity ↔ Domain mappers
- `config/`: Configuration classes