package com.example.hp.chuangyiapp.ui.main.circle;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hp.chuangyiapp.R;
import com.example.hp.chuangyiapp.adapter.StatesAdapter;
import com.example.hp.chuangyiapp.base.BaseFragment;
import com.example.hp.chuangyiapp.net.CampusFactory;
import com.example.hp.chuangyiapp.net.bean.StatesBean;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class StatesFragment extends BaseFragment {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private List<StatesBean.FeedlistBean> states = new ArrayList<StatesBean.FeedlistBean>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_states, container, false);
        initRecyclerView(root);
        initSwipeRefreshLayout(root);
        initData();
        return root;
    }

    private void initSwipeRefreshLayout(View root) {
        swipeRefreshLayout = root.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary),getResources().getColor(R.color.colorAccent));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void initRecyclerView(View root) {
        recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new StatesAdapter(states));
    }

    private void initData() {
        CampusFactory.getRetrofitService().getStates(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StatesBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(StatesBean statesBean) {
                        states.clear();
                        states.addAll(statesBean.getFeedlist());
                        if(recyclerView != null)recyclerView.getAdapter().notifyDataSetChanged();
                    }
                });

    }
}
