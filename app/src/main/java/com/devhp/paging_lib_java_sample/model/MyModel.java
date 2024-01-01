package com.devhp.paging_lib_java_sample.model;


import com.devhp.paging_lib_java_sample.model.entites.MovieDBResponse;
import com.devhp.paging_lib_java_sample.service.Callback;
import java.util.List;
public interface  MyModel {
    void getMovies(Callback<MovieDBResponse> callback);
}
