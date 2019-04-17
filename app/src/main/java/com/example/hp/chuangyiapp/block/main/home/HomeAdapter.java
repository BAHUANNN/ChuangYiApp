package com.example.hp.chuangyiapp.block.main.home;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.hp.chuangyiapp.App;
import com.example.hp.chuangyiapp.R;
import com.example.hp.chuangyiapp.block.museum.MuseumActivity;
import com.example.hp.chuangyiapp.net.bean.HomeItemBean;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeHolder> {

    private ArrayList<HomeItemBean> list;

    public HomeAdapter(ArrayList<HomeItemBean> list){
        this.list = list;
    }

    @NonNull
    @Override
    public HomeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_home_item,viewGroup,false);
        HomeHolder holder = new HomeHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeHolder homeHolder, int i) {
        homeHolder.bind(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class HomeHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;

        public HomeHolder(@NonNull final View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MuseumActivity.startMuseumActivity(itemView.getContext());
                }
            });
        }

        public void bind(HomeItemBean homeItemBean) {
            Glide.with(imageView.getContext()).load(homeItemBean.getDrawableId()).into(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MuseumActivity.startMuseumActivity(imageView.getContext());
                }
            });
        }
    }
}
