package com.mealdb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Meal {
    private String id;
    private String name;
    private String image;
    private List<Ingredient> ingredients;
    private Integer ingredientCount;
    private String instructions;
    private String category;
    private String area;
    private String tags;
    private String youtubeUrl;
    private String source;
}
