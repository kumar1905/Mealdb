# MealDB App - Full-Stack Java Setup Guide

## 📚 Overview

This is a **Full-Stack Java** application:
- **Backend:** Spring Boot 3.2 (Java 17)
- **Frontend:** React 18 (JavaScript)
- **External API:** TheMealDB

---

## 🔧 Person 1: Backend Developer (Java/Spring Boot)

### Prerequisites
- **Java 17 or higher** (Download from [oracle.com](https://www.oracle.com/java/technologies/downloads/))
- **Maven 3.6+** (Download from [maven.apache.org](https://maven.apache.org/download.cgi))
- **IDE:** IntelliJ IDEA (recommended) or VS Code with extensions

### Verify Installation
```bash
java -version
mvn -version
```

### Project Structure
```
backend/
├── pom.xml                                    # Maven configuration
├── src/
│   ├── main/
│   │   ├── java/com/mealdb/
│   │   │   ├── MealDbBackendApplication.java # Main Spring Boot entry point
│   │   │   ├── config/
│   │   │   │   └── AppConfig.java           # CORS & RestTemplate configs
│   │   │   ├── controller/
│   │   │   │   └── MealController.java      # REST API endpoints
│   │   │   ├── service/
│   │   │   │   └── MealService.java         # Business logic & algorithms
│   │   │   ├── model/
│   │   │   │   ├── Meal.java               # Meal entity
│   │   │   │   └── Ingredient.java         # Ingredient entity
│   │   │   └── dto/
│   │   │       └── ApiResponse.java        # Response wrapper
│   │   └── resources/
│   │       └── application.properties       # Server & API configuration
│   └── test/                                # Unit tests
└── README.md
```

### Setup Steps

**Step 1: Install Dependencies**
```bash
cd backend
mvn clean install
```

**Step 2: Run the Application**
```bash
# Development mode (with auto-reload)
mvn spring-boot:run

# Or in IDE:
# Right-click MealDbBackendApplication.java > Run 'MealDbBackendApplication.main()'
```

**Step 3: Verify Server is Running**
```bash
# Open browser and go to:
http://localhost:8080/api/meals/health
```

You should see:
```json
{
  "success": true,
  "message": "MealDB Backend is running!",
  "data": "OK"
}
```

### API Endpoints to Implement

#### 1. Search Meals
```
GET /api/meals/search?name=Arrabiata
```

**Request Example:**
```bash
curl "http://localhost:8080/api/meals/search?name=Pasta"
```

**Response:**
```json
{
  "success": true,
  "message": "Success",
  "data": [
    {
      "id": "52977",
      "name": "Arrabiata",
      "image": "https://...",
      "ingredientCount": 5,
      "ingredients": [
        {"name": "Spaghetti", "measurement": "1 pound"},
        {"name": "Garlic", "measurement": "3 cloves"},
        ...
      ],
      "instructions": "Prepare pasta..."
    }
  ]
}
```

**Logic:**
- Call TheMealDB API: `https://www.themealdb.com/api/json/v1/1/search.php?s={meal}`
- Parse JSON response
- Extract ingredients from `strIngredient1-20` and `strMeasure1-20`
- Sort by ingredient count (ascending)
- Return formatted response

#### 2. Get Meal with Least Ingredients
```
GET /api/meals/least-ingredients?name=Pasta
```

**Response:**
```json
{
  "success": true,
  "message": "Success",
  "data": {
    "id": "52900",
    "name": "Simple Pasta",
    "ingredientCount": 3,
    "ingredients": [
      {"name": "Pasta", "measurement": "500g"},
      {"name": "Salt", "measurement": "1 tsp"},
      {"name": "Water", "measurement": "2L"}
    ]
  }
}
```

**Logic:**
- Call search endpoint internally
- Return first meal (already sorted by ingredient count)

#### 3. Get Random Meal
```
GET /api/meals/random
```

**Logic:**
- Call TheMealDB API: `https://www.themealdb.com/api/json/v1/1/random.php`
- Parse and return single meal

### Key Java Classes

**MealService.java** (Business Logic)
```java
@Service
public class MealService {
    public List<Meal> searchMealsByName(String mealName) {
        // 1. Call TheMealDB API
        // 2. Parse JSON response
        // 3. Extract and count ingredients
        // 4. Sort by ingredient count
        // 5. Return list
    }

    public Meal getMealWithLeastIngredients(String mealName) {
        // Call searchMealsByName and return first result
    }
}
```

**MealController.java** (REST Endpoints)
```java
@RestController
@RequestMapping("/meals")
public class MealController {
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Meal>>> searchMeals(
        @RequestParam String name) {
        // Call service and return response
    }

    @GetMapping("/least-ingredients")
    public ResponseEntity<ApiResponse<Meal>> getMealWithLeastIngredients(
        @RequestParam(required = false) String name) {
        // Call service and return single meal
    }
}
```

### Configuration

**application.properties**
```properties
# Server
server.port=8080
server.servlet.context-path=/api

# Logging
logging.level.root=INFO
logging.level.com.mealdb=DEBUG

# External API
mealdb.api.url=https://www.themealdb.com/api/json/v1/1
```

### Testing with Postman

1. **Import Collection:**
   - Open Postman
   - File → Import → Select `MealDB_API_Collection.postman_collection.json`
   - Set variable: `base_url=http://localhost:8080/api`

2. **Test Each Endpoint:**
   - Health Check
   - Search Meals (try: "Pasta", "Arrabiata", "Biryani")
   - Least Ingredients
   - Random Meal

### Common Issues

**Issue:** `RestTemplate` not found
```
Solution: Add spring-boot-starter-web dependency (already in pom.xml)
```

**Issue:** CORS errors from frontend
```
Solution: Update AppConfig.java corsConfigurationSource() method
Add your frontend URL to allowedOrigins
```

**Issue:** TheMealDB API not responding
```
Solution:
1. Check internet connection
2. Verify URL: https://www.themealdb.com/api/json/v1/1/search.php?s=test
3. Clear proxy settings if behind corporate firewall
```

---

## 👩‍💻 Person 2: Frontend Developer (React)

### Prerequisites
- **Node.js 16+** (Download from [nodejs.org](https://nodejs.org/))
- **npm 8+** (comes with Node.js)
- **IDE:** VS Code recommended

### Project Structure
```
frontend/
├── public/
│   ├── index.html           # Main HTML file
│   └── favicon.ico
├── src/
│   ├── components/          # React components (TO BUILD)
│   │   ├── SearchBar.jsx
│   │   ├── MealCard.jsx
│   │   └── MealList.jsx
│   ├── pages/               # Page components (TO BUILD)
│   │   ├── Home.jsx
│   │   └── Results.jsx
│   ├── services/            # API service
│   │   └── api.js           # Axios API calls
│   ├── App.jsx             # Main component
│   ├── App.css             # Styling
│   ├── index.js            # Entry point
│   └── index.css           # Global styles
├── package.json
├── .env.example
└── README.md
```

### Setup Steps

**Step 1: Install Dependencies**
```bash
cd frontend
npm install
```

**Step 2: Create Environment File**
```bash
# Copy .env.example to .env
cp .env.example .env
```

**Step 3: Start Development Server**
```bash
npm start
```

The app will open at `http://localhost:3000`

### Components to Build

#### SearchBar.jsx
```jsx
function SearchBar({ onSearch }) {
  const [input, setInput] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    onSearch(input);
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        placeholder="Search for a meal..."
        value={input}
        onChange={(e) => setInput(e.target.value)}
      />
      <button type="submit">Search</button>
    </form>
  );
}
```

#### MealCard.jsx
```jsx
function MealCard({ meal }) {
  return (
    <div className="meal-card">
      <img src={meal.image} alt={meal.name} />
      <h3>{meal.name}</h3>
      <p>Ingredients: {meal.ingredientCount}</p>
      <details>
        <summary>View Ingredients</summary>
        <ul>
          {meal.ingredients.map((ing, idx) => (
            <li key={idx}>{ing.name} - {ing.measurement}</li>
          ))}
        </ul>
      </details>
    </div>
  );
}
```

#### API Service (api.js)
```javascript
import axios from 'axios';

const API = axios.create({
  baseURL: process.env.REACT_APP_API_URL || 'http://localhost:8080/api'
});

export const searchMeals = (name) =>
  API.get(`/meals/search?name=${name}`);

export const getLeastIngredientsmeal = (name) =>
  API.get(`/meals/least-ingredients?name=${name}`);

export const getRandomMeal = () =>
  API.get(`/meals/random`);
```

### Features to Implement

✅ Search bar with input
✅ Display meal results in grid
✅ Show ingredient count for each meal
✅ Highlight meal with fewest ingredients
✅ Expandable ingredient list
✅ Loading spinner while fetching
✅ Error message display
✅ Responsive design (mobile, tablet, desktop)

### Integration with Backend

**Before testing frontend:**
1. Make sure backend is running on `http://localhost:8080/api`
2. Verify endpoints work in Postman first
3. Check `.env` file has correct `REACT_APP_API_URL`

**Test flow:**
```
User types "Pasta" → Click Search
↓
Frontend calls: GET http://localhost:8080/api/meals/search?name=Pasta
↓
Backend calls TheMealDB API
↓
Backend returns sorted meals
↓
Frontend displays results
```

### Build for Production

```bash
npm run build
```

Creates optimized build in `build/` folder - ready to deploy!

---

## 🤝 Collaboration Workflow

### Branch Management

**Person 1 (Backend):**
```bash
git checkout -b feature/backend-api
# Make changes...
git add .
git commit -m "Add meal search and filtering endpoints"
git push -u origin feature/backend-api
# Create Pull Request on GitHub
```

**Person 2 (Frontend):**
```bash
git checkout -b feature/frontend-ui
# Make changes...
git add .
git commit -m "Add search bar and meal cards"
git push -u origin feature/frontend-ui
# Create Pull Request on GitHub
```

### Daily Sync

- Backend person ensures API endpoints match contract
- Frontend person verifies API responses match expected format
- Test together with Postman before integration
- Communicate any format changes immediately

### Integration Checklist

✅ Backend running on port 8080
✅ Frontend running on port 3000
✅ CORS enabled in Spring Boot
✅ `.env` file configured correctly
✅ Postman collection tests pass
✅ Frontend makes successful API calls
✅ Results display correctly
✅ No console errors
✅ Responsive design works on mobile

---

## 🧪 Testing

### Backend Testing with Postman

Import `MealDB_API_Collection.postman_collection.json`:
1. Open Postman
2. File → Import
3. Select the JSON file
4. Set `base_url` to `http://localhost:8080/api`
5. Run all requests and verify responses

### Frontend Testing

```bash
# Run tests
npm test

# Build and verify
npm run build
npm run preview  # Preview production build
```

### Manual Testing

1. Start backend: `mvn spring-boot:run`
2. Start frontend: `npm start`
3. Search for "Pasta"
4. Verify results display with ingredient counts
5. Check that meals are sorted by fewest ingredients first

---

## 🚀 Deployment

### Backend Deployment

**Option 1: Heroku**
```bash
# Create JAR file
mvn clean package

# Deploy to Heroku
git push heroku main
```

**Option 2: Railway/Render**
- Push to GitHub
- Connect repository to Railway/Render
- Set Java 17 as runtime
- Deploy!

### Frontend Deployment

**Option 1: Vercel**
```bash
npm install -g vercel
vercel
```

**Option 2: Netlify**
- Connect GitHub repo to Netlify
- Build: `npm run build`
- Publish: `build` folder
- Deploy!

### Environment Variables for Production

**Backend (application.properties):**
```properties
server.port=${PORT:8080}
server.servlet.context-path=/api
mealdb.api.url=https://www.themealdb.com/api/json/v1/1
```

**Frontend (.env):**
```
REACT_APP_API_URL=https://your-backend-url.herokuapp.com/api
```

---

## 📋 Checklist for Hackathon

### Before Demo Day

- [ ] Backend compiles without errors
- [ ] All endpoints tested with Postman
- [ ] Frontend builds without errors
- [ ] Frontend connects to backend successfully
- [ ] Search functionality works end-to-end
- [ ] Least ingredients feature works
- [ ] Responsive design tested on mobile
- [ ] No console warnings/errors
- [ ] Both persons can explain their code
- [ ] Demo script rehearsed (2-3 minutes)
- [ ] Deployment link is live
- [ ] Postman collection documented
- [ ] GitHub shows contributions from both

### Presentation Talking Points

**Problem:** Many people want to cook but feel overwhelmed by complex recipes

**Solution:** MealDB App finds the simplest meals (fewest ingredients)

**Architecture:**
- Java Spring Boot backend connects to TheMealDB API
- Parses meal data and counts ingredients
- React frontend displays results beautifully
- Communication via REST API

**Demo Flow:**
1. Search for "Pasta"
2. Show all results sorted by ingredient count
3. Highlight the meal with fewest ingredients
4. Click to see full ingredient list
5. Show how responsive design works

**Technologies:**
- Backend: Java 17, Spring Boot 3
- Frontend: React 18
- API: TheMealDB (free, public)

---

## 🆘 Troubleshooting

### Backend Issues

**Port 8080 already in use:**
```properties
# Edit application.properties
server.port=8081
```

**Maven: Command not found:**
```bash
# Add Maven to PATH or use full path:
/opt/apache-maven-3.9.0/bin/mvn clean install
```

**Lombok annotations not working:**
- Install Lombok plugin in IDE
- Rebuild project
- Check IDE annotation processing is enabled

### Frontend Issues

**CORS Error:**
```
'Access-Control-Allow-Origin' header missing
```
Solution: Verify AppConfig.java has correct frontend URL

**API calls failing:**
```
network::ERR_CONNECTION_REFUSED
```
Solution: Make sure backend is running on port 8080

**npm modules missing:**
```bash
rm -rf node_modules package-lock.json
npm install
```

---

## 📞 Quick Reference

| Task | Command |
|------|---------|
| Backend - Install deps | `mvn clean install` |
| Backend - Run | `mvn spring-boot:run` |
| Backend - Build JAR | `mvn clean package` |
| Frontend - Install deps | `npm install` |
| Frontend - Run dev | `npm start` |
| Frontend - Build | `npm run build` |
| Test Backend | Import Postman collection |
| Test Frontend | `npm test` |

---

**Good luck with your hackathon! 🎉**

You've got everything set up for a successful Full-Stack Java application!
