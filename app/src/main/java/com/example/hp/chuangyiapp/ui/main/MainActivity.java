package com.example.hp.chuangyiapp.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.hp.chuangyiapp.R;
import com.example.hp.chuangyiapp.adapter.MainViewPagerAdapter;
import com.example.hp.chuangyiapp.base.BaseActivity;
import com.example.hp.chuangyiapp.ui.login.LoginActivity;
import com.example.hp.chuangyiapp.ui.main.shop.ShopFragment;
import com.example.hp.chuangyiapp.ui.main.home.HomeFragment;
import com.example.hp.chuangyiapp.ui.main.circle.CircleFragment;
import com.example.hp.chuangyiapp.ui.main.mine.MineFragment;
import com.example.hp.chuangyiapp.utils.DialogUtil;
import com.example.hp.chuangyiapp.utils.LoginUtil;

import java.util.ArrayList;


public class MainActivity extends BaseActivity {

    private ViewPager viewPager;
    private LinearLayout home;
    private LinearLayout shop;
    private LinearLayout circle;
    private LinearLayout mine;
    private ImageView plus;

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private FragmentPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initViewPage();
    }


    private void initViewPage() {
        fragments.add(new HomeFragment());
        fragments.add(new ShopFragment());
        fragments.add(new CircleFragment());
        fragments.add(new MineFragment());
        pagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) { }
            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:select(home);break;
                    case 1:select(shop);break;
                    case 2:select(circle);break;
                    case 3:select(mine);break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int i) { }
        });

        select(home);
    }

    private void select(LinearLayout selectedOne) {
        home.setActivated(false);
        shop.setActivated(false);
        circle.setActivated(false);
        mine.setActivated(false);

        selectedOne.setActivated(true);
    }

    private void initView() {
        viewPager = findViewById(R.id.view_page);
        home = findViewById(R.id.bottom_home);
        shop = findViewById(R.id.bottom_shop);
        circle = findViewById(R.id.bottom_circle);
        mine = findViewById(R.id.bottom_mine);
        plus = findViewById(R.id.bottom_plus);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LoginUtil.isLogin())AddActivity.startAddActivity(MainActivity.this);
                else{
                    DialogUtil.showNormalDialog(MainActivity.this, "还未登陆，是否去登陆", new DialogUtil.OnClick() {
                        @Override
                        public void onClickPositive() {
                            LoginActivity.startLoginActivity(MainActivity.this);
                        }
                        @Override
                        public void onClickNegative() {
                            Toast.makeText(MainActivity.this,"登陆后更多功能~~",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);
            }
        });
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
            }
        });
        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(2);
            }
        });
        mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(3);
            }
        });
    }
}
