package com.devhp.paging_lib_java_sample.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.devhp.paging_lib_java_sample.databinding.MovieItemBinding;
import com.devhp.paging_lib_java_sample.model.entites.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> movieList = new ArrayList<>();

    public void setData(List<Movie> newList){
        this.movieList = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        MovieItemBinding binding = MovieItemBinding.inflate(layoutInflater,parent,false);
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        private final MovieItemBinding binding;

        MovieViewHolder(MovieItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Movie movie){
            String imagePath="https://image.tmdb.org/t/p/w500"+movie.getPosterPath();
            Glide.with(binding.ivPosterPath.getContext()).load(imagePath).into(binding.ivPosterPath);
            binding.tvMovieTitle.setText(movie.getTitle());
            binding.tvMovieOverView.setText(movie.getOverview());
            binding.tvReleaseData.setText(movie.getReleaseDate());
            binding.tvVoteAverage.setText(String.valueOf(movie.getVoteAverage()));
        }


    }
}
