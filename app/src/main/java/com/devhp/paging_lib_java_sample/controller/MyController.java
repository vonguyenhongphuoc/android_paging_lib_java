package com.devhp.paging_lib_java_sample.controller;

import com.devhp.paging_lib_java_sample.model.MyModel;
import com.devhp.paging_lib_java_sample.model.entites.MovieDBResponse;
import com.devhp.paging_lib_java_sample.service.Callback;
import com.devhp.paging_lib_java_sample.view.MyView;

public class MyController {
    private final MyModel myModel;
    private final MyView myView;

    public MyController(MyModel myModel, MyView myView) {
        this.myModel = myModel;
        this.myView = myView;
    }

    public void loadMovieResponse(){
        myModel.getMovies(new Callback<MovieDBResponse>() {
            @Override
            public void onSuccess(MovieDBResponse result) {
                myView.displayMovieResponse(result);
            }

            @Override
            public void onError(Exception e) {
                myView.displayError(e);
            }
        });
    }
}
