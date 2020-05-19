package com.program.myocabona.Adapters;

import android.content.Context;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import com.program.myocabona.Listeners.OnItemClickedListener;
import com.program.myocabona.R;
import com.program.myocabona.Utils.ItemObjects;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuAdapter.MainMenuHolder>{
    List<ItemObjects> items;
    Context context;
    OnItemClickedListener itemClickedListener;

    public MainMenuAdapter(OnItemClickedListener itemClickedListener, Context context, List<ItemObjects> items) {
        this.itemClickedListener = itemClickedListener;
        this.context = context;
        this.items = items;
    }

    @Override
    public MainMenuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainMenuHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item, null));
    }

    @Override
    public void onBindViewHolder(MainMenuHolder holder, final int position) {
        holder.imgProduct.setImageResource(items.get(position).getImg());
        holder.button1.setText(items.get(position).getTitle());
        holder.cardItemMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickedListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class MainMenuHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.card_item_menu)
        CardView cardItemMenu;

        @BindView(R.id.img_product)
        ImageView imgProduct;

        @BindView(R.id.button1)
        TextView button1;

        public MainMenuHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

