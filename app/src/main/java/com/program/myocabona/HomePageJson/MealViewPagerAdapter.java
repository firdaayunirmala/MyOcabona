package com.program.myocabona.HomePageJson;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.program.myocabona.DetailActivity;
import com.program.myocabona.R;
import com.squareup.picasso.Picasso;

import java.util.List;
public class MealViewPagerAdapter extends PagerAdapter  {
    List<LatestItem> latestItems;

    public MealViewPagerAdapter(List<LatestItem> latestItems) {
        this.latestItems = latestItems;
    }

    @Override
    public int getCount() {
        return latestItems.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {
        final View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.home_viewpager_item, container, false);

        ImageView viewPagerImages = (ImageView) view.findViewById(R.id.viewPagerImages);
        TextView viewPagerTitle = (TextView) view.findViewById(R.id.viewPagerTitle);

        viewPagerTitle.setText(latestItems.get(position).getStrMeal());

        Picasso.with(container.getContext()).load(latestItems.get(position).getStrMealThumb())
                .into(viewPagerImages);
        container.addView(view, 0);

        viewPagerImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String position1 = latestItems.get(position).getIdMeal();
                //Toast.makeText(container.getContext(), position1, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(container.getContext(),DetailActivity.class);
                intent.putExtra("EXTRA_SESSION_ID",position1);
                container.getContext().startActivity(intent);


            }
        });
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
