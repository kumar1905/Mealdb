# MealDB App - Frontend (React)

This is the React frontend for the MealDB App hackathon project.

## Prerequisites

- Node.js 14+
- npm or yarn

## Setup

```bash
npm install
```

## Development

```bash
npm start
```

The app will run on http://localhost:3000

## Build

```bash
npm run build
```

This creates a production-ready build in the `build/` folder.

## Environment Variables

Create a `.env` file:

```
REACT_APP_API_URL=http://localhost:8080/api
```

The backend should be running on **port 8080** (Java/Spring Boot).

## Integration with Backend

The frontend communicates with the Java/Spring Boot backend at `http://localhost:8080/api`:

- `GET /api/meals/search?name=Arrabiata` - Search meals
- `GET /api/meals/least-ingredients?name=Arrabiata` - Get meal with least ingredients
- `GET /api/meals/random` - Get random meal
- `GET /api/meals/health` - Health check

## Features

✅ Search meals by name
✅ Display results with ingredient counts
✅ Show meal with fewest ingredients
✅ Responsive design
✅ Loading states and error handling

## Technologies

- React 18+
- Axios for API calls
- CSS3 for styling

## Project Structure

```
src/
├── App.jsx              # Main component
├── App.css              # Styling
└── index.js             # Entry point
```

---

**Person 2 (Frontend Developer):** Build UI components here and integrate with Java backend!
