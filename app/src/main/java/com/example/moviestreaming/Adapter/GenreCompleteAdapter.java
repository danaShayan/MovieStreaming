package com.example.moviestreaming.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviestreaming.Model.Genre;
import com.example.moviestreaming.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GenreCompleteAdapter extends RecyclerView.Adapter<GenreCompleteAdapter.MyViewHolder> {

    Context context;
    List<Genre> data;

    public GenreCompleteAdapter(Context context, List<Genre> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_genre_complete, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Picasso.get().load(data.get(position).getLink_img()).into(holder.img_genre);
        holder.name_genre.setText(data.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView img_genre;
        TextView name_genre;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img_genre = itemView.findViewById(R.id.img_genre);
            name_genre = itemView.findViewById(R.id.name_genre);
        }
    }
}
