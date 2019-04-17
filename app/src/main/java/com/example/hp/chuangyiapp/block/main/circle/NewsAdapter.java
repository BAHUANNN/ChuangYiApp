package com.example.hp.chuangyiapp.block.main.circle;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hp.chuangyiapp.R;
import com.example.hp.chuangyiapp.block.main.circle.news.NewsActivity;
import com.example.hp.chuangyiapp.net.bean.NewsBean;
import com.example.hp.chuangyiapp.net.bean.NewssBean;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

    private List<NewssBean.NewslistBean> list;

    public NewsAdapter(List<NewssBean.NewslistBean> list){this.list = list;}

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_news_item,viewGroup,false);
        NewsHolder holder = new NewsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder newsHolder, int i) {
        newsHolder.bind(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class NewsHolder extends RecyclerView.ViewHolder{

        private View itemView;
        private TextView titleText;
        private TextView contentText;
        private TextView dateText;
        private TextView commentText;
        private ImageView contentImage;

        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            titleText = itemView.findViewById(R.id.title_text);
            contentText = itemView.findViewById(R.id.content_text);
            dateText = itemView.findViewById(R.id.date_text);
            commentText = itemView.findViewById(R.id.comment_text);
            contentImage = itemView.findViewById(R.id.content_image);
        }

        public void bind(final NewssBean.NewslistBean newBean) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NewsActivity.startNewsActivity(titleText.getContext(),newBean.getNews_id());
                }
            });
            titleText.setText(newBean.getTitle());
            contentText.setText(newBean.getContent());
            dateText.setText(newBean.getTime());
            commentText.setText(newBean.getCommentsnum()+"条评论 > ");
            Glide.with(contentImage.getContext()).load(newBean.getPhoto()).into(contentImage);
        }
    }
}
