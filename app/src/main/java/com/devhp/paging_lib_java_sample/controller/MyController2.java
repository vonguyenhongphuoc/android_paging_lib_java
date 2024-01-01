package com.devhp.paging_lib_java_sample.controller;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.devhp.paging_lib_java_sample.model.MovieDataSource;
import com.devhp.paging_lib_java_sample.model.MovieDataSourceFactory;
import com.devhp.paging_lib_java_sample.model.entites.Movie;
import com.devhp.paging_lib_java_sample.service.TMDBClient;
import com.devhp.paging_lib_java_sample.service.TMDBService;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MyController2 {
    MovieDataSourceFactory factory;
    LiveData<MovieDataSource> movieDataSourceLiveData;
    private Executor executor;
    private LiveData<PagedList<Movie>> moviesPagedList;

    public MyController2() {
        TMDBService tmdbService = TMDBClient.getService();
        factory  = new MovieDataSourceFactory(tmdbService);
        movieDataSourceLiveData = factory.getMutableLiveData();
        PagedList.Config config = (new PagedList.Config.Builder()).setEnablePlaceholders(true).setInitialLoadSizeHint(3).setPageSize(20).setPrefetchDistance(4).build();
        executor = Executors.newFixedThreadPool(5);

        moviesPagedList = (new LivePagedListBuilder<Long, Movie>(factory, config)).setFetchExecutor(executor).build();

    }


    public LiveData<PagedList<Movie>> getMoviesPagedList(){
        return moviesPagedList;
    }


}
