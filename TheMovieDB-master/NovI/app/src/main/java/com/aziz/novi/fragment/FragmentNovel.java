package com.aziz.novi.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.aziz.novi.R;
import com.aziz.novi.activities.DetailMovieActivity;
import com.aziz.novi.adapter.NovelAdapter;
import com.aziz.novi.adapter.NovelHorizontalAdapter;
import com.aziz.novi.model.ModelNovel;
import com.aziz.novi.networking.ApiEndpoint;
import com.ramotion.cardslider.CardSliderLayoutManager;
import com.ramotion.cardslider.CardSnapHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FragmentNovel extends Fragment implements NovelHorizontalAdapter.onSelectData, NovelAdapter.onSelectData {

    private RecyclerView rvNowPlaying, rvFilmRecommend;
    private NovelHorizontalAdapter novelHorizontalAdapter;
    private NovelAdapter novelAdapter;
    private ProgressDialog progressDialog;
    private SearchView searchFilm;
    private List<ModelNovel> moviePlayNow = new ArrayList<>();
    private List<ModelNovel> moviePopular = new ArrayList<>();

    public FragmentNovel() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_film, container, false);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Mohon Tunggu");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sedang menampilkan data");

        searchFilm = rootView.findViewById(R.id.searchFilm);
        searchFilm.setQueryHint(getString(R.string.search_novel));
        searchFilm.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                setSearchMovie(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.equals(""))
                    getMovie();
                return false;
            }
        });

        int searchPlateId = searchFilm.getContext().getResources()
                .getIdentifier("android:id/search_plate", null, null);
        View searchPlate = searchFilm.findViewById(searchPlateId);
        if (searchPlate != null) {
            searchPlate.setBackgroundColor(Color.TRANSPARENT);
        }

        rvNowPlaying = rootView.findViewById(R.id.rvNowPlaying);
        rvNowPlaying.setHasFixedSize(true);
        rvNowPlaying.setLayoutManager(new CardSliderLayoutManager(getActivity()));
        new CardSnapHelper().attachToRecyclerView(rvNowPlaying);

        rvFilmRecommend = rootView.findViewById(R.id.rvFilmRecommend);
        rvFilmRecommend.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvFilmRecommend.setHasFixedSize(true);

        getMovieHorizontal();
        getMovie();

        return rootView;
    }

    private void setSearchMovie(String query) {
        progressDialog.show();
        AndroidNetworking.get(ApiEndpoint.BASEURL + ApiEndpoint.SEARCH + query
//                + ApiEndpoint.SEARCH_MOVIE
//                + ApiEndpoint.APIKEY + ApiEndpoint.LANGUAGE + ApiEndpoint.QUERY + query
                )
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            progressDialog.dismiss();
                            moviePopular = new ArrayList<>();
                            JSONArray jsonArray = response.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                ModelNovel dataApi = new ModelNovel();
                                SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMMM yyyy");
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
//                                String datePost = jsonObject.getString("release_date");
                                String datePost = jsonObject.getString("created_at");

                                dataApi.setId(jsonObject.getInt("id"));
//                                dataApi.setTitle(jsonObject.getString("title"));
                                dataApi.setTitle(jsonObject.getString("name"));
//                                dataApi.setVoteAverage(jsonObject.getDouble("vote_average"));
                                dataApi.setVoteAverage(jsonObject.getDouble("favorit"));
//                                dataApi.setOverview(jsonObject.getString("overview"));
                                dataApi.setOverview(jsonObject.getString("sinopsis"));
                                dataApi.setReleaseDate(formatter.format(dateFormat.parse(datePost)));
//                                dataApi.setPosterPath(jsonObject.getString("poster_path"));
                                dataApi.setPosterPath(jsonObject.getString("cover"));
//                                dataApi.setBackdropPath(jsonObject.getString("backdrop_path"));
                                dataApi.setBackdropPath(jsonObject.getString("cover"));
//                                dataApi.setPopularity(jsonObject.getString("popularity"));
                                dataApi.setPopularity(jsonObject.getString("favorit"));
                                moviePopular.add(dataApi);
                                showMovie();
                            }
                        } catch (JSONException | ParseException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void getMovieHorizontal() {
        progressDialog.show();
        AndroidNetworking.get(ApiEndpoint.BASEURL + ApiEndpoint.TITLE
//                + ApiEndpoint.MOVIE_PLAYNOW + ApiEndpoint.APIKEY + ApiEndpoint.LANGUAGE
                )
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            progressDialog.dismiss();
                            JSONArray jsonArray = response.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                ModelNovel dataApi = new ModelNovel();
                                SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMMM yyyy");
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
//                                String datePost = jsonObject.getString("release_date");
                                String datePost = jsonObject.getString("created_at");

                                dataApi.setId(jsonObject.getInt("id"));
//                                dataApi.setTitle(jsonObject.getString("title"));
                                dataApi.setTitle(jsonObject.getString("name"));
//                                dataApi.setVoteAverage(jsonObject.getDouble("vote_average"));
                                dataApi.setVoteAverage(jsonObject.getDouble("favorit"));
//                                dataApi.setOverview(jsonObject.getString("overview"));
                                dataApi.setOverview(jsonObject.getString("sinopsis"));
                                dataApi.setReleaseDate(formatter.format(dateFormat.parse(datePost)));
//                                dataApi.setPosterPath(jsonObject.getString("poster_path"));
                                dataApi.setPosterPath(jsonObject.getString("cover"));
//                                dataApi.setBackdropPath(jsonObject.getString("backdrop_path"));
                                dataApi.setBackdropPath(jsonObject.getString("cover"));
//                                dataApi.setPopularity(jsonObject.getString("popularity"));
                                dataApi.setPopularity(jsonObject.getString("favorit"));
                                moviePlayNow.add(dataApi);
                                showMovieHorizontal();
                            }
                        } catch (JSONException | ParseException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void getMovie() {
        progressDialog.show();
        AndroidNetworking.get(ApiEndpoint.BASEURL + ApiEndpoint.TITLE
//                + ApiEndpoint.MOVIE_POPULAR + ApiEndpoint.APIKEY + ApiEndpoint.LANGUAGE
                )
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            progressDialog.dismiss();
                            moviePopular = new ArrayList<>();
                            JSONArray jsonArray = response.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                ModelNovel dataApi = new ModelNovel();
                                SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMMM yyyy");
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                                //                                String datePost = jsonObject.getString("release_date");
                                String datePost = jsonObject.getString("created_at");

                                dataApi.setId(jsonObject.getInt("id"));
//                                dataApi.setTitle(jsonObject.getString("title"));
                                dataApi.setTitle(jsonObject.getString("name"));
//                                dataApi.setVoteAverage(jsonObject.getDouble("vote_average"));
                                dataApi.setVoteAverage(jsonObject.getDouble("favorit"));
//                                dataApi.setOverview(jsonObject.getString("overview"));
                                dataApi.setOverview(jsonObject.getString("sinopsis"));
                                dataApi.setReleaseDate(formatter.format(dateFormat.parse(datePost)));
//                                dataApi.setPosterPath(jsonObject.getString("poster_path"));
                                dataApi.setPosterPath(jsonObject.getString("cover"));
//                                dataApi.setBackdropPath(jsonObject.getString("backdrop_path"));
                                dataApi.setBackdropPath(jsonObject.getString("cover"));
//                                dataApi.setPopularity(jsonObject.getString("popularity"));
                                dataApi.setPopularity(jsonObject.getString("favorit"));
                                moviePopular.add(dataApi);
                                showMovie();
                            }
                        } catch (JSONException | ParseException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showMovieHorizontal() {
        novelHorizontalAdapter = new NovelHorizontalAdapter(getActivity(), moviePlayNow, this);
        rvNowPlaying.setAdapter(novelHorizontalAdapter);
        novelHorizontalAdapter.notifyDataSetChanged();
    }

    private void showMovie() {
        novelAdapter = new NovelAdapter(getActivity(), moviePopular, this);
        rvFilmRecommend.setAdapter(novelAdapter);
        novelAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSelected(ModelNovel modelNovel) {
        Intent intent = new Intent(getActivity(), DetailMovieActivity.class);
        intent.putExtra("detailMovie", modelNovel);
        startActivity(intent);
    }
}