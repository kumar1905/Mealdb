package com.mealdb.controller;

import com.mealdb.model.Meal;
import com.mealdb.dto.ApiResponse;
import com.mealdb.service.MealService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meals")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"})
public class MealController {

    private final MealService mealService;

    /**
     * Health check endpoint
     */
    @GetMapping("/health")
    public ResponseEntity<ApiResponse<String>> health() {
        return ResponseEntity.ok(new ApiResponse<>(true, "MealDB Backend is running!", "OK"));
    }

    /**
     * Search meals by name
     * GET /api/meals/search?name=Arrabiata
     */
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Meal>>> searchMeals(@RequestParam String name) {
        log.info("Searching for meals with name: {}", name);

        try {
            List<Meal> meals = mealService.searchMealsByName(name);

            if (meals.isEmpty()) {
                return ResponseEntity.ok(new ApiResponse<>(false, "No meals found for: " + name));
            }

            return ResponseEntity.ok(new ApiResponse<>(meals));
        } catch (Exception e) {
            log.error("Error searching meals: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Error searching meals: " + e.getMessage()));
        }
    }

    /**
     * Get meal with least ingredients
     * GET /api/meals/least-ingredients?name=Arrabiata (optional)
     */
    @GetMapping("/least-ingredients")
    public ResponseEntity<ApiResponse<Meal>> getMealWithLeastIngredients(
            @RequestParam(required = false) String name) {
        log.info("Fetching meal with least ingredients for: {}", name != null ? name : "any");

        try {
            if (name == null || name.isEmpty()) {
                // If no name provided, get a random meal
                Meal meal = mealService.getRandomMeal();
                if (meal == null) {
                    return ResponseEntity.ok(new ApiResponse<>(false, "Could not fetch meal"));
                }
                return ResponseEntity.ok(new ApiResponse<>(meal));
            }

            Meal meal = mealService.getMealWithLeastIngredients(name);
            if (meal == null) {
                return ResponseEntity.ok(new ApiResponse<>(false, "No meals found for: " + name));
            }

            return ResponseEntity.ok(new ApiResponse<>(meal));
        } catch (Exception e) {
            log.error("Error fetching meal with least ingredients: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Error fetching meal: " + e.getMessage()));
        }
    }

    /**
     * Get random meal
     * GET /api/meals/random
     */
    @GetMapping("/random")
    public ResponseEntity<ApiResponse<Meal>> getRandomMeal() {
        log.info("Fetching random meal");

        try {
            Meal meal = mealService.getRandomMeal();

            if (meal == null) {
                return ResponseEntity.ok(new ApiResponse<>(false, "Could not fetch random meal"));
            }

            return ResponseEntity.ok(new ApiResponse<>(meal));
        } catch (Exception e) {
            log.error("Error fetching random meal: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Error fetching random meal: " + e.getMessage()));
        }
    }
}
