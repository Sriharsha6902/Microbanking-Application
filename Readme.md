# MicroBank -- Distributed Microservices Banking System

## Overview

MicroBank is a distributed microservices-based banking system designed
to simulate real-world financial operations such as account management,
transactions, notifications, and fraud detection.

The system focuses on scalability, fault tolerance, concurrency
handling, and event-driven architecture.

------------------------------------------------------------------------

## Key Features

-   Account creation and management
-   Money transfer between accounts
-   Transaction ledger tracking
-   Notification system (event-driven)
-   Idempotent transaction handling
-   Fraud detection (extensible)
-   Resilient inter-service communication
-   Concurrent request handling using virtual threads

------------------------------------------------------------------------

## Architecture

### Core Services
-   Orchestrator Service -- coordinates distributed transactions using
    Saga pattern
-   Account Service -- manages user accounts and balances
-   Transaction Service -- handles transfers and idempotency
-   Ledger Service -- maintains immutable transaction records
-   Notification Service -- sends alerts based on events
-   Fraud Detection Service (Planned) -- analyzes suspicious activity
-   Load Service (Planned) -- manages loans of account holders

------------------------------------------------------------------------

### Infrastructure Components

-   API Gateway -- entry point for requests
-   Service Discovery (Consul)
-   Config Server
-   Kafka -- event-driven communication
-   Prometheus -- metrics collection
-   Grafana -- metrics visualization dashboards

------------------------------------------------------------------------

## Tech Stack

-   Java, Spring Boot, Spring Cloud
-   Kafka
-   PostgreSQL
-   Docker, Docker Compose
-   Prometheus + Grafana
------------------------------------------------------------------------

## System Design Highlights

### Saga Orchestration

-   Orchestrator Service manages distributed transactions
-   Ensures consistency across services using Saga pattern
-   Handles:
    -   Step execution
    -   Compensation (rollback)
    -   Failure recovery

### Idempotency

Each transaction uses a unique id to prevent duplication and ensure safe
retries.

### Event-Driven Architecture

Kafka enables loose coupling and asynchronous processing.
-   Transaction → Ledger
-   Transaction → Notification
-   Transaction → Fraud Analysis(Planned)

### Concurrency

Virtual threads efficiently handle high concurrent workloads, especially
IO-bound operations.

### Resilience

-   Circuit breakers
-   Retry mechanisms
-   Graceful degradation
------------------------------------------------------------------------

## Project Structure

microbanking-application\
├── account-service\
├── transaction-service\
├── ledger-service\
├── notification-service\
├── orchestrator-service\
├── gateway-service\
├── config-service

------------------------------------------------------------------------

## Running the Project

### Prerequisites

-   Java 21+
-   Docker & Docker Compose

### Steps

1.  Clone repository
2.  Build with Gradle
3.  Run using docker-compose

------------------------------------------------------------------------

## API Flow Example

1.  Client → API Gateway
2.  Orchestrator Service initiates Saga
3.  Transaction Service processes debit/credit
4.  Account Service updates balances
5.  Events published via Kafka
6.  Ledger + Notification services consume
7.  On failure → Orchestrator triggers compensation

------------------------------------------------------------------------

## Monitoring

-   Prometheus scrapes metrics from services
-   Grafana visualizes:
    -   API performance
    -   System health
    -   Resource usage

------------------------------------------------------------------------

## Future Enhancements

-   ML-based fraud detection
-   Distributed tracing
-   Kubernetes deployment

------------------------------------------------------------------------

## Purpose

Demonstrates distributed system design, Saga orchestration,
observability, and concurrency handling in a microservices architecture.
