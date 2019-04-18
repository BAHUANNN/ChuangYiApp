package com.example.hp.chuangyiapp.ui.main.shop;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.hp.chuangyiapp.R;
import com.example.hp.chuangyiapp.base.BaseFragment;
import com.example.hp.chuangyiapp.ui.web.WebActivity;

public class ShopFragment extends BaseFragment {
    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_shop, container, false);
        imageView = root.findViewById(R.id.shop_image);
        Glide.with(this).load(R.drawable.shop_background).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebActivity.startWebActivity(getContext(),"https://k.weidian.com/CzcLldMO");
            }
        });
        return root;
    }
}
