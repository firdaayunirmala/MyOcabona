package com.program.myocabona.CategoryJson;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.program.myocabona.BaseClient;
import com.program.myocabona.RecipeHomeApi;
import com.program.myocabona.R;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class FoodFragment extends Fragment {
    String title;
    RecyclerView CategoryRecycle;
    CategoryRecyclerAdapter adapter;

    public FoodFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle data = getArguments();
        title = data.getString("title");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_tab_food, container, false);


        CategoryRecycle = (RecyclerView) view.findViewById(R.id.CategoryRecycle);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2
                , LinearLayoutManager.VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0) {
                    return 2;
                } else return 1;
            }
        });
        CategoryRecycle.setLayoutManager(gridLayoutManager);
        getFoodItem();

        return view;
    }

    private void getFoodItem() {
        RecipeHomeApi recipeHomeApi = BaseClient.getBaseClient().create(RecipeHomeApi.class);
        Call<CategoryFragmentResponse> call = recipeHomeApi.getCategory(title);
        call.enqueue(new Callback<CategoryFragmentResponse>() {
            @Override
            public void onResponse(Call<CategoryFragmentResponse> call, Response<CategoryFragmentResponse> response) {

                if (response.isSuccessful() && HttpURLConnection.HTTP_OK == response.code()) {

                    CategoryFragmentResponse psb = response.body();
                    List<CategoryFragment> sdf = new ArrayList<>();
                    CategoryFragment sd = new CategoryFragment();
                    sd.setType("Bannner");
                    sd.setStrMealThumb("https://www.themealdb.com/images/category/beef.png");
                    sdf.add(sd);
                    sdf.addAll(psb.getMeals());
                    adapter = new CategoryRecyclerAdapter(sdf);
                    CategoryRecycle.setAdapter(adapter);
                } else {
                    Toast.makeText(getActivity(), "Uncessfull", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CategoryFragmentResponse> call, Throwable t) {

                Toast.makeText(getActivity(), "Try Again", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
