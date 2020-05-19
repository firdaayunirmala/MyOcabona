package com.program.myocabona;

import com.program.myocabona.CategoryTabs.TabCategoryResponse;
import com.program.myocabona.CategoryJson.CategoryFragmentResponse;
import com.program.myocabona.DetailRecipe.DetailRecipeResponse;
import com.program.myocabona.HomePageJson.HomeCategoryResponse;
import com.program.myocabona.HomePageJson.LatestMealResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipeHomeApi {
    @GET("categories.php")
    Call<HomeCategoryResponse> getCategories();

    @GET("latest.php")
    Call<LatestMealResponse> getLatestMeal();

    @GET("list.php")
    Call<TabCategoryResponse> getTablist(@Query("c") String abc);

    @GET("filter.php")
    Call<CategoryFragmentResponse> getCategory(@Query("c") String foodItem);

    @GET("search.php")
    Call<DetailRecipeResponse> getRecipe(@Query("s") String Arrabiata);

    @GET("lookup.php")
    Call<DetailRecipeResponse> getDetailRecipe(@Query("i") Integer RecipeId);
}
