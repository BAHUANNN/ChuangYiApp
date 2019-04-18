package com.example.hp.chuangyiapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp.chuangyiapp.R;
import com.example.hp.chuangyiapp.net.bean.StatesBean;

import java.util.List;

public class StatesAdapter extends RecyclerView.Adapter<StatesAdapter.StateHolder> {
    private final List<StatesBean.FeedlistBean> states;

    public StatesAdapter(List<StatesBean.FeedlistBean> states) {
        this.states = states;
    }

    @NonNull
    @Override
    public StateHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_state_item,viewGroup,false);
        return new StateHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StateHolder stateHolder, int i) {
        stateHolder.bind(states.get(i));
    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    class StateHolder extends RecyclerView.ViewHolder {

        private TextView idText;
        private TextView contentText;
        private TextView dataText;

        public StateHolder(@NonNull View itemView) {
            super(itemView);
            idText = itemView.findViewById(R.id.user_id_text);
            contentText = itemView.findViewById(R.id.content_text);
            dataText = itemView.findViewById(R.id.date_text);
        }


        public void bind(StatesBean.FeedlistBean stateBean) {
            idText.setText(stateBean.getUsername());
            contentText.setText(stateBean.getContent());
            dataText.setText(stateBean.getTime());
        }
    }
}
