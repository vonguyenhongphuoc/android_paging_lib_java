package com.devhp.paging_lib_java_sample.model;

import android.util.Log;

import com.devhp.paging_lib_java_sample.model.entites.MovieDBResponse;
import com.devhp.paging_lib_java_sample.service.Callback;
import com.devhp.paging_lib_java_sample.service.TMDBClient;
import com.devhp.paging_lib_java_sample.service.TMDBService;
import com.devhp.paging_lib_java_sample.util.Constant;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Response;

public class MyModelImpl implements MyModel {
    private final TMDBService tmdbService;
    private final ExecutorService executor;

    public MyModelImpl() {
        tmdbService = TMDBClient.getService();
        executor = Executors.newSingleThreadExecutor();
    }

    @Override
    public void getMovies(Callback<MovieDBResponse> callback) {
        executor.execute(
                () -> {
        Log.d("MyTagThread getMovies", Thread.currentThread().getName());
        try {
            Call<MovieDBResponse> call = tmdbService.getPopularMovies(Constant.API_KEY);
            Response<MovieDBResponse> response = call.execute();
            Log.d("MyTagThread response", Thread.currentThread().getName());
            if (response.isSuccessful()) {
                callback.onSuccess(response.body());
            } else {
                callback.onError(new Exception("Error: " + response.code()));
            }
        } catch (Exception e) {
            callback.onError(e);
        }
                }
        );
    }
}
