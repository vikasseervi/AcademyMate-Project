# AcademyMate Project

AcademyMate is a full-stack project with separate backend and frontend modules.

- **Backend**: Spring Boot REST API (`AcademyMate Backend/`)
- **Frontend**: Vite + React application (`AcademyMate Frontend/AcademyMate/`)

## Repository Structure

```text
AcademyMate-Project/
├── AcademyMate Backend/
│   ├── pom.xml
│   ├── src/
│   └── ...
├── AcademyMate Frontend/
│   └── AcademyMate/
│       ├── package.json
│       ├── src/
│       └── ...
└── README.md
```

## Module READMEs

For module-level setup and details:

- Backend guide: [`AcademyMate Backend/README.md`](./AcademyMate%20Backend/README.md)
- Frontend guide: [`AcademyMate Frontend/AcademyMate/README.md`](./AcademyMate%20Frontend/AcademyMate/README.md)

## Quick Start

### 1) Backend

```bash
cd "AcademyMate Backend"
./mvnw spring-boot:run
```

### 2) Frontend

```bash
cd "AcademyMate Frontend/AcademyMate"
npm install
npm run dev
```

## Suggested Run Order

1. Start backend first (default Spring Boot port: `8080`)
2. Start frontend second (Vite dev server)
3. Ensure frontend API base URL points to backend host/port

## Notes

- This repo currently contains IDE/build artifacts in some places (e.g., `.idea`).
- Consider adding stronger root-level docs over time:
  - architecture diagram
  - API endpoint list
  - environment variable matrix
  - deployment instructions
