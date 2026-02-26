# MealDB App - Backend (Java/Spring Boot)

This is the backend API for the MealDB App hackathon project, built with **Spring Boot**.

## Prerequisites

- Java 17 or higher
- Maven 3.6+

## Setup

### 1. Install Dependencies
```bash
mvn clean install
```

### 2. Running the Application

**Development Mode (with auto-reload):**
```bash
mvn spring-boot:run
```

**Production Mode:**
```bash
mvn clean package
java -jar target/mealdb-backend-1.0.0.jar
```

The server will run on **http://localhost:8080/api**

## Project Structure

```
backend/
├── pom.xml                          # Maven configuration
├── src/main/
│   ├── java/com/mealdb/
│   │   ├── MealDbBackendApplication.java   # Main Spring Boot app
│   │   ├── config/
│   │   │   └── AppConfig.java             # CORS & Bean configs
│   │   ├── controller/
│   │   │   └── MealController.java        # REST endpoints
│   │   ├── service/
│   │   │   └── MealService.java           # Business logic
│   │   ├── model/
│   │   │   ├── Meal.java                  # Meal entity
│   │   │   └── Ingredient.java            # Ingredient entity
│   │   └── dto/
│   │       └── ApiResponse.java           # Response wrapper
│   └── resources/
│       └── application.properties          # Configuration
└── README.md
```

## API Endpoints

### 1. Search Meals
```
GET /api/meals/search?name=Arrabiata
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
        { "name": "Spaghetti", "measurement": "1 pound" },
        { "name": "Garlic", "measurement": "3 cloves" }
      ],
      "instructions": "..."
    }
  ]
}
```

### 2. Get Meal with Least Ingredients
```
GET /api/meals/least-ingredients?name=Arrabiata
```

Returns the meal requiring the **fewest ingredients**.

### 3. Get Random Meal
```
GET /api/meals/random
```

### 4. Health Check
```
GET /api/meals/health
```

## Configuration

Edit `src/main/resources/application.properties`:

```properties
# Server Port (default: 8080)
server.port=8080

# Context path
server.servlet.context-path=/api

# Logging
logging.level.root=INFO
logging.level.com.mealdb=DEBUG

# TheMealDB API
mealdb.api.url=https://www.themealdb.com/api/json/v1/1
```

## Technologies Used

- **Spring Boot 3.2.0** - Web framework
- **Spring WebFlux** - Reactive web client
- **Lombok** - Reduce boilerplate code
- **Jackson** - JSON processing
- **Maven** - Build tool

## Testing with Postman

Import the following requests:

1. **Search Meal**
   - Method: `GET`
   - URL: `http://localhost:8080/api/meals/search?name=Arrabiata`

2. **Least Ingredients**
   - Method: `GET`
   - URL: `http://localhost:8080/api/meals/least-ingredients?name=Arrabiata`

3. **Random Meal**
   - Method: `GET`
   - URL: `http://localhost:8080/api/meals/random`

4. **Health Check**
   - Method: `GET`
   - URL: `http://localhost:8080/api/meals/health`

## Key Features

✅ **RESTful API** - Clean REST endpoints
✅ **CORS Enabled** - Works with React frontend
✅ **Exception Handling** - Proper error responses
✅ **Logging** - Debug and monitor requests
✅ **Spring Bean Management** - Dependency injection
✅ **External API Integration** - TheMealDB API calls

## Common Issues & Solutions

**Issue:** Port 8080 already in use
**Solution:** Change port in `application.properties`:
```properties
server.port=8081
```

**Issue:** SSL certificate error when calling TheMealDB API
**Solution:** Update Java SSL certificate (uncommon, but add if needed)

**Issue:** CORS errors from frontend
**Solution:** Verify frontend URL is in `AppConfig.java` corsConfigurationSource()

## Deployment

### Heroku
```bash
git push heroku main
```

### Railway / Vercel
- Upload JAR file to cloud platform
- Set Java 17 as runtime
- Expose port 8080

## Next Steps

1. ✅ Backend API is ready
2. ⏳ Test endpoints with Postman
3. ⏳ Integrate with React frontend
4. ⏳ Deploy on cloud platform

---

**Person 1 (Backend Developer):** This is your main file! Build all endpoints and business logic here.
