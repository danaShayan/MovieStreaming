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

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.MyViewolder> {

    Context context;
    List<Genre> data;

    public GenreAdapter(Context context, List<Genre> data) {
        this.context = context;
        this.data = data;

    }

    @NonNull
    @Override
    public MyViewolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(context).inflate(R.layout.item_genre, parent, false);
        return new MyViewolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewolder holder, int position) {

        holder.name_genre.setText(data.get(position).getName());
        Picasso.get().load(data.get(position).getLink_img()).into(holder.img_genre);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewolder extends RecyclerView.ViewHolder {

        ImageView img_genre;
        TextView name_genre;

        public MyViewolder(@NonNull View itemView) {
            super(itemView);

            img_genre = itemView.findViewById(R.id.img_genre);
            name_genre = itemView.findViewById(R.id.name_genre);
        }
    }
}
