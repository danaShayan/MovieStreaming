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
import com.example.moviestreaming.Interface.ItemClickListener;
import com.example.moviestreaming.Model.TopMovieIMDb;
import com.example.moviestreaming.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.zip.Inflater;

public class TopMovieIMDbAdapter extends RecyclerView.Adapter<TopMovieIMDbAdapter.MyViewHolder> {


    List<TopMovieIMDb> data;
    Context context;

    public TopMovieIMDbAdapter(Context context, List<TopMovieIMDb> data) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_top_movie_imdb, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.time.setText(data.get(position).getTime());
        holder.name.setText(data.get(position).getName());
        holder.rank.setText(new StringBuilder("Rank: " + data.get(position).getRank()));
        Picasso.get().load(data.get(position).getLink_img()).into(holder.img);

        holder.setListener(new ItemClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShowDetailMovieActivity.class);
                intent.putExtra(ShowDetailMovieActivity.ID_DETAIL_ITEM, data.get(position).getId());
                intent.putExtra(ShowDetailMovieActivity.CATEGORY_NAME, data.get(position).getCategory_name());
                intent.putExtra(ShowDetailMovieActivity.ID_DETAIL_ITEM, data.get(position).getId());
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

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, time, rank;
        ImageView img;

        ItemClickListener listener;

        public void setListener(ItemClickListener listener) {
            this.listener = listener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            time = itemView.findViewById(R.id.time_top_movie_imdb);
            name = itemView.findViewById(R.id.name_top_movie_imdb);
            rank = itemView.findViewById(R.id.rank_movie);
            img = itemView.findViewById(R.id.img_top_movie_imdb);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            listener.onClick(view);
        }
    }
}
