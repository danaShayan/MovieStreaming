package com.example.moviestreaming.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviestreaming.Model.Animation;
import com.example.moviestreaming.Model.NewMovie;
import com.example.moviestreaming.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AnimationAdapter extends RecyclerView.Adapter<AnimationAdapter.MyViewHolder> {


    List<Animation> data;
    Context context;

    public AnimationAdapter(Context context, List<Animation> data) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_animation, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.time.setText(data.get(position).getTime());
        holder.name.setText(data.get(position).getName());
        Picasso.get().load(data.get(position).getLink_img()).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, time;
        ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            time = itemView.findViewById(R.id.time_top_movie_imdb);
            name = itemView.findViewById(R.id.name_top_movie_imdb);
            img = itemView.findViewById(R.id.img_top_movie_imdb);

        }
    }
}
