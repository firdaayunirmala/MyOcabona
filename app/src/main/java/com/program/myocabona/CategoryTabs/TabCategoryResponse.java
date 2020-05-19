package com.program.myocabona.CategoryTabs;

import java.util.List;
public class TabCategoryResponse {
    List<TabCategoryList> meals;

    public List<TabCategoryList> getMeals() {
        return meals;
    }

    public void setMeals(List<TabCategoryList> meals) {
        this.meals = meals;
    }
}
