package com.devhp.paging_lib_java_sample;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.devhp.paging_lib_java_sample.adapter.MovieAdapter;
import com.devhp.paging_lib_java_sample.controller.MyController;
import com.devhp.paging_lib_java_sample.controller.MyController2;
import com.devhp.paging_lib_java_sample.databinding.ActivityMainBinding;
import com.devhp.paging_lib_java_sample.model.entites.Movie;
import com.devhp.paging_lib_java_sample.model.entites.MovieDBResponse;
import com.devhp.paging_lib_java_sample.view.MyView;

public class MainActivity extends AppCompatActivity implements MyView {
    private MyController controller;

    private PagedList<Movie> movies;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.devhp.paging_lib_java_sample.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        movieAdapter = new MovieAdapter();
        binding.rcvMovies.setLayoutManager(new LinearLayoutManager(this));
        binding.rcvMovies.setAdapter(movieAdapter);
//        MyModel model = new MyModelImpl();
//        controller = new MyController(model, this);
//        controller.loadMovieResponse();

        MyController2 myController2 = new MyController2();
        myController2.getMoviesPagedList().observe(this, new Observer<PagedList<Movie>>() {
            @Override
            public void onChanged(PagedList<Movie> moviesFromLiveData) {
                movies = moviesFromLiveData;
                for (int i = 0; i < movies.size(); i++) {
                    Log.d("MyTagMovieTitle", i + ": " + String.valueOf(movies.get(i).getTitle()));
                }
                showOnRecyclerView();
            }
        });

    }

    private void showOnRecyclerView() {
        movieAdapter.submitList(movies);
    }

    @Override
    public void displayMovieResponse(MovieDBResponse movieDBResponse) {
//      runOnUiThread(() -> {
//          if (!movieDBResponse.getResults().isEmpty()) {
//              movieAdapter.setData(movieDBResponse.getResults());
//          }
//      });
    }

    @Override
    public void displayError(Exception e) {
//        runOnUiThread(() -> Toast.makeText(MainActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
//        Log.d("MyTag", "Current Thread:  " + Thread.currentThread().getName() + " Error: " + e.getMessage());
    }
}