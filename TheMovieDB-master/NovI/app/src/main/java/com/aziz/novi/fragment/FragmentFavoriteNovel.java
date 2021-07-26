package com.aziz.novi.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aziz.novi.R;
import com.aziz.novi.activities.DetailMovieActivity;
import com.aziz.novi.adapter.NovelAdapter;
import com.aziz.novi.model.ModelNovel;
import com.aziz.novi.realm_.RealmHelper;

import java.util.ArrayList;
import java.util.List;

public class FragmentFavoriteNovel extends Fragment implements NovelAdapter.onSelectData {

    private RecyclerView rvMovieFav;
    private List<ModelNovel> modelNovel = new ArrayList<>();
    private RealmHelper helper;
    private TextView txtNoData;

    public FragmentFavoriteNovel() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favorite_film, container, false);

//        helper = new RealmHelper(getActivity());

        txtNoData = rootView.findViewById(R.id.tvNotFound);
        rvMovieFav = rootView.findViewById(R.id.rvMovieFav);
        rvMovieFav.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvMovieFav.setAdapter(new NovelAdapter(getActivity(), modelNovel, this));
        rvMovieFav.setHasFixedSize(true);

//        setData();

        return rootView;
    }

//    private void setData() {
//        modelMovie = helper.showFavoriteMovie();
//        if (modelMovie.size() == 0) {
//            txtNoData.setVisibility(View.VISIBLE);
//            rvMovieFav.setVisibility(View.GONE);
//        } else {
//            txtNoData.setVisibility(View.GONE);
//            rvMovieFav.setVisibility(View.VISIBLE);
//            rvMovieFav.setAdapter(new MovieAdapter(getActivity(), modelMovie, this));
//        }
//    }

    @Override
    public void onSelected(ModelNovel modelNovel) {
        Intent intent = new Intent(getActivity(), DetailMovieActivity.class);
        intent.putExtra("detailMovie", modelNovel);
        startActivity(intent);
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        setData();
//    }
}
