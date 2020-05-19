package com.program.myocabona;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.Toolbar;
import android.widget.Toast;

import com.program.myocabona.CategoryTabs.CategoryTabAdapter;
import com.program.myocabona.CategoryJson.FoodFragment;
import com.program.myocabona.CategoryTabs.TabCategoryList;
import com.program.myocabona.CategoryTabs.TabCategoryResponse;
import com.program.myocabona.R;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class CategoryActivity extends AppCompatActivity {
    List<TabCategoryList> tabCategoryLists;
    TabLayout tab;
    ViewPager vp;
    Toolbar category_toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        tab = findViewById(R.id.tab);
        vp = findViewById(R.id.CategoryViewPager);
        tab.setupWithViewPager(vp);

        //Adding toolbar and back button to toolbar
        category_toolbar = findViewById(R.id.category_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        getTabs();
    }


    private void getTabs() {
        // String id = getIntent().getStringExtra("Key_ID");

        RecipeHomeApi recipeHomeApi2 = BaseClient.getBaseClient().create(RecipeHomeApi.class);
        Call<TabCategoryResponse> call = recipeHomeApi2.getTablist("list");

        call.enqueue(new Callback<TabCategoryResponse>() {
            @Override
            public void onResponse(Call<TabCategoryResponse> call, Response<TabCategoryResponse> response) {
                if (response.isSuccessful() && HttpURLConnection.HTTP_OK == response.code()) {
                    TabCategoryResponse categoryResponse = response.body();
                    tabCategoryLists = new ArrayList<>();
                    tabCategoryLists = categoryResponse.getMeals();
                    setTabData(tabCategoryLists);
                } else {
                    Toast.makeText(CategoryActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TabCategoryResponse> call, Throwable t) {
                Toast.makeText(CategoryActivity.this, "Uncessfull", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setTabData(List<TabCategoryList> tabList) {
        List<String> titles = new ArrayList<>();
        List<Fragment> fragList = new ArrayList<>();
        for (TabCategoryList i:tabList) {
            titles.add(i.getStrCategory());
            Fragment fg = new FoodFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title",i.getStrCategory());
            fg.setArguments(bundle);
            fragList.add(fg);
        }
        CategoryTabAdapter adapter = new CategoryTabAdapter(getSupportFragmentManager(),titles,fragList);
        vp.setAdapter(adapter);
    }
}
