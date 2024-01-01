package com.devhp.paging_lib_java_sample.service;

import com.devhp.paging_lib_java_sample.model.entites.MovieDBResponse;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TMDBService {

    @GET("movie/popular")
    Call<MovieDBResponse> getPopularMovies(@Query("api_key") String apiKey, @Query("page") long page);
}
