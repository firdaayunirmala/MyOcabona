package com.program.myocabona.HomePageJson;

import java.util.List;

public class LatestMealResponse {
    List<LatestItem> meals;

    public List<LatestItem> getMeals() {
        return meals;
    }

    public void setMeals(List<LatestItem> meals) {
        this.meals = meals;
    }

}
