package com.program.myocabona.Fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.gridlayout.widget.GridLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import com.program.myocabona.Adapters.MainMenuAdapter;
import com.program.myocabona.Listeners.OnItemClickedListener;

import com.program.myocabona.R;
import com.program.myocabona.Utils.ItemObjects;
import com.program.myocabona.recyclerActivity;


import butterknife.BindView;
import butterknife.ButterKnife;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment implements OnItemClickedListener {
    public HomeFragment() {}
    @BindView(R.id.lst_main_menu)
    RecyclerView lstMainMenu;

    List<ItemObjects> items;
    MainMenuAdapter adapter;
    GridLayoutManager layoutManager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        inisializeData();

        return view;

    }

    private void inisializeData() {
        layoutManager = new GridLayoutManager(getActivity(), 3);

        items = new ArrayList<>();
        items.add(new ItemObjects(R.mipmap.ic_makan, "Makan", "Makanan"));
        items.add(new ItemObjects(R.mipmap.ic_minum, "Minum", "Minuman"));
        items.add(new ItemObjects(R.mipmap.ic_kosmetik, "Kosmetik", "Kosmetik"));
        adapter = new MainMenuAdapter(this, getActivity(), items);

        lstMainMenu.setLayoutManager(layoutManager);
        lstMainMenu.setAdapter(adapter);
    }

    @Override
    public void onClick(int position) {
        Toast.makeText(getActivity(), items.get(position).getDesc(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity().getApplication(), recyclerActivity.class);
        startActivity(intent);
    }


}
