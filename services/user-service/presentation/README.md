# Presentation Layer

This layer contains **REST API controllers** and handles HTTP communication.

## Responsibilities
- Expose REST endpoints
- Validate HTTP requests
- Handle HTTP-specific concerns (status codes, headers, etc.)
- Map between HTTP DTOs and application DTOs
- Global exception handling

## Rules
- ✅ Can use application layer
- ✅ HTTP-specific code (Controllers, @RestController, etc.)
- ✅ Validation annotations (@Valid, @NotNull, etc.)
- ❌ Should NOT contain business logic
- ❌ Should NOT access infrastructure directly

## Structure
- `controller/`: REST controllers
- `dto/`: HTTP-specific DTOs
- `mapper/`: HTTP DTO ↔ Application DTO mappers
- `exception/`: Global exception handler