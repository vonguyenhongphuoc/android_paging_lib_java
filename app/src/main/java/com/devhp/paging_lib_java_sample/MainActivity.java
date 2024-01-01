package com.devhp.paging_lib_java_sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.devhp.paging_lib_java_sample.controller.MyController;
import com.devhp.paging_lib_java_sample.model.MyModel;
import com.devhp.paging_lib_java_sample.model.MyModelImpl;
import com.devhp.paging_lib_java_sample.model.entites.MovieDBResponse;
import com.devhp.paging_lib_java_sample.view.MyView;

public class MainActivity extends AppCompatActivity implements MyView {
    private MyController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyModel model = new MyModelImpl();
        controller = new MyController(model, this);
        controller.loadMovieResponse();
    }

    @Override
    public void displayMovieResponse(MovieDBResponse movieDBResponse) {
        if (!movieDBResponse.getResults().isEmpty()) {
            for (int i = 0; i < movieDBResponse.getResults().size(); i++) {
                Log.d("MyTag", movieDBResponse.getResults().get(i).getOriginalTitle());
            }
        }
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