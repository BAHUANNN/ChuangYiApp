package com.example.hp.chuangyiapp.block.main.circle;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hp.chuangyiapp.R;
import com.example.hp.chuangyiapp.base.BaseFragment;
import com.example.hp.chuangyiapp.block.main.MainViewPagerAdapter;
import com.example.hp.chuangyiapp.block.main.home.HomeAdapter;
import com.example.hp.chuangyiapp.block.main.home.HomeFragment;
import com.example.hp.chuangyiapp.block.main.shop.ShopFragment;
import com.example.hp.chuangyiapp.block.web.WebActivity;

import java.util.ArrayList;

public class CircleFragment extends BaseFragment {

    private static final String CONTRIBUTE_WEBSITE = "https://www.wjx.top/jq/36781591.aspx";

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<Fragment> fragments;
    private ImageView headView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_circle, container, false);
        initRecyclerView(root);
        return root;
    }

    private void initRecyclerView(View root) {
        viewPager = root.findViewById(R.id.viewpager);
        fragments = new ArrayList<>();
        fragments.add(new StatesFragment());
        fragments.add(new NewsFragment());
        viewPager.setAdapter(new MainViewPagerAdapter(getChildFragmentManager(),fragments));

        tabLayout = root.findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("动态");
        tabLayout.getTabAt(1).setText("新闻");

        headView = root.findViewById(R.id.head_image);
        headView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebActivity.startWebActivity(getContext(),CONTRIBUTE_WEBSITE);
            }
        });
    }

}
