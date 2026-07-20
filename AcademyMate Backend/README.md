# AcademyMate Backend

Spring Boot backend for AcademyMate, providing REST APIs backed by MySQL and JPA/Hibernate.

## Tech Stack

- Java 21
- Spring Boot 3.2.3
- Spring Web
- Spring Data JPA
- MySQL Connector/J
- Hibernate + JCache integration
- Ehcache (configured via `ehcache.xml`)
- Maven Wrapper (`mvnw`)

## Backend Structure

```text
AcademyMate Backend/
├── pom.xml
├── mvnw / mvnw.cmd
├── sql scripts/
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   │       ├── application.properties
│   │       └── ehcache.xml
│   └── test/
└── README.md
```

## Configuration

Primary configuration files:

- `src/main/resources/application.properties`
- `src/main/resources/ehcache.xml`

Likely configurable items include:
- datasource URL/credentials
- JPA/Hibernate behavior
- cache behavior
- server properties

> Update database credentials and connection URL for your local environment before running.

## Prerequisites

- Java 21
- MySQL running locally or remotely
- Maven (optional, wrapper included)

## Run Backend

From repository root:

```bash
cd "AcademyMate Backend"
./mvnw spring-boot:run
```

On Windows:

```bat
cd "AcademyMate Backend"
mvnw.cmd spring-boot:run
```

## Build / Test

```bash
./mvnw clean test
./mvnw clean package
```

## Database

- Ensure your target database exists.
- Apply scripts from `sql scripts/` as needed.
- Validate table/entity mappings before first run.

## Caching

This module includes Ehcache + Hibernate JCache dependencies and an `ehcache.xml` file, indicating second-level caching support.

## Next Improvements

- Add API endpoint documentation (OpenAPI/Swagger)
- Add environment variable-based configuration
- Add Dockerfile and docker-compose for MySQL + API
- Add integration tests for repository/service layers
