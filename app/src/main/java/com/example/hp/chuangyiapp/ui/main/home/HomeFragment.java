package com.example.hp.chuangyiapp.ui.main.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp.chuangyiapp.R;
import com.example.hp.chuangyiapp.adapter.HomeAdapter;
import com.example.hp.chuangyiapp.base.BaseFragment;
import com.example.hp.chuangyiapp.net.bean.HomeItemBean;

import java.util.ArrayList;

public class HomeFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private TextView searchText;

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    private ArrayList<HomeItemBean> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        searchText = root.findViewById(R.id.search_view);
        searchText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeSearchActivity.startHomeSearchActivity(container.getContext());
            }
        });
        initData();
        initRecyclerView(root);
        return root;
    }

    private void initData() {
        list.clear();
        list.add(new HomeItemBean(R.drawable.home_one,HomeAdapter.FLAG));
        list.add(new HomeItemBean(R.drawable.home_two,"http://u3316051.viewer.maka.im/pcviewer/OR3YUH0PW3316051"));
        list.add(new HomeItemBean(R.drawable.home_three,"http://u3316051.viewer.maka.im/pcviewer/Y18U3NLTW3316051"));
    }

    private void initRecyclerView(View root) {
        recyclerView = root.findViewById(R.id.home_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setAdapter(new HomeAdapter(list));
    }


}
