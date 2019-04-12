package com.example.hp.chuangyiapp.block.main.circle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.chuangyiapp.R;
import com.example.hp.chuangyiapp.base.BaseActivity;

public class NewsActivity extends BaseActivity {
    private static final String KEY_NEWS = "newsId";
    private String newsId;

    private TextView contentText;
    private TextView titleText;
    private TextView dateText;
    private ImageView contentImage;

    public static void startNewsActivity(Context context,String newsId){
        Intent intent = new Intent(context,NewsActivity.class);
        intent.putExtra(KEY_NEWS,newsId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newsId = getIntent().getStringExtra(KEY_NEWS);
        setContentView(R.layout.activity_news);
        initView();
    }

    private void initView() {
        titleText = findViewById(R.id.title_text);
        dateText = findViewById(R.id.date_text);
        contentText = findViewById(R.id.content_text);
        contentImage = findViewById(R.id.content_image);
    }
}
