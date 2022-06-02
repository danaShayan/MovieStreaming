package com.example.moviestreaming.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviestreaming.Model.AllInformation;
import com.example.moviestreaming.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RandomAdapter extends RecyclerView.Adapter<RandomAdapter.MyViewHolder> {

    List<AllInformation> data;
    Context context;

    public RandomAdapter(List<AllInformation> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_new_movie, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.name.setText(data.get(position).getName());
        holder.time.setText(data.get(position).getTime());
        Picasso.get().load(data.get(position).getLink_img()).into(holder.img_movie);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView img_movie;
        TextView name, time;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img_movie = itemView.findViewById(R.id.img_top_movie_imdb);
            name = itemView.findViewById(R.id.name_top_movie_imdb);
            time = itemView.findViewById(R.id.time_top_movie_imdb);


        }
    }
}
