package com.devhp.paging_lib_java_sample.model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.devhp.paging_lib_java_sample.model.entites.Movie;
import com.devhp.paging_lib_java_sample.model.entites.MovieDBResponse;
import com.devhp.paging_lib_java_sample.service.TMDBClient;
import com.devhp.paging_lib_java_sample.service.TMDBService;
import com.devhp.paging_lib_java_sample.util.Constant;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource extends PageKeyedDataSource<Long, Movie> {
    private TMDBService tmdbService;

    public MovieDataSource(TMDBService tmdbService) {
        this.tmdbService = tmdbService;
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> loadParams, @NonNull LoadCallback<Long, Movie> loadCallback) {
        tmdbService = TMDBClient.getService();
        Call<MovieDBResponse> call = tmdbService.getPopularMovies(Constant.API_KEY, loadParams.key);
        call.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                Log.d("MyTag LoadAfter loading page: " , String.valueOf(loadParams.key));
                MovieDBResponse movieDBResponse = response.body();
                ArrayList<Movie> movies;
                if (movieDBResponse != null && movieDBResponse.getResults() != null) {
                    movies = (ArrayList<Movie>) movieDBResponse.getResults();
                    loadCallback.onResult(movies, loadParams.key + 1);
                }

            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> loadParams, @NonNull LoadCallback<Long, Movie> loadCallback) {

    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> loadInitialParams, @NonNull LoadInitialCallback<Long, Movie> loadInitialCallback) {
        tmdbService = TMDBClient.getService();
        Call<MovieDBResponse> call = tmdbService.getPopularMovies(Constant.API_KEY, 1);
        call.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                MovieDBResponse movieDBResponse = response.body();
                ArrayList<Movie> movies;
                if (movieDBResponse != null && movieDBResponse.getResults() != null) {
                    movies = (ArrayList<Movie>) movieDBResponse.getResults();
                    loadInitialCallback.onResult(movies, null, (long) 2);
                }


            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {

            }
        });

    }
}
