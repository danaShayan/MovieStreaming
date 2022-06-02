package com.example.moviestreaming.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviestreaming.Activities.ShowDetailMovieActivity;
import com.example.moviestreaming.Activities.VideoPlayActivity;
import com.example.moviestreaming.Model.Episode;
import com.example.moviestreaming.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.MyViewHolder> {

    Context context;
    List<Episode> data = new ArrayList<>();


    public EpisodeAdapter(Context context, List<Episode> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_episode, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.time.setText(data.get(position).getTime());
        holder.detail.setText(data.get(position).getDetail());
        holder.name.setText(data.get(position).getName());

        Picasso.get().load(data.get(position).getLink_img()).into(holder.img);


        holder.btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDetailMovieActivity.LINK_MOVIE = data.get(position).getLink_play_episode();
                if (!ShowDetailMovieActivity.LINK_MOVIE.equals("")) {
                    Intent intent = new Intent(context, VideoPlayActivity.class);
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView img, download;
        TextView name, detail, time;
        FloatingActionButton btn_play;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img_episode);
            download = itemView.findViewById(R.id.download);
            name = itemView.findViewById(R.id.name_episode);
            detail = itemView.findViewById(R.id.detail_episode);
            time = itemView.findViewById(R.id.time);
            btn_play = itemView.findViewById(R.id.play);


        }

    }
}
