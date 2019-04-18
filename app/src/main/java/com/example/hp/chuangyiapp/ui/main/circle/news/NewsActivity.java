package com.example.hp.chuangyiapp.ui.main.circle.news;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hp.chuangyiapp.R;
import com.example.hp.chuangyiapp.adapter.NewsCommentAdapter;
import com.example.hp.chuangyiapp.base.BaseActivity;
import com.example.hp.chuangyiapp.ui.login.LoginActivity;
import com.example.hp.chuangyiapp.net.CampusFactory;
import com.example.hp.chuangyiapp.net.bean.NewsBean;
import com.example.hp.chuangyiapp.utils.DialogUtil;
import com.example.hp.chuangyiapp.utils.LoginUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.RequestBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NewsActivity extends BaseActivity {
    private static final String KEY_NEWS = "newsId";
    private int newsId;
    private NewsBean newsBean;

    private TextView contentText;
    private TextView titleText;
    private TextView dateText;
    private ImageView contentImage;

    private TextView postText;

    private RecyclerView recyclerView;
    private List<NewsBean.CommentsListBean> newsCommentBeans = new ArrayList<>();


    public static void startNewsActivity(Context context,int newsId){
        Intent intent = new Intent(context,NewsActivity.class);
        intent.putExtra(KEY_NEWS,newsId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newsId = getIntent().getIntExtra(KEY_NEWS,1);
        setContentView(R.layout.activity_news);
        initView();
        initData();
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
                if(!LoginUtil.isLogin()){
                    DialogUtil.showNormalDialog(NewsActivity.this, "还未登陆，是否去登陆", new DialogUtil.OnClick() {
                        @Override
                        public void onClickPositive() {
                            LoginActivity.startLoginActivity(NewsActivity.this);
                        }
                        @Override
                        public void onClickNegative() {
                            Toast.makeText(NewsActivity.this,"登陆后更多功能~~",Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {
                    CommentDialogFragment commentDialogFragment = new CommentDialogFragment();
                    commentDialogFragment.setOnPost(new CommentDialogFragment.OnPost() {
                        @Override
                        public void post(String s) {
                            postComment(s);
                        }
                    });
                    commentDialogFragment.show(getSupportFragmentManager(),"CommentDialogFragment");
                }
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
        CampusFactory.getRetrofitService().getNews(newsId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        whenRequestOut();
                    }

                    @Override
                    public void onNext(NewsBean newsBean) {
                        NewsActivity.this.newsBean = newsBean;
                        NewsActivity.this.newsCommentBeans.addAll(newsBean.getComments_list());
                        update();
                    }
                });
    }

    private void update() {
        titleText.setText(newsBean.getTitle());
        dateText.setText(newsBean.getTime());
        contentText.setText(newsBean.getContent());
        Glide.with(this).load(newsBean.getPhoto()).into(contentImage);

        recyclerView.getAdapter().notifyDataSetChanged();
    }

    private void postComment(String s) {
        Gson gson = new Gson();
        HashMap<String,String> paramsMap = new HashMap<>();
        paramsMap.put("content",s);
        String strEntity = gson.toJson(paramsMap);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"),strEntity);
        Log.i("post ",strEntity);

        CampusFactory.getRetrofitService().addComment(LoginUtil.getToken(),newsId,body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(NewsActivity.this,"发送成功",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(NewsActivity.this,"网络似乎丢失了~~",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Object o) {

                    }
                });
    }
}
