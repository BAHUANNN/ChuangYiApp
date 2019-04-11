package com.example.hp.chuangyiapp.block.main.circle;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hp.chuangyiapp.R;
import com.example.hp.chuangyiapp.base.BaseFragment;
import com.example.hp.chuangyiapp.net.bean.StateBean;

import java.util.ArrayList;
import java.util.List;

public class StatesFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private List<StateBean> states;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_states, container, false);
        initRecyclerView(root);
        return root;
    }

    private void initRecyclerView(View root) {
        initData();
        recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new StatesAdapter(states));
    }

    private void initData() {
        states = new ArrayList<>();
    }
}
