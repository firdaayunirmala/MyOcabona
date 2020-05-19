package com.program.myocabona;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.program.myocabona.HomePageJson.LatestMealResponse;
import com.program.myocabona.HomePageJson.MealViewPagerAdapter;

import com.program.myocabona.HomePageJson.HomeCategoryResponse;
import com.program.myocabona.HomePageJson.HomeCategoryRecyclerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class recyclerActivity extends AppCompatActivity {
    RecyclerView recycle;
    ViewPager viewPager;
    ImageView ic_fav,ic_share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        ic_fav= (ImageView)findViewById(R.id.ic_fav);
        ic_share = (ImageView)findViewById(R.id.ic_share);

        recycle = findViewById(R.id.recycle);
        recycle.setLayoutManager(new GridLayoutManager(this, 3));

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        getRecipe();
        getMeal();

        //Settin onClickListner on Favroite icon
        ic_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(MainActivity.this,FavoriteActivity.class);
                //startActivity(intent);
            }
        });

    }



    private void getRecipe() {

        RecipeHomeApi recipeHomeApi = BaseClient.getBaseClient().create(RecipeHomeApi.class);
        Call<HomeCategoryResponse> call = recipeHomeApi.getCategories();

        call.enqueue(new Callback<HomeCategoryResponse>() {
            @Override
            public void onResponse(Call<HomeCategoryResponse> call, Response<HomeCategoryResponse> response) {
                if (response.isSuccessful() && HttpURLConnection.HTTP_OK == response.code()) {
                    HomeCategoryResponse abc = response.body();
                    HomeCategoryRecyclerAdapter adapter = new HomeCategoryRecyclerAdapter(abc.getCategories());
                    recycle.setAdapter(adapter);
                } else {
                    Toast.makeText(recyclerActivity.this, "Unsucessful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<HomeCategoryResponse> call, Throwable t) {

                Toast.makeText(recyclerActivity.this, "Coba Lagir", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getMeal() {
        RecipeHomeApi recipeHomeApi1 = BaseClient.getBaseClient().create(RecipeHomeApi.class);
        Call<LatestMealResponse> callMeal = recipeHomeApi1.getLatestMeal();
        callMeal.enqueue(new Callback<LatestMealResponse>() {
            @Override
            public void onResponse(Call<LatestMealResponse> call, Response<LatestMealResponse> response) {

                if (response.isSuccessful() && HttpURLConnection.HTTP_OK == response.code()) {
                    final LatestMealResponse xyz = response.body();

                    final MealViewPagerAdapter adapter = new MealViewPagerAdapter(xyz.getMeals());
                    viewPager.setAdapter(adapter);
                    viewPager.setPadding(0, 0, 150, 0);

                    final ImageView viewPagerImages = (ImageView)findViewById(R.id.viewPagerImages);

                } else {
                    Toast.makeText(recyclerActivity.this, "Unscessfull", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LatestMealResponse> call, Throwable t) {
                Toast.makeText(recyclerActivity.this, "TryAgain", Toast.LENGTH_SHORT).show();

            }
        });
    }

    //Setting menu on MainActivity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Yakin akan keluar?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Jika ditekan ya, maka tutup aplikasi
                finish();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //jika ditekan no, maka kembali ke aplikasi
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}