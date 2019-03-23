package com.example.hp.chuangyiapp.block.museum;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.hp.chuangyiapp.R;

public class MuseumActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView museumDetail;

    public static void startMuseumActivity(Context context){
        Intent intent = new Intent(context,MuseumActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum);

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
        museumDetail = findViewById(R.id.museum_detail);
        museumDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MuseumDetailActivity.startMuseumDetailActivity(MuseumActivity.this);
            }
        });
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
