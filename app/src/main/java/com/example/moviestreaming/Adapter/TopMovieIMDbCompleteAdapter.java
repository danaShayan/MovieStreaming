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
import com.example.moviestreaming.Global.Global;
import com.example.moviestreaming.Interface.ItemClickListener;
import com.example.moviestreaming.Model.TopMovieIMDb;
import com.example.moviestreaming.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TopMovieIMDbCompleteAdapter extends RecyclerView.Adapter<TopMovieIMDbCompleteAdapter.MyViewHolder> {


    List<TopMovieIMDb> data;
    Context context;

    public TopMovieIMDbCompleteAdapter(Context context, List<TopMovieIMDb> data) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_top_movie_imdb_complete_activity, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.time.setText(data.get(position).getTime());
        holder.name.setText(data.get(position).getName());
        holder.rank.setText(new StringBuilder("Rank: " + data.get(position).getRank()));
        Picasso.get().load(data.get(position).getLink_img()).into(holder.img);
        holder.director.setText(new StringBuilder("Director: " + data.get(position).getDirector()));
        holder.rate.setText(new StringBuilder("IMDb: " + data.get(position).getRate_imdb()));
        holder.published.setText(new StringBuilder("Published: " + data.get(position).getPublished()));

        holder.setListener(new ItemClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShowDetailMovieActivity.class);
                intent.putExtra(ShowDetailMovieActivity.ID_DETAIL_ITEM, data.get(position).getId());
                intent.putExtra(ShowDetailMovieActivity.CATEGORY_NAME, data.get(position).getCategory_name());
                intent.putExtra(ShowDetailMovieActivity.NAME_DETAIL_ITEM, data.get(position).getName());
                intent.putExtra(ShowDetailMovieActivity.DIRECTOR_DETAIL_ITEM, data.get(position).getDirector());
                intent.putExtra(ShowDetailMovieActivity.RATE_IMDB_DETAIL_ITEM, data.get(position).getRate_imdb());
                intent.putExtra(ShowDetailMovieActivity.TIME_DETAIL_ITEM, data.get(position).getTime());
                intent.putExtra(ShowDetailMovieActivity.PUBLISHED_DETAIL_ITEM, data.get(position).getPublished());
                intent.putExtra(ShowDetailMovieActivity.IMG_DETAIL_ITEM, data.get(position).getLink_img());
                intent.putExtra(ShowDetailMovieActivity.GENRE_DETAIL_ITEM, data.get(position).getGenre());


                context.startActivity(intent);
            }
        });


        // condition Favorite

        if (Global.favoriteRepository.isFavorite(Integer.parseInt(data.get(position).getId())) == 1)
            holder.like.setImageResource(R.drawable.ic_baseline_favorite_24);
        else
            holder.like.setImageResource(R.drawable.ic_baseline_favorite_border_24);

        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Global.favoriteRepository.isFavorite(Integer.parseInt(data.get(position).getId())) != 1) {
                    holder.like.setImageResource(R.drawable.ic_baseline_favorite_24);

                    Favorite favorite = new Favorite();

                    favorite.id = data.get(position).getId();
                    favorite.category_name = data.get(position).getCategory_name();
                    favorite.director = data.get(position).getDirector();
                    favorite.genre_name = data.get(position).getGenre();
                    favorite.link_img = data.get(position).getLink_img();
                    favorite.name = data.get(position).getName();
                    favorite.published = data.get(position).getPublished();
                    favorite.rate_imdb = data.get(position).getRate_imdb();
                    favorite.time = data.get(position).getTime();

                    Global.favoriteRepository.insertFavoriteItem(favorite);
                } else {
                    holder.like.setImageResource(R.drawable.ic_baseline_favorite_border_24);

                    Favorite favorite = new Favorite();

                    favorite.id = data.get(position).getId();
                    favorite.category_name = data.get(position).getCategory_name();
                    favorite.director = data.get(position).getDirector();
                    favorite.genre_name = data.get(position).getGenre();
                    favorite.link_img = data.get(position).getLink_img();
                    favorite.name = data.get(position).getName();
                    favorite.published = data.get(position).getPublished();
                    favorite.rate_imdb = data.get(position).getRate_imdb();
                    favorite.time = data.get(position).getTime();

                    Global.favoriteRepository.deleteFavoriteItem(favorite);


                }


            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, time, director, published, rate, rank;
        ImageView img, like;

        ItemClickListener listener;

        public void setListener(ItemClickListener listener) {
            this.listener = listener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            time = itemView.findViewById(R.id.time_movie);
            name = itemView.findViewById(R.id.name_movie);
            rank = itemView.findViewById(R.id.rank_movie);
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
