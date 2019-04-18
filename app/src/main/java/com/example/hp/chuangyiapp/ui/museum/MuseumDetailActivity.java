package com.example.hp.chuangyiapp.ui.museum;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.hp.chuangyiapp.R;
import com.example.hp.chuangyiapp.base.BaseActivity;
import com.example.hp.chuangyiapp.adapter.MainViewPagerAdapter;

import java.util.ArrayList;

public class MuseumDetailActivity extends BaseActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView headImage;

    public static void startMuseumDetailActivity(Context context){
        Intent intent = new Intent(context,MuseumDetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum_detail);
        initView();
        initToolbar();
    }


    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
    }

    private void initView() {


        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new IntroduceFragment());
        list.add(new ExhibitionsFragment());
        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager(),list));

        tabLayout = findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("场馆");
        tabLayout.getTabAt(1).setText("展讯");

        headImage = findViewById(R.id.head_image);
        headImage.setImageBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.museum_background));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
