package com.example.moviestreaming.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviestreaming.Activities.ShowGenreActivity;
import com.example.moviestreaming.Interface.ItemClickListener;
import com.example.moviestreaming.Model.Genre;
import com.example.moviestreaming.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.MyViewHolder> {

    Context context;
    List<Genre> data;

    public GenreAdapter(Context context, List<Genre> data) {
        this.context = context;
        this.data = data;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(context).inflate(R.layout.item_genre, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.name_genre.setText(data.get(position).getName());
        Picasso.get().load(data.get(position).getLink_img()).into(holder.img_genre);

        holder.setListener(new ItemClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShowGenreActivity.class);
                intent.putExtra("name", data.get(position).getName());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView img_genre;
        TextView name_genre;

        ItemClickListener listener;


        public void setListener(ItemClickListener listener) {
            this.listener = listener;
        }


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img_genre = itemView.findViewById(R.id.img_genre);
            name_genre = itemView.findViewById(R.id.name_genre);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            listener.onClick(view);
        }
    }
}
