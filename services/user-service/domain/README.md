# Domain Layer

This layer contains the **core business logic** and is **independent of any framework or external library**.

## Responsibilities
- Define business entities and value objects
- Define business rules and validations
- Define repository interfaces (ports)
- Define domain exceptions

## Rules
- ❌ NO Spring annotations
- ❌ NO database annotations (@Entity, @Table, etc.)
- ❌ NO external dependencies
- ✅ Pure Java
- ✅ Business logic only

## Structure
- `model/`: Domain entities (User, Order, etc.)
- `repository/`: Repository interfaces
- `exception/`: Business exceptions
- `valueobject/`: Value objects (Email, Money, etc.)