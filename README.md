# Re-Bank - Fake Real Bank

A full‑stack banking application using Java Spring Boot microservices for the backend and a Next.js (TypeScript + React) frontend.

## Architecture

The system follows these architectural patterns:

- **Service Discovery** (Spring Cloud Netflix Eureka)
- **Centralized Configuration** (Spring Cloud Config)
- **Distributed Tracing** (Zipkin)
- **Event-Driven Architecture** (Apache Kafka)
- **Database per Microservice** (MongoDB, PostgreSQL, MySQL)
- **Cache-Aside Pattern** (Redis, Spring Cache)
- **Circuit Breaker Pattern** (Resilience4j)
- **API Gateway** (Spring Cloud Gateway)
- **Security** (JWT, OAuth2)
- **Containerization** (Docker)

## Modules

```text
backend/
├── config-server/
├── discovery-server/
├── api-gateway/
├── account-service/
├── transaction-service/
├── asset-service/
└── notification-service/

frontend/
└── nextjs-bank-app/
```

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.6+
- Node.js 18+
- Docker (optional)

### Running the Backend

Open a separate terminal for each service and run:

```
cd backend/config-server && mvn spring-boot:run
cd backend/discovery-server && mvn spring-boot:run
cd backend/api-gateway && mvn spring-boot:run
cd backend/account-service && mvn spring-boot:run
cd backend/transaction-service && mvn spring-boot:run
cd backend/asset-service && mvn spring-boot:run
cd backend/notification-service && mvn spring-boot:run
```

### Running the Frontend

```
cd frontend/nextjs-bank-app
npm install
npm run dev
```

## Next Steps

1. Scaffold each microservice with a `pom.xml`, `Application` class, and basic controller.
2. Implement domain models, repositories, and services.
3. Configure Spring Cloud Config, Eureka clients, and Kafka.
4. Secure endpoints with JWT/OAuth2.
5. Build and containerize modules with Docker.

---

## Frontend Documentation

### Pages
- `/` - Landing page with hero, operations dropdown, signup/login
- `/features` - Displays app capabilities and backend endpoint list
- `/help` - Fun under-construction page with animations
- `/blog` - Fake blog posts about the app
- `/advice` - Displays a playful motto
- `/accounts` - Fetches and displays accounts from `/api/accounts`
- `/transactions` - Fetches and displays transactions
- `/assets` - Fetches and displays assets
- `/notifications` - Fetches and displays notifications
- `/login` - Login form posting to `/api/login`
- `/signup` - Signup form posting to `/api/signup`

### Proxy API Endpoints
- `GET/POST/PUT/PATCH/DELETE /api/accounts`
- `GET/POST/PUT/PATCH/DELETE /api/transactions`
- `GET/POST/PUT/PATCH/DELETE /api/assets`
- `GET/POST/PUT/PATCH/DELETE /api/notifications`
- `POST /api/login`
- `POST /api/signup`

## Backend Documentation

All backend services run behind the API Gateway at `http://localhost:8080`.

### Account Service
- `GET /api/accounts`
- `POST /api/accounts`
- `GET /api/accounts/{id}`
- `PUT /api/accounts/{id}`
- `DELETE /api/accounts/{id}`

### Transaction Service
- `GET /api/transactions`
- `POST /api/transactions`
- `GET /api/transactions/{id}`
- `PUT /api/transactions/{id}`
- `DELETE /api/transactions/{id}`

### Asset Service
- `GET /api/assets`
- `POST /api/assets`
- `GET /api/assets/{id}`
- `PUT /api/assets/{id}`
- `DELETE /api/assets/{id}`

### Notification Service
- `GET /api/notifications`
- `POST /api/notifications`
- `GET /api/notifications/{id}`
- `PUT /api/notifications/{id}`
- `DELETE /api/notifications/{id}`

### Authentication Endpoints
- `POST /login` - Spring Security login (session cookie)
- `POST /signup` - User registration

## Tech Stack

### Backend
- Java 17
- Spring Boot, Spring Cloud (Eureka, Config, Gateway)
- Spring Security (JWT, OAuth2)
- Resilience4j (Circuit Breaker)
- Apache Kafka (Event-Driven)
- Redis (Cache-Aside)
- MongoDB, PostgreSQL, MySQL
- Docker

### Frontend
- Next.js (React, TypeScript)
- Tailwind CSS
- Shadcn UI
- Environment: Node.js 18, .env.local for API_GATEWAY_URL

---
