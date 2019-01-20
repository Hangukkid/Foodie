package com.example.hangukkid.foodie.API;

import java.util.List;

import unirest.HttpResponse;
import unirest.JsonNode;
import unirest.Unirest;

public class GetFoodData {
    private String queryString (String name, List<String> list) {
        StringBuilder s  = new StringBuilder (name + "=");
        for (String item : list) {
            s.append("%2" + item);
        }
        return s.toString();
    }
    public HttpResponse<JsonNode> searchRecipes (List<String> ing, Integer num) {
        String fillIngredients = "fillIngredients=false";
        String ingredients = queryString("ingredients", ing);
        String limitLicense = "limitLicense=false";
        String number = "number=" + Integer.toString(num);
        String ranking = "ranking=2";
        String link = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/findByIngredients?" + fillIngredients + "&" + ingredients + "&" + limitLicense + "&" + number + "&" + ranking;
        return Unirest.get("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/findByIngredients?fillIngredients=false&ingredients=apples%2Cflour%2Csugar&limitLicense=false&number=5&ranking=1")
                .header("X-Mashape-Key", "I0HS3fKlD8mshsgdvMAISLH06S6kp1QFYFxjsnCaW1a5LP9oiv")
                .header("Accept", "application/json")
                .asJson();
    }
}
