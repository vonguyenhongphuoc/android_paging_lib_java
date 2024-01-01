package com.devhp.paging_lib_java_sample.model;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.devhp.paging_lib_java_sample.service.TMDBService;

public class MovieDataSourceFactory extends DataSource.Factory {

    private final TMDBService tmdbService;

    private final MutableLiveData<MovieDataSource> mutableLiveData;

    public MovieDataSourceFactory( TMDBService tmdbService) {
        this.tmdbService = tmdbService;
        mutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource create() {
        MovieDataSource movieDataSource = new MovieDataSource(tmdbService);
        mutableLiveData.postValue(movieDataSource);
        return movieDataSource;
    }

    public LiveData<MovieDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
