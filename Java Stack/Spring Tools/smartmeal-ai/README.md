# SmartMeal AI

Complete Spring Boot starter project based on the 10-page wireframe.

## Included
- 10 responsive pages with Tailwind CSS CDN
- Spring Boot REST API
- AJAX/fetch updates without page reload
- Registration/login with BCrypt password hashing and HttpSession
- Meal CRUD (add/history/delete)
- Weekly food analysis
- Rule-based meal recommendations (ready to replace with Gemini/OpenAI)
- Restaurant search and seeded sample data
- H2 database by default + MySQL profile

## Run in Spring Tool Suite
1. Extract the ZIP.
2. File → Import → Existing Maven Projects.
3. Select the `smartmeal-ai` folder.
4. Run `SmartMealApplication` as Spring Boot App.
5. Open `http://localhost:8080`.

## MySQL
Create/update credentials in `application-mysql.properties`, then run with:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=mysql
```

## Main API endpoints
- POST `/api/auth/register`
- POST `/api/auth/login`
- POST `/api/auth/logout`
- GET/PUT `/api/profile`
- GET `/api/meals/today`
- GET `/api/meals/history`
- POST `/api/meals`
- DELETE `/api/meals/{id}`
- GET `/api/analysis/summary`
- GET `/api/recommendations`
- GET `/api/restaurants?q=healthy`

## AI note
The project includes a working local recommendation engine, so it runs without paid API keys. To use Gemini/OpenAI, replace `RecommendationService` with an HTTP client call and keep the same API response structure.
