package com.example.hp.chuangyiapp.block.main.circle;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hp.chuangyiapp.R;
import com.example.hp.chuangyiapp.net.bean.StateBean;

import java.util.List;

class StatesAdapter extends RecyclerView.Adapter<StatesAdapter.StateHolder> {
    private final List<StateBean> states;

    public StatesAdapter(List<StateBean> states) {
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

        public StateHolder(@NonNull View itemView) {
            super(itemView);
        }


        public void bind(StateBean stateBean) {

        }
    }
}
