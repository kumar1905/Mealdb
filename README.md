# 🍽️ MealDB App - Hackathon Project

Find meals from TheMealDB that require the **least number of ingredients**!

## Project Structure

```
mealdb-app/
├── backend/          # Express.js API server
├── frontend/         # React.js web application
├── README.md         # This file
└── .gitignore        # Git ignore rules
```

## Quick Start

### Backend Setup (Java/Spring Boot)
```bash
cd backend
mvn clean install
mvn spring-boot:run
# Server runs on http://localhost:8080/api
```

### Frontend Setup (React)
```bash
cd frontend
npm install
npm start
# App runs on http://localhost:3000
```

## Key Features

✅ Search meals by name
✅ Find meals with the least ingredients
✅ Display ingredient counts
✅ Responsive UI design
✅ Real-time API integration

## Tech Stack

**Backend (Java):**
- Java 17
- Spring Boot 3.2.0
- Spring WebFlux
- Maven
- Lombok (boilerplate reduction)
- Jackson (JSON processing)

**Frontend:**
- React.js
- Axios (API calls)
- CSS3 (responsive design)

**External API:**
- TheMealDB API: https://www.themealdb.com/api

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/meals/search?name=Arrabiata` | Search meals by name |
| GET | `/api/meals/least-ingredients` | Get meal with least ingredients |
| GET | `/api/meals/random` | Get random meal |

## Environment Setup

Create `.env` files in both directories:

**backend/.env:**
```
PORT=5000
NODE_ENV=development
```

**frontend/.env:**
```
REACT_APP_API_URL=http://localhost:5000/api
```

## Team Roles

👤 **Person 1 (Backend Developer)**
- Build Express.js API
- Create meal search and filtering logic
- Implement least-ingredients algorithm
- Test endpoints with Postman

👤 **Person 2 (Frontend Developer)**
- Build React UI
- Create search and display components
- Connect frontend to backend API
- Implement responsive design

## Development

1. Clone the repository
2. Install dependencies in both `backend/` and `frontend/`
3. Create `.env` files based on `.env.example`
4. Run `npm run dev` in backend (or `npm start` for frontend)
5. Make changes in separate branches
6. Create pull requests and merge to main

## Testing

Use Postman to test API endpoints:
1. Import endpoints from Postman collection
2. Test each endpoint with various inputs
3. Verify response formats

## Deployment

### Backend (Heroku/Railway)
```bash
git push heroku main
```

### Frontend (Vercel/Netlify)
```bash
npm run build
# Deploy build/ folder
```

## Hackathon Presentation

See `MEALDB_APP_TASK_DIVISION.md` for:
- Complete presentation talking points
- Demo walkthrough script
- Q&A preparation
- Technical architecture explanation

## Resources

- 🍽️ TheMealDB API: https://www.themealdb.com/api
- 📮 Postman: https://www.postman.com
- ⚛️ React Docs: https://react.dev
- 🚂 Express Docs: https://expressjs.com

## License

MIT License

---

**Good luck with the hackathon! 🚀**
