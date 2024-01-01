package com.devhp.paging_lib_java_sample.view;

import com.devhp.paging_lib_java_sample.model.entites.MovieDBResponse;

public interface MyView {
    void displayMovieResponse(MovieDBResponse movieDBResponse);
    void displayError(Exception e);
}
