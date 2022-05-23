package com.example.moviestreaming.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.moviestreaming.Model.Slider;
import com.example.moviestreaming.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SliderAdapter extends PagerAdapter {

    Context context;
    List<Slider> data;

    public SliderAdapter(Context context, List<Slider> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.slider_item, container, false);
        ImageView img_slider = view.findViewById(R.id.imageSlider);
        TextView name_slider = view.findViewById(R.id.nameSlider);
        TextView time_slider = view.findViewById(R.id.timeSlider);
        TextView published_slider = view.findViewById(R.id.publishedSlider);

        name_slider.setText(data.get(position).getName());
        time_slider.setText(data.get(position).getTime());
        published_slider.setText(data.get(position).getPublished());
        Picasso.get().load(data.get(position).getLink_img()).into(img_slider);


        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
