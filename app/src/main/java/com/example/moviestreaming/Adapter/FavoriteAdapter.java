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

import com.example.moviestreaming.Activities.ShowDetailMovieActivity;
import com.example.moviestreaming.Database.ModelDB.Favorite;
import com.example.moviestreaming.Interface.ItemClickListener;
import com.example.moviestreaming.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MyViewHolder> {

    Context context;
    List<Favorite> data;

    public FavoriteAdapter(Context context, List<Favorite> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_favorite, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Picasso.get().load(data.get(position).link_img).into(holder.img);
        holder.name.setText(data.get(position).name);
        holder.time.setText(data.get(position).time);
        holder.director.setText(new StringBuilder("Director: " + data.get(position).director));
        holder.published.setText(new StringBuilder("Published: " + data.get(position).published));
        holder.rate.setText(new StringBuilder("IMDb: " + data.get(position).rate_imdb));

        holder.setListener(new ItemClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShowDetailMovieActivity.class);
                intent.putExtra(ShowDetailMovieActivity.ID_DETAIL_ITEM, data.get(position).id);
                intent.putExtra(ShowDetailMovieActivity.CATEGORY_NAME, data.get(position).category_name);
                intent.putExtra(ShowDetailMovieActivity.NAME_DETAIL_ITEM, data.get(position).name);
                intent.putExtra(ShowDetailMovieActivity.DIRECTOR_DETAIL_ITEM, data.get(position).director);
                intent.putExtra(ShowDetailMovieActivity.RATE_IMDB_DETAIL_ITEM, data.get(position).rate_imdb);
                intent.putExtra(ShowDetailMovieActivity.TIME_DETAIL_ITEM, data.get(position).time);
                intent.putExtra(ShowDetailMovieActivity.PUBLISHED_DETAIL_ITEM, data.get(position).published);
                intent.putExtra(ShowDetailMovieActivity.IMG_DETAIL_ITEM, data.get(position).link_img);
                intent.putExtra(ShowDetailMovieActivity.GENRE_DETAIL_ITEM, data.get(position).genre_name);


                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name, time, director, published, rate;
        ImageView img, like;

        ItemClickListener listener;

        public void setListener(ItemClickListener listener) {
            this.listener = listener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            time = itemView.findViewById(R.id.time_movie);
            name = itemView.findViewById(R.id.name_movie);
            img = itemView.findViewById(R.id.img_movie);
            like = itemView.findViewById(R.id.like_movie);
            director = itemView.findViewById(R.id.name_director);
            published = itemView.findViewById(R.id.published);
            rate = itemView.findViewById(R.id.rate_movie);


            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            listener.onClick(view);
        }
    }

}
