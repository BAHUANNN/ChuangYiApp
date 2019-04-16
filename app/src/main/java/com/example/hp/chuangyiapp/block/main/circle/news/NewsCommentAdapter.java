package com.example.hp.chuangyiapp.block.main.circle.news;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hp.chuangyiapp.R;
import com.example.hp.chuangyiapp.net.bean.NewsCommentBean;

import java.util.List;

public class NewsCommentAdapter  extends RecyclerView.Adapter<NewsCommentAdapter.NewsCommentHolder> {
    private final List<NewsCommentBean> newsComments;

    public NewsCommentAdapter(List<NewsCommentBean> newsComments) {
        this.newsComments = newsComments;
    }

    @NonNull
    @Override
    public NewsCommentHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_comment_item,viewGroup,false);
        return new NewsCommentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsCommentHolder newsCommentHolder, int i) {
        newsCommentHolder.bind(newsComments.get(i));
    }


    @Override
    public int getItemCount() {
        return newsComments.size();
    }

    class NewsCommentHolder extends RecyclerView.ViewHolder {

        public NewsCommentHolder(@NonNull View itemView) {
            super(itemView);
        }


        public void bind(NewsCommentBean newsCommentBean) {

        }
    }
}
