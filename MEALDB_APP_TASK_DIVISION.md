# 🍽️ MealDB App - Hackathon Project

## Project Overview
An application that fetches meal data from TheMealDB API and returns meals with the **least number of ingredients**. This is a 2-person collaborative project designed for hackathon presentation.

### Key Features:
- Search for meals by name
- Find meals requiring the fewest ingredients
- Display complete ingredient lists
- Show meal images and preparation instructions
- Responsive, user-friendly interface

---

## 📋 TASK DIVISION - PERSON 1 & PERSON 2

### ✅ PERSON 1: Backend Developer

#### Responsibilities:
1. **API Integration & Backend Setup**
   - Set up Express.js/Node.js backend
   - Create API wrapper for TheMealDB API
   - Handle API requests and responses
   - Error handling and data validation
   - CORS configuration for frontend communication

2. **Core Algorithm**
   - Implement function to search meals by name via TheMealDB API
   - Create algorithm to **find meal with LEAST ingredients**
   - Parse ingredient data from API response
   - Count ingredients accurately
   - Return meal data sorted by ingredient count

3. **API Endpoints to Build:**
   ```
   GET /api/meals/search?name=Arrabiata
   - Query TheMealDB API
   - Return list of matching meals with ingredient counts
   - Response: { meals: [...], count: number }

   GET /api/meals/least-ingredients
   - Query TheMealDB API
   - Find meal with fewest ingredients
   - Return single meal object with ingredients array

   GET /api/meals/random
   - Get random meal from TheMealDB
   - Return with ingredient count
   ```

4. **Testing**
   - Use Postman to test all endpoints
   - Document API responses
   - Handle edge cases (no results, API errors)
   - Test with various meal names

#### Person 1 Tech Stack:
- Node.js
- Express.js
- Axios (for HTTP requests)
- CORS
- Environment variables (.env)

#### Person 1 File Structure:
```
mealdb-backend/
├── server.js
├── routes/
│   └── meals.js
├── controllers/
│   └── mealController.js
├── utils/
│   └── mealDB.js
├── .env
├── .gitignore
└── package.json
```

---

### ✅ PERSON 2: Frontend Developer

#### Responsibilities:
1. **UI/UX Setup**
   - Set up React/Vue.js frontend
   - Create responsive meal display components
   - Design meal cards showing ingredients
   - Implement loading and error states

2. **Features to Build:**
   - **Search Bar**: Input field to search meals by name
   - **Results Display**: Show all matching meals in cards
   - **Meal Cards**: Display meal image, name, ingredient count
   - **Least Ingredients Highlight**: Prominently show meal with fewest ingredients
   - **Ingredient List**: Expandable/collapsible ingredient list for each meal
   - **Instructions**: Display meal preparation instructions
   - **Filters**: Sort by ingredient count

3. **User Interface Components:**
   - Header with logo/title
   - Search input with submit button
   - Loading spinner during API calls
   - Error message display
   - Meal result cards (grid/list view)
   - Single meal detail view
   - "Least Ingredients" badge/marker

4. **Integration:**
   - Connect to Person 1's backend APIs
   - Handle API responses and errors
   - Display data dynamically
   - State management for meals data

#### Person 2 Tech Stack:
- React.js (or Vue.js)
- Axios (for API calls)
- CSS/Bootstrap/Tailwind (styling)
- React Router (navigation)

#### Person 2 File Structure:
```
mealdb-frontend/
├── src/
│   ├── components/
│   │   ├── SearchBar.jsx
│   │   ├── MealCard.jsx
│   │   ├── MealList.jsx
│   │   └── MealDetail.jsx
│   ├── pages/
│   │   ├── Home.jsx
│   │   └── Results.jsx
│   ├── services/
│   │   └── api.js
│   ├── App.jsx
│   └── App.css
├── public/
└── package.json
```

---

## 🤝 COLLABORATION POINTS

### Before Starting:
1. **Agree on API Response Format**
   ```json
   Meal Object:
   {
     "id": "52977",
     "name": "Arrabiata",
     "image": "url",
     "ingredients": [
       { "name": "Garlic", "measurement": "3 cloves" },
       { "name": "Red Chili Pepper", "measurement": "2" }
     ],
     "ingredientCount": 4,
     "instructions": "..."
   }
   ```

2. **Define Base API URL**
   ```
   Backend URL: http://localhost:5000/api
   TheMealDB Base: https://www.themealdb.com/api/json/v1/1/
   ```

3. **Create .env Files**
   ```
   Backend .env:
   PORT=5000
   NODE_ENV=development

   Frontend .env:
   REACT_APP_API_URL=http://localhost:5000/api
   ```

### During Development:
- **Branch Strategy**: Person 1 uses `feature/backend-api-integration`, Person 2 uses `feature/frontend-ui`
- **Daily Sync**: Brief 15-min sync to ensure endpoints match expectations
- **Code Reviews**: Review each other's pull requests before merge

### Testing Phase:
- Both test using Postman before frontend integration
- Test various meal names
- Test error scenarios
- Verify UI displays correctly with real API data

### Integration Phase:
- Connect frontend to backend
- Test end-to-end flow
- Fix any data format mismatches
- Optimize API calls (debouncing search, caching)

---

## 🔄 GITHUB WORKFLOW

### Repository Setup:
```bash
# One person initializes (Person 1):
mkdir mealdb-app
cd mealdb-app
git init
git remote add origin https://github.com/your-username/mealdb-app.git

# Create initial structure
mkdir backend frontend

# Push initial commit
git add .
git commit -m "Initial commit: Project structure"
git push -u origin main
```

### Branching Strategy:

**Person 1 (Backend):**
```bash
git checkout -b feature/backend-api-integration
# ... make changes ...
git add .
git commit -m "Add meal search and least-ingredients endpoints"
git push -u origin feature/backend-api-integration
# Create Pull Request on GitHub
```

**Person 2 (Frontend):**
```bash
git checkout -b feature/frontend-ui
# ... make changes ...
git add .
git commit -m "Add search bar and meal results display"
git push -u origin feature/frontend-ui
# Create Pull Request on GitHub
```

### Merging:
1. Create Pull Requests on GitHub
2. Review each other's code
3. Discuss any issues
4. Merge to `main` branch
5. Pull latest `main` locally
6. Both test final integration

### Deployment:
```bash
# From main branch
git checkout main
git pull origin main
# Follow deployment steps (see DEPLOYMENT section)
```

---

## 🚀 QUICK START COMMANDS

### PERSON 1: Backend Setup
```bash
# Create backend directory
mkdir mealdb-backend
cd mealdb-backend

# Initialize Node.js project
npm init -y

# Install dependencies
npm install express axios cors dotenv

# Create file structure
mkdir routes controllers utils
touch server.js routes/meals.js controllers/mealController.js utils/mealDB.js .env .gitignore

# Start development
npm install -g nodemon
nodemon server.js
```

### PERSON 2: Frontend Setup
```bash
# Create React app
npx create-react-app mealdb-frontend
cd mealdb-frontend

# Install dependencies
npm install axios react-router-dom

# Create file structure
mkdir src/components src/pages src/services
touch src/services/api.js

# Start development
npm start
```

---

## 📝 API ENDPOINT SPECIFICATIONS

### 1. Search Meals Endpoint

**URL:** `GET /api/meals/search`

**Query Parameters:**
- `name` (string, required): Name of the meal to search

**Example Request:**
```
GET http://localhost:5000/api/meals/search?name=Arrabiata
```

**Response:**
```json
{
  "success": true,
  "data": [
    {
      "id": "52977",
      "name": "Arrabiata",
      "image": "https://www.themealdb.com/images/media/meals/b8mzwxvtrb371.jpg",
      "ingredientCount": 5,
      "ingredients": [
        { "name": "Spaghetti", "measurement": "1 pound" },
        { "name": "Garlic", "measurement": "3 cloves" },
        { "name": "Red Chili Pepper", "measurement": "2" },
        { "name": "Tomato", "measurement": "1 can" },
        { "name": "Olive Oil", "measurement": "3 tablespoons" }
      ]
    }
  ]
}
```

**Error Response:**
```json
{
  "success": false,
  "message": "No meals found"
}
```

### 2. Least Ingredients Endpoint

**URL:** `GET /api/meals/least-ingredients`

**Query Parameters:**
- `name` (string, optional): Search specific meal category
- `limit` (number, optional, default: 5): Return top N meals with least ingredients

**Example Request:**
```
GET http://localhost:5000/api/meals/least-ingredients?limit=3
```

**Response:**
```json
{
  "success": true,
  "data": {
    "id": "52900",
    "name": "Simple Pasta",
    "image": "https://...",
    "ingredientCount": 3,
    "ingredients": [
      { "name": "Pasta", "measurement": "500g" },
      { "name": "Salt", "measurement": "1 teaspoon" },
      { "name": "Water", "measurement": "2 liters" }
    ],
    "instructions": "..."
  }
}
```

### 3. Random Meal Endpoint

**URL:** `GET /api/meals/random`

**Response:**
```json
{
  "success": true,
  "data": {
    "id": "52977",
    "name": "Arrabiata",
    "image": "https://...",
    "ingredientCount": 5,
    "ingredients": [...]
  }
}
```

---

## 🧪 POSTMAN TESTING GUIDE

### Setting up Postman Collection:

**Collection Name:** MealDB API

**Environment Variables:**
- `base_url`: http://localhost:5000/api
- `meal_name`: Arrabiata

### Test Requests:

**1. Search Meals**
```
Method: GET
URL: {{base_url}}/meals/search?name={{meal_name}}
```

**2. Least Ingredients**
```
Method: GET
URL: {{base_url}}/meals/least-ingredients?limit=5
```

**3. Random Meal**
```
Method: GET
URL: {{base_url}}/meals/random
```

### Postman Tests:
```javascript
// Add this in Tests tab to validate responses
pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});

pm.test("Response has success field", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData).to.have.property('success');
    pm.expect(jsonData.success).to.equal(true);
});

pm.test("Response has data", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData).to.have.property('data');
});
```

---

## 🎨 Frontend Component Examples

### SearchBar Component (Person 2)
```jsx
import React, { useState } from 'react';

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
        placeholder="Search meals..."
        value={input}
        onChange={(e) => setInput(e.target.value)}
      />
      <button type="submit">Search</button>
    </form>
  );
}

export default SearchBar;
```

### MealCard Component (Person 2)
```jsx
function MealCard({ meal, isLeastIngredients }) {
  return (
    <div className="meal-card">
      <img src={meal.image} alt={meal.name} />
      <h3>{meal.name}</h3>
      {isLeastIngredients && <span className="badge">Least Ingredients!</span>}
      <p>Ingredients: {meal.ingredientCount}</p>
      <button onClick={() => showDetails(meal.id)}>View Details</button>
    </div>
  );
}
```

---

## 🏗️ Backend Implementation Example

### Server Setup (Person 1) - server.js
```javascript
const express = require('express');
const cors = require('cors');
require('dotenv').config();

const mealRoutes = require('./routes/meals');

const app = express();
const PORT = process.env.PORT || 5000;

// Middleware
app.use(cors());
app.use(express.json());

// Routes
app.use('/api/meals', mealRoutes);

// Health check
app.get('/health', (req, res) => {
  res.json({ status: 'Server is running' });
});

app.listen(PORT, () => {
  console.log(`Backend running on http://localhost:${PORT}`);
});
```

### API Utility (Person 1) - utils/mealDB.js
```javascript
const axios = require('axios');

const MEALDB_API = 'https://www.themealdb.com/api/json/v1/1';

async function searchMeals(mealName) {
  try {
    const response = await axios.get(`${MEALDB_API}/search.php?s=${mealName}`);
    return formatMeals(response.data.meals || []);
  } catch (error) {
    throw new Error('Failed to fetch meals');
  }
}

function formatMeals(meals) {
  return meals.map(meal => ({
    id: meal.idMeal,
    name: meal.strMeal,
    image: meal.strMealThumb,
    ingredients: extractIngredients(meal),
    instructions: meal.strInstructions
  }));
}

function extractIngredients(meal) {
  const ingredients = [];
  for (let i = 1; i <= 20; i++) {
    const ingredient = meal[`strIngredient${i}`];
    const measure = meal[`strMeasure${i}`];
    if (ingredient && ingredient.trim()) {
      ingredients.push({ name: ingredient, measurement: measure });
    }
  }
  return ingredients;
}

function getMealWithLeastIngredients(meals) {
  if (!meals || meals.length === 0) return null;
  return meals.reduce((prev, current) =>
    prev.ingredients.length < current.ingredients.length ? prev : current
  );
}

module.exports = {
  searchMeals,
  getMealWithLeastIngredients
};
```

---

## 🎤 HACKATHON PRESENTATION - TALKING POINTS

### Opening (30 seconds):
"We built a meal discovery app that helps users find simple recipes. Our unique feature is identifying meals that require the **fewest ingredients** - perfect for quick cooking!"

### Problem Statement (30 seconds):
- Many people want to cook but feel overwhelmed by recipes with 20+ ingredients
- Existing meal apps don't highlight simplicity
- Our solution: **Find the easiest-to-make meals instantly**

### Solution Overview (1 minute):
1. **Backend**: Express.js API that queries TheMealDB and identifies ingredient count
2. **Frontend**: React UI for searching and discovering meals
3. **Algorithm**: Efficient sorting to find least-ingredient meals
4. **Architecture**: Separated backend/frontend for scalability

### Technical Implementation (2 minutes):

**Backend Highlights:**
- "We integrated with TheMealDB API to get real meal data"
- "Created a custom algorithm to count and sort ingredients"
- "Built RESTful endpoints for search and filtering"
- "Implemented error handling for edge cases"

**Frontend Highlights:**
- "Responsive design that works on mobile and desktop"
- "Real-time search with loading states"
- "Color-coded badges to highlight least-ingredient meals"
- "Expandable ingredient lists with measurements"

### Key Features Demonstration:
1. **Search**: Type meal name → shows all matches sorted by ingredient count
2. **Highlight**: Automatically badges the meal with fewest ingredients
3. **Details**: Click to see full ingredients and instructions
4. **Responsive**: Works on all devices

### Challenges & Solutions:
**Challenge 1**: Different ingredient formats in the API
- **Solution**: Parsed raw API data into standardized ingredient objects

**Challenge 2**: Handling meals with no results
- **Solution**: Added user-friendly error messages and suggestions

**Challenge 3**: Performance with large meal lists
- **Solution**: Implemented pagination and caching

### Why This Matters:
- Encourages home cooking with simple recipes
- Saves time for busy people
- Reduces food waste (fewer ingredients to buy)
- Educational value: Learn which meals are easiest to make

### Scalability & Future:
- Could add dietary filters (vegan, gluten-free)
- Add nutritional information
- Save favorite recipes
- Share recipes with friends
- Mobile app version

### Closing Statement:
"Our app makes cooking accessible by finding the dishes that don't demand a ton of ingredients. Whether you're a beginner cook or just busy - we help you find the right meal quickly!"

---

## 🚢 DEPLOYMENT GUIDE

### Option 1: Deploy on Heroku

**Backend Deployment:**
```bash
# Create Heroku account and install CLI
heroku login

# In backend directory
git init
git add .
git commit -m "Backend ready for deployment"

heroku create mealdb-backend-app
git push heroku main:main

# View logs
heroku logs --tail
```

**Frontend Deployment:**
```bash
# In frontend directory
npm run build

# Deploy to Vercel
npm install -g vercel
vercel

# Or deploy to Netlify
# Connect GitHub repo to Netlify dashboard
```

### Option 2: Deploy on Vercel + Railway

**Backend (Railway):**
1. Push code to GitHub
2. Connect GitHub repo to Railway
3. Set environment variables
4. Deploy

**Frontend (Vercel):**
1. Push code to GitHub
2. Connect GitHub repo to Vercel
3. Set API URL environment variable
4. Deploy

### Environment Variables for Deployment:
```
Backend:
PORT=5000
NODE_ENV=production

Frontend:
REACT_APP_API_URL=https://your-backend-url.vercel.app
```

---

## 📚 RESOURCES

### APIs & Documentation:
- TheMealDB: https://www.themealdb.com/api.php
- Postman: https://www.postman.com/
- Express.js: https://expressjs.com/
- React: https://react.dev/

### Tools:
- Git: https://git-scm.com/
- Node.js: https://nodejs.org/
- npm: https://www.npmjs.com/

### Deployment:
- Heroku: https://www.heroku.com/
- Vercel: https://vercel.com/
- Railway: https://railway.app/
- Netlify: https://www.netlify.com/

---

## 📞 SYNCHRONIZATION CHECKLIST

### Before Starting:
- [ ] Repository created and both members have access
- [ ] API response format agreed upon
- [ ] Base API URL defined
- [ ] Environment variables setup
- [ ] Development tools installed (Node.js, npm, git)

### Mid-Development:
- [ ] Person 1: First API endpoint ready for testing
- [ ] Person 2: UI structure created
- [ ] Both: Daily sync to track progress
- [ ] Both: Postman collection created and tested

### Final Integration:
- [ ] All backend endpoints working
- [ ] All frontend components complete
- [ ] Frontend successfully calls all backend endpoints
- [ ] No console errors or warnings
- [ ] Responsive design tested on mobile/desktop

### Pre-Hackathon:
- [ ] Both members can explain the entire project
- [ ] Demo rehearsed (2-3 minute walkthrough)
- [ ] Talking points memorized
- [ ] Live demo prepared (have internet connection)
- [ ] Backup: Screen recording ready
- [ ] Deployment successful and live
- [ ] Postman collection documented and saved

---

## 📋 SUCCESS CRITERIA

✅ **Technical:**
- App successfully searches meals from TheMealDB
- Correctly identifies meal with least ingredients
- Backend and frontend communicate seamlessly
- No bugs or errors during demo
- Responsive and user-friendly UI

✅ **Presentation:**
- Both members can explain the project clearly
- Can answer follow-up questions
- Demo is smooth and error-free
- Talking points are well-rehearsed
- Live deployment is active

✅ **Collaboration:**
- Work divided equally
- Code is clean and documented
- GitHub history shows both members' contributions
- No merge conflicts
- Regular commits from both members

---

**Good luck with your hackathon! 🎉**
