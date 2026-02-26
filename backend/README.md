# MealDB App - Backend

This is the backend API for the MealDB App hackathon project.

## Setup

```bash
npm install
```

## Development

```bash
npm run dev
```

The server will run on http://localhost:5000

## Environment Variables

Create a `.env` file:

```
PORT=5000
NODE_ENV=development
```

## API Endpoints

- `GET /api/meals/search?name=Arrabiata` - Search meals by name
- `GET /api/meals/least-ingredients` - Get meal with least ingredients
- `GET /api/meals/random` - Get random meal

See main project documentation for full details.
