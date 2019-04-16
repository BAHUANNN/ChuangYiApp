package com.example.hp.chuangyiapp.block.main.circle.news;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.chuangyiapp.R;
import com.example.hp.chuangyiapp.base.BaseActivity;
import com.example.hp.chuangyiapp.net.bean.NewsBean;
import com.example.hp.chuangyiapp.net.bean.NewsCommentBean;

import java.util.ArrayList;

public class NewsActivity extends BaseActivity {
    private static final String KEY_NEWS = "newsId";
    private String newsId;
    private NewsBean newsBean;

    private TextView contentText;
    private TextView titleText;
    private TextView dateText;
    private ImageView contentImage;

    private TextView postText;

    private RecyclerView recyclerView;
    private ArrayList<NewsCommentBean> newsCommentBeans;


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
        initData();
        initView();
    }

    private void initView() {
        titleText = findViewById(R.id.title_text);
        dateText = findViewById(R.id.date_text);
        contentText = findViewById(R.id.content_text);
        contentImage = findViewById(R.id.content_image);
        initRecyclerView();

        postText = findViewById(R.id.post_text);
        postText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommentDialogFragment commentDialogFragment = new CommentDialogFragment();
                commentDialogFragment.setOnPost(new CommentDialogFragment.OnPost() {
                    @Override
                    public void post() {
                        postComment();
                    }
                });
                commentDialogFragment.show(getSupportFragmentManager(),"CommentDialogFragment");
            }
        });

    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                // 直接禁止垂直滑动
                return false;
            }
        };
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new NewsCommentAdapter(newsCommentBeans));
    }
    private void initData() {
        newsCommentBeans = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            newsCommentBeans.add(new NewsCommentBean());
        }
    }

    private void postComment() {

    }
}
