package com.example.hp.chuangyiapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp.chuangyiapp.R;
import com.example.hp.chuangyiapp.net.bean.NewsBean;

import java.util.List;

public class NewsCommentAdapter  extends RecyclerView.Adapter<NewsCommentAdapter.NewsCommentHolder> {
    private final List<NewsBean.CommentsListBean> newsComments;

    public NewsCommentAdapter(List<NewsBean.CommentsListBean> newsComments) {
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

        private TextView nameText;
        private TextView dateText;
        private TextView contentText;

        public NewsCommentHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.user_id_text);
            dateText = itemView.findViewById(R.id.date_text);
            contentText = itemView.findViewById(R.id.content_text);
        }


        public void bind(NewsBean.CommentsListBean newsCommentBean) {
            nameText.setText(newsCommentBean.getUsername());
            dateText.setText(newsCommentBean.getTime());
            contentText.setText(newsCommentBean.getContent());
        }
    }
}
