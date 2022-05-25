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

import com.example.moviestreaming.Activities.EpisodesActivity;
import com.example.moviestreaming.Interface.ItemClickListener;
import com.example.moviestreaming.Model.Season;
import com.example.moviestreaming.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SeasonAdapter extends RecyclerView.Adapter<SeasonAdapter.MyViewHolder> {

    Context context;
    List<Season> data = new ArrayList<>();

    public SeasonAdapter(Context context, List<Season> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_season, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.count_episodes.setText(new StringBuilder("Episodes: " + data.get(position).getCount_episodes()));
        holder.season_number.setText(new StringBuilder("Season " + data.get(position).getNumber_season()));
        Picasso.get().load(data.get(position).getLink_img_seaon()).into(holder.img);


        holder.setListener(new ItemClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EpisodesActivity.class);
                intent.putExtra(EpisodesActivity.ID_SEASON, data.get(position).getId());
                intent.putExtra(EpisodesActivity.SEASON_NUMBER, data.get(position).getNumber_season());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView img;
        TextView season_number, count_episodes;

        ItemClickListener listener;

        public void setListener(ItemClickListener listener) {
            this.listener = listener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.season_img);
            season_number = itemView.findViewById(R.id.number_season);
            count_episodes = itemView.findViewById(R.id.count_episodes);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            listener.onClick(view);
        }
    }
}