# AcademyMate Frontend

Frontend module for AcademyMate built with React + Vite.

## Tech Stack

- React
- Vite
- JavaScript/JSX
- ESLint
- npm

## Frontend Structure

```text
AcademyMate Frontend/AcademyMate/
├── index.html
├── package.json
├── package-lock.json
├── vite.config.js
├── eslint.config.js
├── public/
├── src/
└── README.md
```

## Prerequisites

- Node.js (LTS recommended)
- npm

## Install & Run

From repository root:

```bash
cd "AcademyMate Frontend/AcademyMate"
npm install
npm run dev
```

Vite will print the local URL (typically `http://localhost:5173`).

## Build for Production

```bash
npm run build
npm run preview
```

## Lint

```bash
npm run lint
```

## Backend Integration

- Ensure backend is running (typically at `http://localhost:8080`).
- Configure API base URL in frontend code/env (if applicable).
- If CORS issues occur, enable/configure CORS on backend.

## Existing Frontend README

A starter README already exists in this module. This updated README provides a more complete run/build/integration guide.

## Next Improvements

- Add environment config (`.env`) documentation
- Add route map and screen descriptions
- Add UI screenshots/GIF demo
- Add API service layer docs
