package com.example.moviestreaming.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviestreaming.Model.Series;
import com.example.moviestreaming.Model.TopMovieIMDb;
import com.example.moviestreaming.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SeriesCompleteAdapter extends RecyclerView.Adapter<SeriesCompleteAdapter.MyViewHolder> {


    List<Series> data;
    Context context;

    public SeriesCompleteAdapter(Context context, List<Series> data) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_series_complete_activity, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.time.setText(data.get(position).getTime());
        holder.name.setText(data.get(position).getName());
        Picasso.get().load(data.get(position).getLink_img()).into(holder.img);
        holder.director.setText(new StringBuilder("Director: " + data.get(position).getDirector()));
        holder.rate.setText(new StringBuilder("IMDb: " + data.get(position).getRate_imdb()));
        holder.published.setText(new StringBuilder("Episodes: " + data.get(position).getPublished()));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, time, director, published, rate;
        ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            time = itemView.findViewById(R.id.time_movie);
            name = itemView.findViewById(R.id.name_movie);
            img = itemView.findViewById(R.id.img_movie);
            director = itemView.findViewById(R.id.name_director);
            published = itemView.findViewById(R.id.published);
            rate = itemView.findViewById(R.id.rate_movie);
        }
    }
}
