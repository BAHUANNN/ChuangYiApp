package com.example.hp.chuangyiapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.hp.chuangyiapp.R;
import com.example.hp.chuangyiapp.ui.museum.MuseumActivity;
import com.example.hp.chuangyiapp.ui.web.WebActivity;
import com.example.base.net.bean.HomeItemBean;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeHolder> {

    private ArrayList<HomeItemBean> list;
    public static final String FLAG = "no_url";

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

        public void bind(final HomeItemBean homeItemBean) {
            Glide.with(imageView.getContext()).load(homeItemBean.getDrawableId()).into(imageView);
            if(homeItemBean.getUrl() == FLAG){
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MuseumActivity.startMuseumActivity(imageView.getContext());
                    }
                });
            }else {
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WebActivity.startWebActivity(imageView.getContext(),homeItemBean.getUrl());
                    }
                });
            }
        }
    }
}
