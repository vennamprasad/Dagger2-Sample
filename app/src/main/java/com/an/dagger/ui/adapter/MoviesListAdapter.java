package com.an.dagger.ui.adapter;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.an.dagger.R;
import com.an.dagger.data.local.entity.MovieEntity;
import com.an.dagger.databinding.MoviesListItemBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.CustomViewHolder> {

    private Activity activity;
    private List<MovieEntity> movies;

    public MoviesListAdapter(Activity activity) {
        this.activity = activity;
        this.movies = new ArrayList<>();
    }

    @NonNull
    @Override
    public MoviesListAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        MoviesListItemBinding itemBinding = MoviesListItemBinding.inflate(layoutInflater, parent, false);
        return new CustomViewHolder(itemBinding);
    }

    public void setItems(List<MovieEntity> movies) {
        int startPosition = this.movies.size();
        this.movies.addAll(movies);
        notifyItemRangeChanged(startPosition, movies.size());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public MovieEntity getItem(int position) {
        return movies.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesListAdapter.CustomViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        private MoviesListItemBinding binding;

        CustomViewHolder(MoviesListItemBinding binding) {
            super(binding.image);
            this.binding = binding;

            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = displayMetrics.widthPixels;

            itemView.setLayoutParams(new RecyclerView.LayoutParams(Float.valueOf(width * 0.85f).intValue(),
                    RecyclerView.LayoutParams.WRAP_CONTENT));
        }

        void bindTo(MovieEntity movie) {
            Picasso.get().load(movie.getPosterPath())
                    .placeholder(R.drawable.ic_placeholder)
                    .into(binding.image);
        }
    }
}
