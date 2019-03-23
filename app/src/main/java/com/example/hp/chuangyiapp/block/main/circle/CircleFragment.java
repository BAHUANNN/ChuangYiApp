package com.example.hp.chuangyiapp.block.main.circle;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hp.chuangyiapp.R;
import com.example.hp.chuangyiapp.block.main.home.HomeAdapter;

import java.util.ArrayList;

public class CircleFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<String> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_message, container, false);
        initRecyclerView(root);
        return root;
    }

    private void initRecyclerView(View root) {
        recyclerView = root.findViewById(R.id.message_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        list = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            list.add("");
        }
        recyclerView.setAdapter(new HomeAdapter(list));
    }

}
