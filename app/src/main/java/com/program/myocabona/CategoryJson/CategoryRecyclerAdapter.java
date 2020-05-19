package com.program.myocabona.CategoryJson;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.program.myocabona.DetailActivity;
import com.program.myocabona.R;
import com.squareup.picasso.Picasso;

import java.util.List;
public class CategoryRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    List<CategoryFragment> food_item;


    public CategoryRecyclerAdapter(List<CategoryFragment> food_item) {
        this.food_item = food_item;
    }

    @Override
    public int getItemViewType(int position) {
        CategoryFragment data = food_item.get(position);
        String ciew = data.getType();
        if (ciew == null) {
            ciew = "";
        }

        if (ciew.isEmpty()) {
            return R.layout.category_list_items;
        } else {
            return R.layout.item_banner;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(i, viewGroup, false);
        if (i == R.layout.category_list_items) {
            return new Cat_VH(view);
        } else {
            return new BannerVH(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder cat_vh, int i) {

        if (food_item.get(i).getType().isEmpty()) {
            Cat_VH hols = (Cat_VH) cat_vh;
            CategoryFragment categoryFragment = food_item.get(i);
            hols.dish_title.setText(categoryFragment.getStrMeal());
            Picasso.with(hols.dish_title.getContext()).load(food_item.get(i).getStrMealThumb())
                    .into(hols.dish_img);
        } else {
            BannerVH hols = (BannerVH) cat_vh;
            Picasso.with(hols.des_img.getContext()).load(food_item.get(i).getStrMealThumb())
                    .into(hols.des_img);
        }
    }

    @Override
    public int getItemCount() {
        return food_item.size();
    }

    class Cat_VH extends RecyclerView.ViewHolder {
        ImageView des_img, dish_img;
        TextView des_title, dish_title;

        public Cat_VH(@NonNull final View itemView) {
            super(itemView);

            dish_img = (ImageView) itemView.findViewById(R.id.dish_img);
            des_title = (TextView) itemView.findViewById(R.id.des_title);
            dish_title = (TextView) itemView.findViewById(R.id.dish_title);

            dish_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String dishPosition = food_item.get(getAdapterPosition()).getIdMeal();

                    Toast.makeText(itemView.getContext(), dishPosition, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(itemView.getContext(), DetailActivity.class);

                    intent.putExtra("EXTRA_SESSION_ID", dishPosition);

                    itemView.getContext().startActivity(intent);

                }
            });
        }
    }

    class BannerVH extends RecyclerView.ViewHolder {
        ImageView des_img, dish_img;

        public BannerVH(@NonNull final View itemView) {
            super(itemView);
            des_img = (ImageView) itemView.findViewById(R.id.des_img);
        }
    }
}
