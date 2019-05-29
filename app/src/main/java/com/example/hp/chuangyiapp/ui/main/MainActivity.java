package com.example.hp.chuangyiapp.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.hp.chuangyiapp.App;
import com.example.hp.chuangyiapp.R;
import com.example.hp.chuangyiapp.adapter.MainViewPagerAdapter;
import com.example.hp.chuangyiapp.base.BaseActivity;
import com.example.hp.chuangyiapp.ui.login.LoginActivity;
import com.example.hp.chuangyiapp.ui.main.shop.ShopFragment;
import com.example.hp.chuangyiapp.ui.main.home.HomeFragment;
import com.example.hp.chuangyiapp.ui.main.circle.CircleFragment;
import com.example.hp.chuangyiapp.ui.main.mine.MineFragment;
import com.example.hp.chuangyiapp.utils.DialogUtil;
import com.example.hp.chuangyiapp.utils.FileUtils;
import com.example.hp.chuangyiapp.utils.LoginUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import dalvik.system.DexClassLoader;

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
        loadDexClass();
        initView();
        initViewPage();
    }

    private void loadDexClass() {
        File cacheFile = FileUtils.getCacheDir(App.getInstance());
        String internalPath = cacheFile.getAbsolutePath() + File.separator + "Dynamic.dex";
        File desFile = new File(internalPath);
        try {
            if (!desFile.exists()) {
                desFile.createNewFile();
                // 从assets目录下 copy 文件到 app/data/cache目录
                FileUtils.copyFiles(App.getInstance(), "Dynamic.dex", desFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 这里由于是加载 jar文件，所以采用DexClassLoader
        //下面开始加载dex class
        DexClassLoader dexClassLoader = new DexClassLoader(internalPath, cacheFile.getAbsolutePath(), null, getClassLoader());
        try {
            // 类加载器负责读取 .class文件，并把它转为 Class实例，这个实例就表示一个java类
            // 加载 dex文件中的Class，格式是：包名+类名（全类名）
            Class libClazz = dexClassLoader.loadClass("hp.test.Dynamic");
            // 调用Class的 newInstance()方法，创建Class的对象 dynamic
            // Dynamic 是 dex文件中之前的一个接口类
            IDynamic dynamic = (IDynamic) libClazz.newInstance();
            if (dynamic != null)
                Toast.makeText(this, dynamic.say(), Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
