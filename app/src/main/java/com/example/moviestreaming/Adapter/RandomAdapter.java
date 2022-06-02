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

import com.example.moviestreaming.Activities.ShowDetailsSeriesActivity;
import com.example.moviestreaming.Interface.ItemClickListener;
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

        holder.setListener(new ItemClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, ShowDetailsSeriesActivity.class);

                intent.putExtra(ShowDetailsSeriesActivity.ID_DETAIL_ITEM, data.get(position).getId());
                intent.putExtra(ShowDetailsSeriesActivity.CATEGORY_NAME, data.get(position).getCategory_name());
                intent.putExtra(ShowDetailsSeriesActivity.NAME_DETAIL_ITEM, data.get(position).getName());
                intent.putExtra(ShowDetailsSeriesActivity.DIRECTOR_DETAIL_ITEM, data.get(position).getDirector());
                intent.putExtra(ShowDetailsSeriesActivity.PUBLISHED_DETAIL_ITEM, data.get(position).getPublished());
                intent.putExtra(ShowDetailsSeriesActivity.TIME_DETAIL_ITEM, data.get(position).getTime());
                intent.putExtra(ShowDetailsSeriesActivity.RATE_IMDB_DETAIL_ITEM, data.get(position).getRate_imdb());
                intent.putExtra(ShowDetailsSeriesActivity.IMG_DETAIL_ITEM, data.get(position).getLink_img());
                context.startActivity(intent);


            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView img_movie;
        TextView name, time;


        ItemClickListener listener;

        public void setListener(ItemClickListener listener) {
            this.listener = listener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img_movie = itemView.findViewById(R.id.img_top_movie_imdb);
            name = itemView.findViewById(R.id.name_top_movie_imdb);
            time = itemView.findViewById(R.id.time_top_movie_imdb);


            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            listener.onClick(view);
        }
    }
}
