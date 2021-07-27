package com.mobile.novi.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.novi.R;
import com.mobile.novi.activities.DetailMovieActivity;
import com.mobile.novi.adapter.NovelAdapter;
import com.mobile.novi.model.ModelNovel;


import java.util.ArrayList;
import java.util.List;

public class FragmentFavoriteNovel extends Fragment implements NovelAdapter.onSelectData {

    private RecyclerView rvMovieFav;
    private List<ModelNovel> modelNovel = new ArrayList<>();
    private TextView txtNoData;

    public FragmentFavoriteNovel() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favorite_novel, container, false);



        txtNoData = rootView.findViewById(R.id.tvNotFound);
        rvMovieFav = rootView.findViewById(R.id.rvMovieFav);
        rvMovieFav.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvMovieFav.setAdapter(new NovelAdapter(getActivity(), modelNovel, this));
        rvMovieFav.setHasFixedSize(true);



        return rootView;
    }



    @Override
    public void onSelected(ModelNovel modelNovel) {
        Intent intent = new Intent(getActivity(), DetailMovieActivity.class);
        intent.putExtra("detailMovie", modelNovel);
        startActivity(intent);
    }


}
