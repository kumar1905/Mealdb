package com.mealdb.service;

import com.mealdb.model.Meal;
import com.mealdb.model.Ingredient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MealService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${mealdb.api.url}")
    private String mealDbApiUrl;

    /**
     * Search meals by name
     */
    public List<Meal> searchMealsByName(String mealName) {
        try {
            String url = mealDbApiUrl + "/search.php?s=" + mealName;
            log.info("Fetching meals from: {}", url);

            String response = restTemplate.getForObject(url, String.class);
            JsonNode root = objectMapper.readTree(response);

            if (root.has("meals") && !root.get("meals").isNull()) {
                JsonNode mealsNode = root.get("meals");
                List<Meal> meals = new ArrayList<>();

                for (JsonNode mealNode : mealsNode) {
                    Meal meal = parseMealFromJson(mealNode);
                    meals.add(meal);
                }

                // Sort by ingredient count (ascending)
                meals.sort(Comparator.comparingInt(Meal::getIngredientCount));
                return meals;
            }

            return Collections.emptyList();
        } catch (Exception e) {
            log.error("Error searching meals: {}", e.getMessage());
            throw new RuntimeException("Failed to search meals", e);
        }
    }

    /**
     * Get meal with the least ingredients
     */
    public Meal getMealWithLeastIngredients(String mealName) {
        List<Meal> meals = searchMealsByName(mealName);
        if (meals.isEmpty()) {
            return null;
        }
        return meals.get(0); // Already sorted by ingredient count
    }

    /**
     * Get random meal from TheMealDB
     */
    public Meal getRandomMeal() {
        try {
            String url = mealDbApiUrl + "/random.php";
            log.info("Fetching random meal from: {}", url);

            String response = restTemplate.getForObject(url, String.class);
            JsonNode root = objectMapper.readTree(response);

            if (root.has("meals") && !root.get("meals").isNull()) {
                JsonNode mealNode = root.get("meals").get(0);
                return parseMealFromJson(mealNode);
            }

            return null;
        } catch (Exception e) {
            log.error("Error fetching random meal: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch random meal", e);
        }
    }

    /**
     * Parse meal from JSON node
     */
    private Meal parseMealFromJson(JsonNode mealNode) {
        Meal meal = new Meal();
        meal.setId(mealNode.get("idMeal").asText());
        meal.setName(mealNode.get("strMeal").asText());
        meal.setImage(mealNode.get("strMealThumb").asText());
        meal.setInstructions(mealNode.get("strInstructions").asText());
        meal.setCategory(getTextOrNull(mealNode, "strCategory"));
        meal.setArea(getTextOrNull(mealNode, "strArea"));
        meal.setTags(getTextOrNull(mealNode, "strTags"));
        meal.setYoutubeUrl(getTextOrNull(mealNode, "strYoutube"));
        meal.setSource(getTextOrNull(mealNode, "strSource"));

        // Extract ingredients
        List<Ingredient> ingredients = extractIngredients(mealNode);
        meal.setIngredients(ingredients);
        meal.setIngredientCount(ingredients.size());

        return meal;
    }

    /**
     * Extract ingredients from meal JSON
     */
    private List<Ingredient> extractIngredients(JsonNode mealNode) {
        List<Ingredient> ingredients = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {
            String ingredientKey = "strIngredient" + i;
            String measureKey = "strMeasure" + i;

            JsonNode ingredientNode = mealNode.get(ingredientKey);
            if (ingredientNode != null && !ingredientNode.isNull()) {
                String ingredientName = ingredientNode.asText().trim();

                if (!ingredientName.isEmpty()) {
                    String measure = mealNode.get(measureKey).asText().trim();
                    Ingredient ingredient = new Ingredient(ingredientName, measure);
                    ingredients.add(ingredient);
                }
            }
        }

        return ingredients;
    }

    /**
     * Helper to get text or null
     */
    private String getTextOrNull(JsonNode node, String fieldName) {
        JsonNode field = node.get(fieldName);
        return (field != null && !field.isNull()) ? field.asText() : null;
    }
}
