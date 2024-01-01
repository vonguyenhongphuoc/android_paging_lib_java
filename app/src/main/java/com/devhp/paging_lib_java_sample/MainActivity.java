package com.devhp.paging_lib_java_sample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.devhp.paging_lib_java_sample.adapter.MovieAdapter;
import com.devhp.paging_lib_java_sample.controller.MyController;
import com.devhp.paging_lib_java_sample.databinding.ActivityMainBinding;
import com.devhp.paging_lib_java_sample.model.MyModel;
import com.devhp.paging_lib_java_sample.model.MyModelImpl;
import com.devhp.paging_lib_java_sample.model.entites.MovieDBResponse;
import com.devhp.paging_lib_java_sample.view.MyView;

public class MainActivity extends AppCompatActivity implements MyView {
    private ActivityMainBinding binding;
    private MyController controller;

    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        movieAdapter = new MovieAdapter();
        binding.rcvMovies.setLayoutManager(new LinearLayoutManager(this));
        binding.rcvMovies.setAdapter(movieAdapter);
        MyModel model = new MyModelImpl();
        controller = new MyController(model, this);
        controller.loadMovieResponse();
    }

    @Override
    public void displayMovieResponse(MovieDBResponse movieDBResponse) {
      runOnUiThread(() -> {
          if (!movieDBResponse.getResults().isEmpty()) {
              movieAdapter.setData(movieDBResponse.getResults());
          }
      });
    }

    @Override
    public void displayError(Exception e) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        Log.d("MyTag", "Current Thread:  " + Thread.currentThread().getName() + " Error: " + e.getMessage());
    }
}