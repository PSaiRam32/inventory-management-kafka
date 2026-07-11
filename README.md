# 📦 Inventory Management System using Apache Kafka

<div align="center">

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.1-brightgreen?style=for-the-badge)
![Gradle](https://img.shields.io/badge/Gradle-Build-blue?style=for-the-badge)
![Apache Kafka](https://img.shields.io/badge/Apache-Kafka-black?style=for-the-badge)
![MySQL](https://img.shields.io/badge/MySQL-Database-blue?style=for-the-badge)
![Kafka Consumer](https://img.shields.io/badge/Kafka-Consumer-success?style=for-the-badge)
![DLT](https://img.shields.io/badge/Dead%20Letter-Topic-red?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Active-success?style=for-the-badge)

</div>

---

# 📖 Overview

The **Inventory Service** is an enterprise-grade **Kafka Consumer Microservice** responsible for maintaining product inventory by consuming product events published by the Product Service.

Unlike a traditional REST-based application, this service follows an **Event-Driven Architecture** where business events are asynchronously consumed from Apache Kafka.

The service ensures reliable processing through:

- Idempotent Consumer Pattern
- Dead Letter Topic (DLT)
- Retry Mechanism
- Centralized Exception Handling
- Processed Event Tracking
- Kafka Error Handling

The Inventory Service is designed to demonstrate production-ready Kafka consumer patterns commonly adopted in enterprise microservice ecosystems.

---

# 🎯 Business Problem

In modern distributed systems, inventory must remain synchronized with product changes occurring across multiple services.

Whenever a product is:

- Created
- Updated
- Deleted

the inventory system must immediately reflect those changes.

If inventory updates rely on synchronous REST communication, several issues arise:

- Tight coupling
- Reduced scalability
- Single point of failure
- Poor fault tolerance
- Reduced availability

---

# 💡 Business Solution

Instead of direct service-to-service communication, the Product Service publishes business events to Apache Kafka.

The Inventory Service subscribes to these events and processes them asynchronously.

This approach provides:

- Loose coupling
- Independent deployment
- High scalability
- Better reliability
- Fault tolerance
- Event replay capability

---

# 🏢 Enterprise Concepts Demonstrated

This project demonstrates several enterprise backend engineering concepts including:

- Event-Driven Architecture
- Apache Kafka Consumer
- Consumer Groups
- Idempotent Consumer Pattern
- Processed Event Tracking
- Retry Strategy
- Dead Letter Topic (DLT)
- Global Exception Handling
- Custom Kafka Error Handler
- Spring Data JPA
- Layered Architecture
- DTO Pattern
- Repository Pattern
- Service Layer Pattern

---

# 🎯 Project Objectives

The primary objectives of this service are:

- Consume Product Events
- Maintain Inventory
- Prevent Duplicate Processing
- Handle Kafka Failures Gracefully
- Support Reliable Message Processing
- Demonstrate Enterprise Kafka Consumer Patterns
- Improve Fault Tolerance
- Enable Event Replay

---

# ✨ Features

## Kafka Consumer

- Product Event Consumer
- JSON Event Deserialization
- Consumer Groups
- Topic Subscription
- Offset Management

---

## Inventory Management

- Inventory Creation
- Inventory Updates
- Inventory Synchronization
- Product Availability Tracking

---

## Reliability

- Retry Mechanism
- Dead Letter Topic (DLT)
- Kafka Error Handler
- Idempotent Processing
- Duplicate Event Detection

---

## Exception Handling

- Retryable Exceptions
- Non-Retryable Exceptions
- Global Exception Handling
- Failure Logging

---

## Persistence

- Inventory Table
- Processed Event Table
- MySQL Integration
- Spring Data JPA

---

# 🛠 Technology Stack

| Category | Technology |
|------------|------------|
| Language | Java 21 |
| Framework | Spring Boot 4.1 |
| Build Tool | Gradle |
| Database | MySQL |
| ORM | Spring Data JPA |
| Messaging | Apache Kafka |
| Serialization | Jackson |
| Validation | Jakarta Validation |
| Testing | JUnit |

---

# 🏛 High-Level Architecture

```text
                Product Service

                       │

               Product Events

                       │

                       ▼

               Apache Kafka

                       │

                       ▼

             Inventory Service

                       │

          Kafka Consumer Listener

                       │

                       ▼

             Inventory Service

             Business Logic

                │

      ┌─────────┴──────────┐

      ▼                    ▼

Inventory Table     Processed Event Table
```

---

# 🏗 Consumer Architecture

```text
                 Kafka Topic

                      │

                      ▼

              Kafka Consumer

                      │

             Event Validation

                      │

        Duplicate Event Check

                      │

        ┌─────────────┴─────────────┐

        ▼                           ▼

Already Processed           New Event

        │                           │

 Ignore Event              Process Inventory

                                    │

                                    ▼

                         Save Processed Event

                                    │

                                    ▼

                             Commit Offset
```

---
# 📂 Project Structure

```text
inventory-service
│
├── gradle/
│
├── src
│   ├── main
│   │
│   ├── java
│   │   └── com
│   │       └── kafka
│   │           └── inventory_service
│   │
│   │               ├── Messaging
│   │               │   ├── config
│   │               │   ├── consumer
│   │               │   └── event
│   │               │
│   │               ├── entity
│   │               │   ├── Inventory.java
│   │               │   ├── InventoryStatus.java
│   │               │   ├── ProcessedEvent.java
│   │               │   └── ProductStatus.java
│   │               │
│   │               ├── exception
│   │               │   ├── RetryableException.java
│   │               │   └── NonRetryableException.java
│   │               │
│   │               ├── repository
│   │               │   ├── InventoryRepository.java
│   │               │   └── ProcessedEventRepository.java
│   │               │
│   │               ├── service
│   │               │   ├── InventoryService.java
│   │               │   ├── InventoryServiceImpl.java
│   │               │   ├── ProcessedEventService.java
│   │               │   └── ProcessedEventServiceImpl.java
│   │               │
│   │               └── InventoryServiceApplication.java
│   │
│   ├── resources
│   │   └── application.yml
│   │
│   └── test
│
├── build.gradle
├── .gitignore
└── README.md
```

---

# 📦 Package Responsibilities

| Package | Responsibility |
|----------|---------------|
| **Messaging.config** | Kafka Consumer configuration, DLT configuration, Error Handler configuration and Kafka-related beans. |
| **Messaging.consumer** | Consumes Product Events from Apache Kafka and delegates processing to the service layer. |
| **Messaging.event** | Contains Kafka Event Models used for communication between Product Service and Inventory Service. |
| **entity** | JPA entities representing Inventory and Processed Event tables along with domain enums. |
| **exception** | Custom business exceptions categorized as Retryable and Non-Retryable to support Kafka retry and DLT handling. |
| **repository** | Spring Data JPA repositories responsible for Inventory persistence and duplicate event tracking. |
| **service** | Contains business logic for inventory synchronization, processed event management, and event handling. |
| **resources** | Stores application configuration including Spring Boot, MySQL, Kafka Consumer, and Profile configurations. |
| **test** | Unit and integration tests validating business logic, Kafka consumers, and repository behavior. |
| **InventoryServiceApplication** | Main Spring Boot application entry point responsible for bootstrapping the Inventory Service. |

---

# 📌 Layered Architecture

```text
                 Apache Kafka
                      │
                      ▼
          Messaging Consumer Layer
                      │
                      ▼
               Service Layer
                      │
          ┌───────────┴───────────┐
          ▼                       ▼
 Inventory Repository     Processed Event Repository
          │                       │
          ▼                       ▼
      Inventory Table     Processed Events Table
```

---

# 📚 Package Overview

The Inventory Service follows a clean **Layered Architecture** where each package has a single responsibility.

- **Messaging** handles all Kafka-related communication, including event consumption and configuration.
- **Service** contains the complete business logic for inventory synchronization.
- **Repository** abstracts all database operations using Spring Data JPA.
- **Entity** represents the database schema.
- **Exception** provides custom exception types to distinguish retryable failures from permanent failures.
- **Resources** centralizes all application configuration using Spring Profiles.
- **Test** contains automated tests to verify application correctness.

This separation of concerns improves maintainability, scalability, and testability while following enterprise application development best practices.

---
---

# ⭐ Key Highlights

✅ Event-Driven Architecture

✅ Apache Kafka Consumer

✅ Idempotent Consumer Pattern

✅ Dead Letter Topic

✅ Retry Mechanism

✅ Kafka Error Handling

✅ Processed Event Tracking

✅ Enterprise Layered Architecture

✅ Spring Boot 4.1

✅ MySQL Integration

---

# 🏗️ Application Architecture

The Inventory Service follows a **Layered Architecture** combined with an **Event-Driven Architecture**.

Instead of exposing REST endpoints for business operations, the service consumes events asynchronously from Apache Kafka.

```text
                    +----------------------+
                    |    Product Service   |
                    +----------+-----------+
                               |
                               |
                      Product Events
                               |
                               ▼
                    +----------------------+
                    |     Apache Kafka     |
                    +----------+-----------+
                               |
                               ▼
                    +----------------------+
                    |   Kafka Consumer     |
                    +----------+-----------+
                               |
                               ▼
                    +----------------------+
                    | Event Validation     |
                    +----------+-----------+
                               |
                               ▼
                    +----------------------+
                    | Duplicate Detection  |
                    +----------+-----------+
                               |
                +--------------+--------------+
                |                             |
                ▼                             ▼
      Already Processed              New Event
                |                             |
                ▼                             ▼
        Ignore Event               Business Logic
                                              |
                                              ▼
                              +----------------------------+
                              | Inventory Repository       |
                              +----------------------------+
                                              |
                                              ▼
                              +----------------------------+
                              | ProcessedEvent Repository  |
                              +----------------------------+
```

---

# 📖 Layer Responsibilities

## 1️⃣ Kafka Consumer Layer

Acts as the entry point of the Inventory Service.

Responsibilities

- Consume Product Events
- Deserialize JSON Events
- Receive Kafka Messages
- Delegate processing to Service Layer

The consumer should never contain business logic.

---

## 2️⃣ Service Layer

Contains the complete business logic.

Responsibilities include

- Validate Product Events
- Update Inventory
- Save Inventory Records
- Prevent Duplicate Processing
- Track Processed Events
- Handle Business Exceptions

---

## 3️⃣ Repository Layer

Responsible for communicating with MySQL.

Responsibilities include

- Inventory CRUD Operations
- Processed Event Persistence
- Event Lookup
- Duplicate Detection

---

## 4️⃣ Entity Layer

Contains JPA entities.

Current entities include

### Inventory

Stores inventory details for products.

Responsibilities

- Product Inventory
- Quantity Management
- Inventory Status

---

### ProcessedEvent

Stores processed Kafka events.

Responsibilities

- Prevent Duplicate Processing
- Track Processed Event IDs
- Enable Idempotent Consumer Pattern

---

# 🔄 Event Processing Lifecycle

Whenever the Product Service publishes an event, the following sequence occurs.

```text
Product Service

      │

      ▼

Kafka Topic

      │

      ▼

Inventory Consumer

      │

      ▼

Deserialize Event

      │

      ▼

Validate Event

      │

      ▼

Check ProcessedEvent Table

      │

 ┌────┴────┐

 │         │

 ▼         ▼

Duplicate  New Event

 │          │

 │          ▼

 │   Update Inventory

 │          │

 │          ▼

 │   Save Processed Event

 │          │

 └──────────┴──────────► Commit Offset
```

---

# 📦 Kafka Consumer Flow

```text
Kafka Broker

      │

      ▼

Consumer Group

      │

      ▼

Kafka Listener

      │

      ▼

Inventory Consumer

      │

      ▼

Inventory Service

      │

      ▼

Database
```

---

# 📨 Product Event Flow

```text
Product Created

        │

        ▼

Kafka Topic

        │

        ▼

Inventory Consumer

        │

        ▼

Validate Event

        │

        ▼

Update Inventory

        │

        ▼

Persist Inventory

        │

        ▼

Mark Event Processed
```

The same workflow applies for

- Product Updated
- Product Deleted

---

# 🛡️ Idempotent Consumer Pattern

Kafka guarantees **at-least-once delivery**.

This means a consumer may receive the same event more than once.

Without duplicate detection:

```text
Event Received

↓

Inventory Updated

↓

Consumer Crash

↓

Kafka Redelivers Event

↓

Inventory Updated Again ❌
```

This creates inconsistent inventory.

---

# ✅ Duplicate Detection

To prevent duplicate processing, every successfully processed event is stored in the **ProcessedEvent** table.

Workflow

```text
Receive Event

        │

        ▼

Check ProcessedEvent

        │

 ┌──────┴──────┐

 │             │

 ▼             ▼

Exists      Doesn't Exist

 │             │

 ▼             ▼

Ignore     Process Event

               │

               ▼

 Save Processed Event
```

This guarantees each business event is processed only once.

---

# 🔁 Retry Strategy

Some failures are temporary.

Examples

- Database unavailable
- Kafka timeout
- Network interruption

These failures should trigger automatic retries.

Workflow

```text
Consume Event

      │

      ▼

Processing Failed

      │

      ▼

Retry

      │

 ┌────┴────┐

 │         │

 ▼         ▼

Success   Failed

 │          │

 ▼          ▼

Commit   Send to DLT
```

---

# ☠️ Dead Letter Topic (DLT)

If an event continues to fail after all retry attempts, it is forwarded to a **Dead Letter Topic**.

Purpose

- Prevent blocking the consumer
- Preserve failed messages
- Support manual investigation
- Enable future replay

Workflow

```text
Kafka Event

      │

      ▼

Consumer

      │

      ▼

Retry

      │

      ▼

Still Failed

      │

      ▼

Dead Letter Topic

      │

      ▼

DLT Consumer

      │

      ▼

Logging / Investigation
```

---

# 🚨 Error Handling Strategy

The Inventory Service categorizes failures into two types.

## Retryable Exceptions

Temporary failures that can succeed later.

Examples

- Database Connection Failure
- Kafka Timeout
- Network Failure

These are retried automatically.

---

## Non-Retryable Exceptions

Permanent failures.

Examples

- Invalid Event
- Missing Required Fields
- Unsupported Event Type
- Invalid Payload

These are immediately routed to the Dead Letter Topic.

---

# 📊 Sequence Diagram

```text
Product Service

      │

Publish Event

      │

      ▼

    Kafka

      │

      ▼

Inventory Consumer

      │

Validate Event

      │

Check Duplicate

      │

Update Inventory

      │

Save Processed Event

      │

Commit Offset

      │

Finished
```

---

# 🚀 Benefits of This Architecture

- Loose Coupling
- Fault Tolerance
- Reliable Event Processing
- Event Replay
- Duplicate Prevention
- Retry Support
- Dead Letter Queue
- Consumer Scalability
- Independent Deployment
- High Availability

---
---

# 🗄️ Database Design

The Inventory Service uses **MySQL** as the primary relational database to maintain inventory information and support the **Idempotent Consumer Pattern**.

The application currently maintains two core tables:

- **inventory** – Stores product inventory details.
- **processed_events** – Stores processed Kafka event IDs to prevent duplicate message processing.

---

# 📦 Inventory Table (`inventory`)

The `inventory` table stores the current inventory state for every product.

| Column | Type | Description |
|---------|------|-------------|
| `id` | BIGINT | Primary Key. Auto-generated inventory identifier. |
| `product_id` | BIGINT | Unique Product Identifier received from Product Service. |
| `sku` | VARCHAR | Unique Stock Keeping Unit (SKU). |
| `product_name` | VARCHAR | Product name. |
| `available_quantity` | INTEGER | Quantity currently available for sale. |
| `reserved_quantity` | INTEGER | Quantity reserved for pending orders. |
| `warehouse` | VARCHAR | Warehouse where the inventory is maintained. |
| `status` | ENUM (STRING) | Current inventory status. |
| `created_at` | TIMESTAMP | Automatically generated creation timestamp. |
| `updated_at` | TIMESTAMP | Automatically updated modification timestamp. |

### Constraints

- Primary Key on `id`
- Unique Constraint on `productId`
- Unique Constraint on `sku`
- Product Name is mandatory
- Available Quantity is mandatory
- Reserved Quantity is mandatory
- Warehouse is mandatory
- Inventory Status is mandatory

### Automatic Audit Fields

The entity uses Hibernate auditing annotations.

- `@CreationTimestamp`
  - Automatically sets `createdAt` when the inventory record is created.

- `@UpdateTimestamp`
  - Automatically updates `updatedAt` whenever the inventory record is modified.

---

# ✅ Processed Events Table (`processed_events`)

The `processed_events` table implements the **Idempotent Consumer Pattern**.

Every successfully processed Kafka event is stored in this table to ensure duplicate events are ignored.

| Column | Type | Description |
|---------|------|-------------|
| `id` | BIGINT | Primary Key. |
| `event_id` | VARCHAR | Unique Kafka Event Identifier. |
| `processed_at` | TIMESTAMP | Timestamp when the event was successfully processed. |

### Constraints

- Primary Key on `id`
- Unique Constraint on `eventId`

---

# 🗂️ Database Relationship

```text
+--------------------------------------------------------+
|                     INVENTORY                          |
+--------------------------------------------------------+
| id (PK)                                                |
| product_id (UNIQUE)                                    |
| sku (UNIQUE)                                           |
| product_name                                           |
| available_quantity                                     |
| reserved_quantity                                      |
| warehouse                                              |
| status                                                 |
| created_at                                             |
| updated_at                                             |
+--------------------------------------------------------+

                     ▲

                     │ Updated By

                     │

             Kafka Product Events

                     │

                     ▼

+----------------------------------------------+
|            PROCESSED_EVENTS                  |
+----------------------------------------------+
| id (PK)                                      |
| event_id (UNIQUE)                            |
| processed_at                                 |
+----------------------------------------------+
```

---

# 🔧 Kafka Consumer Configuration

The Inventory Service consumes Product Events using Apache Kafka.

### Consumer Configuration

| Property | Value |
|----------|-------|
| Bootstrap Servers | `localhost:9092, localhost:9094` |
| Consumer Group | `inventory-service-group` |
| Key Deserializer | `StringDeserializer` |
| Auto Offset Reset | `earliest` |
| Auto Commit | `false` |
| Max Poll Records | `10` |
| Max Poll Interval | `300000 ms` |
| Session Timeout | `10000 ms` |
| Heartbeat Interval | `3000 ms` |
| Trusted Packages | `*` |

### Consumer Reliability

The consumer is configured to improve reliability by using:

- Manual Offset Management
- Earliest Offset Recovery
- Controlled Poll Size
- Consumer Group Coordination
- Session Monitoring
- Heartbeat Management

---

# 🌍 Spring Profiles

The application supports multiple runtime environments.

| Profile | Purpose | Logging |
|---------|---------|---------|
| `local` | Local Development | INFO |
| `cut` | Component Unit Testing | INFO |
| `ete` | End-to-End Testing | INFO |
| `drt` | Development Regression Testing | INFO |
| `test` | Automated Testing | DEBUG |
| `prod` | Production | WARN |

### Test Profile

The `test` profile is optimized for automated testing.

- `ddl-auto=create-drop`
- SQL logging enabled
- DEBUG logging enabled

### Production Profile

The `prod` profile is optimized for production deployments.

- Existing schema validation
- SQL logging disabled
- WARN level logging

---

# 🚨 Exception Handling Strategy

The Inventory Service categorizes failures into two types.

## Retryable Exceptions

Retryable exceptions represent temporary failures that may succeed when retried.

Examples include:

- Database connectivity issues
- Temporary Kafka communication failures
- Network interruptions
- External dependency failures

These exceptions trigger Kafka retry processing.

---

## Non-Retryable Exceptions

Non-retryable exceptions represent permanent failures.

Examples include:

- Invalid event payload
- Unsupported event type
- Invalid business data
- Corrupted messages

These events are routed directly to the Dead Letter Topic (DLT).

---

# 🔍 Logging Strategy

Logging is configured using Spring Profiles.

| Environment | Log Level |
|------------|-----------|
| Local | INFO |
| CUT | INFO |
| ETE | INFO |
| DRT | INFO |
| Test | DEBUG |
| Production | WARN |

This strategy provides:

- Detailed debugging during testing
- Clean logs during development
- Minimal logging overhead in production

---

# 📂 Configuration Flow

```text
                    application.yml
                           │
        ┌──────────────────┼──────────────────┐
        │                  │                  │
        ▼                  ▼                  ▼
   Server Config      Database Config    Kafka Consumer
        │                  │                  │
        ▼                  ▼                  ▼
  Spring Boot       Spring Data JPA   Kafka Listener
        │                  │                  │
        └──────────────────┼──────────────────┘
                           ▼
                    Inventory Service
```

---

# 📋 Configuration Highlights

- Spring Boot 4.1
- Apache Kafka Consumer
- MySQL Integration
- Spring Data JPA
- Manual Offset Management
- Consumer Groups
- Idempotent Consumer Pattern
- Multiple Spring Profiles
- Environment-specific Logging
- Production-Oriented Configuration

---
---

# 🚀 Getting Started

This section explains how to set up and run the Inventory Service locally.

---

# 📋 Prerequisites

Ensure the following software is installed before running the application.

| Software | Recommended Version |
|-----------|---------------------|
| Java | 21 |
| Spring Boot | 4.1 |
| Gradle | 8+ |
| MySQL | 8.x |
| Apache Kafka | 4.x (KRaft Mode) |
| Git | Latest |
| IntelliJ IDEA | Latest |

---

# 📥 Clone Repository

```bash
git clone https://github.com/PSaiRam32/inventory-service.git

cd inventory-service
```

---

# 🗄️ Configure MySQL

Create the Inventory database.

```sql
CREATE DATABASE kafka_inventory;
```

Update your datasource configuration if required.

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/kafka_inventory
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
```

---

# 📨 Start Apache Kafka (KRaft Mode)

This project uses **Apache Kafka running in KRaft Mode**, eliminating the need for ZooKeeper.

### Start Kafka Brokers

If using a two-node Kafka cluster:

```text
Broker 1 : localhost:9092

Broker 2 : localhost:9094
```

Start each broker using its respective KRaft configuration.

---

### Verify Kafka

List all topics.

```bash
kafka-topics --bootstrap-server localhost:9092 --list
```

Example:

```
product-events

product-events-dlt
```

---

# ▶️ Build Project

Linux / macOS

```bash
./gradlew clean build
```

Windows

```cmd
gradlew.bat clean build
```

---

# ▶️ Run Inventory Service

```bash
./gradlew bootRun
```

or

```cmd
gradlew.bat bootRun
```

The application starts on

```
http://localhost:8083
```

---

# 📡 Kafka Topics

The Inventory Service subscribes to the following topics.

| Topic | Purpose |
|---------|---------|
| product-events | Receives Product Events |
| product-events-dlt | Stores permanently failed events |

---

# 👥 Consumer Group

Consumer Group

```
inventory-service-group
```

Using a Consumer Group enables:

- Horizontal Scaling
- Partition Rebalancing
- Fault Tolerance
- Load Distribution

---

# 🔄 Event Processing Flow

```text
Product Service

      │

Publish Product Event

      │

      ▼

Apache Kafka

      │

      ▼

Inventory Consumer

      │

Validate Event

      │

Duplicate Detection

      │

Business Processing

      │

Update Inventory

      │

Save Processed Event

      │

Commit Offset
```

---

# 🧪 Testing

Recommended testing strategy.

## Unit Testing

- Service Layer
- Validator
- Kafka Consumer Logic
- Mapper
- Utility Classes

---

## Integration Testing

- Kafka Consumer
- MySQL Integration
- Repository Layer
- Event Processing
- Duplicate Detection

---

## End-to-End Testing

Validate complete workflow.

```text
Product Service

↓

Kafka

↓

Inventory Service

↓

Database Updated
```

---

# 📈 Performance Considerations

The service has been designed for high throughput event processing.

Enterprise considerations include:

- Consumer Groups
- Batch Polling
- Manual Offset Commit
- Idempotent Processing
- Efficient Database Access

Future optimizations may include:

- Batch Database Writes
- Connection Pool Tuning
- Kafka Partition Scaling
- Horizontal Consumer Scaling
- Distributed Cache

---

# 📊 Monitoring

The following metrics are recommended for production deployments.

- Consumer Lag
- Kafka Throughput
- Processing Time
- Retry Count
- DLT Count
- Database Latency
- Failed Events
- JVM Metrics

Tools

- Spring Boot Actuator
- Prometheus
- Grafana

---

# 🔒 Security Considerations

For production environments consider implementing:

- Spring Security
- OAuth2
- JWT Authentication
- Secure Database Credentials
- TLS Encryption
- Secrets Management
- Audit Logging
- Role-Based Access Control (RBAC)

---

# 🏭 Production Readiness

The Inventory Service demonstrates several production-ready design patterns.

Implemented

- Layered Architecture
- Apache Kafka Consumer
- Consumer Groups
- Manual Offset Management
- Retry Strategy
- Dead Letter Topic
- Idempotent Consumer Pattern
- Processed Event Tracking
- Spring Profiles
- Global Exception Handling

Recommended Future Enhancements

- Docker
- Kubernetes
- Spring Boot Actuator
- Distributed Tracing
- Prometheus
- Grafana
- OpenTelemetry
- ELK Stack
- CI/CD Pipeline
- Centralized Configuration

---

# 📚 Learning Outcomes

This project demonstrates practical implementation of:

- Apache Kafka Consumer
- Event-Driven Microservices
- Consumer Groups
- Retry Mechanism
- Dead Letter Topic
- Idempotent Consumer Pattern
- Spring Boot
- Spring Data JPA
- MySQL
- Enterprise Layered Architecture

---

# 📌 Best Practices Followed

- Loose Coupling
- Event-Driven Communication
- Reliable Event Processing
- Duplicate Event Prevention
- Retry Handling
- Dead Letter Queue
- Clean Code Principles
- Layered Architecture
- Separation of Concerns
- Production-Oriented Configuration

---


# 🚀 Future Roadmap

The Inventory Service has been designed with extensibility in mind. The following enhancements are planned for future iterations.

## Planned Features

- Inventory Reservation Service
- Inventory Release Workflow
- Stock Adjustment Events
- Inventory Threshold Alerts
- Inventory Audit History
- Warehouse Management
- Multi-Warehouse Support
- Inventory Transfer Between Warehouses
- Batch Event Processing
- Event Replay Support

---

## Kafka Enhancements

- Retry Topics
- Dead Letter Queue Monitoring
- Schema Registry Integration
- Kafka Transactions
- Message Compression
- Partition Scaling
- Consumer Parallelism

---

## Cloud & DevOps

- Docker Support
- Docker Compose
- Kubernetes Deployment
- Helm Charts
- GitHub Actions CI/CD
- Jenkins Pipeline
- OpenShift Deployment

---

## Monitoring & Observability

- Spring Boot Actuator
- Prometheus Metrics
- Grafana Dashboards
- OpenTelemetry
- Distributed Tracing
- ELK Stack
- Centralized Logging

---

## Security

- Spring Security
- JWT Authentication
- OAuth2
- Role-Based Access Control (RBAC)
- Secrets Management
- HTTPS/TLS

---

# 📚 Key Concepts Demonstrated

This project showcases several enterprise-grade backend engineering concepts:

- Apache Kafka Consumer
- Event-Driven Architecture
- Consumer Groups
- Kafka Listener
- Manual Offset Management
- Idempotent Consumer Pattern
- Dead Letter Topic (DLT)
- Retry Strategy
- Global Exception Handling
- Spring Data JPA
- Layered Architecture
- Clean Code Principles
- Enterprise Configuration Management

---

# 🏆 Enterprise Design Patterns Used

| Pattern | Purpose |
|----------|---------|
| Layered Architecture | Separation of concerns |
| Repository Pattern | Data access abstraction |
| Service Layer Pattern | Business logic encapsulation |
| Idempotent Consumer Pattern | Prevent duplicate event processing |
| Dead Letter Topic (DLT) | Handle permanently failed events |
| Retry Pattern | Recover from transient failures |
| Event-Driven Architecture | Asynchronous communication |

---

# 🎯 Interview Highlights

This project demonstrates practical experience with enterprise messaging and distributed systems.

Topics you can confidently discuss during interviews include:

- Apache Kafka Consumer Architecture
- Event-Driven Microservices
- Consumer Groups
- Offset Management
- Manual Acknowledgement
- Idempotent Consumer Pattern
- Dead Letter Topic (DLT)
- Retry Mechanism
- Spring Boot Kafka Integration
- Fault Tolerance
- Distributed System Reliability

---

# 🤝 Contributing

Contributions are welcome.

If you would like to contribute:

1. Fork the repository.
2. Create a feature branch.
3. Implement your changes.
4. Commit your changes with meaningful commit messages.
5. Push the branch to your fork.
6. Open a Pull Request.

Please ensure all new code follows the existing project structure and coding standards.

---

# 📄 License

This project is intended for educational and learning purposes.

You are welcome to study, fork, and extend the project while respecting the repository license.

---

# 👨‍💻 Author

**Sai Ram Paidipati**

Java Backend Engineer

### Connect with me

- GitHub: https://github.com/PSaiRam32
- LinkedIn: https://www.linkedin.com/in/sairam-paidipati/

---

# ⭐ Support the Project

If this project helped you understand:

- Apache Kafka
- Event-Driven Architecture
- Kafka Consumer Design
- Idempotent Consumer Pattern
- Dead Letter Topic (DLT)
- Enterprise Spring Boot Development

please consider giving this repository a ⭐ on GitHub.

Your support motivates continued improvements and helps other developers discover the project.

---

# 📌 Project Summary

The **Inventory Service** is an enterprise-grade Kafka consumer built using **Java 21**, **Spring Boot 4.1**, **Apache Kafka**, and **MySQL**.

It demonstrates reliable event consumption using the **Idempotent Consumer Pattern**, supports fault tolerance through **Retry Mechanisms** and **Dead Letter Topics (DLT)**, and follows clean architectural principles such as **Layered Architecture**, **Repository Pattern**, and **Service Layer Pattern**.

By combining asynchronous messaging, duplicate event prevention, and production-oriented configuration, this project provides a strong foundation for building scalable, resilient, and event-driven microservices.

---

# 🙏 Acknowledgements

This project was built to explore enterprise backend development concepts and real-world event-driven microservice design.

Special focus areas include:

- Reliable Message Processing
- Fault-Tolerant Kafka Consumers
- Clean Architecture
- Production-Oriented Design
- Scalable Microservice Communication

Thank you for exploring this repository. Feedback, suggestions, and contributions are always appreciated.
