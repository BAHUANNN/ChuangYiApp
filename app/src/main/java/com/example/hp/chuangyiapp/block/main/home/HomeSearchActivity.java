package com.example.hp.chuangyiapp.block.main.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.chuangyiapp.R;
import com.example.hp.chuangyiapp.db.ChuangyiDao;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

public class HomeSearchActivity extends AppCompatActivity {

    private SearchView searchView;
    private TextView cancerText;
    private TextView clearText;
    private TagFlowLayout flowLayout;
    private RecyclerView recyclerView;

    private String query = "";
    private List<String> queryHistory;
    private ChuangyiDao dao;

    public static void startHomeSearchActivity(Context context){
        Intent intent = new Intent(context,HomeSearchActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_search);
        dao = new ChuangyiDao(getApplicationContext());
        initData();
        initView();
        initFlowLayout();
    }

    private void initData() {
        queryHistory = dao.loadSearchHistory();
    }

    private void initFlowLayout() {
        flowLayout = findViewById(R.id.flow_view);
        final LayoutInflater inflater = LayoutInflater.from(HomeSearchActivity.this);
        flowLayout.setAdapter(new TagAdapter<String>(queryHistory) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) inflater.inflate(R.layout.search_history_item,
                        flowLayout, false);
                tv.setText(s);
                return tv;
            }
        });
        flowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                query = queryHistory.get(position);
                searchView.setQuery(query,true);
                return false;
            }
        });
    }

    private void initView() {
        cancerText = findViewById(R.id.cancer_text);
        cancerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeSearchActivity.this.finish();
            }
        });
        clearText = findViewById(R.id.clear_text);
        clearText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao.deleteAllHistory();
                Toast.makeText(HomeSearchActivity.this,"成功清空",Toast.LENGTH_SHORT).show();
                initData();
                flowLayout.getAdapter().notifyDataChanged();
            }
        });

        recyclerView = findViewById(R.id.result_recycler_view);
        recyclerView.setVisibility(View.GONE);
        initSearchView();
    }

    private void initSearchView() {
        searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchView.clearFocus();//失去焦点
                dao.insertSearchHistory(s);//插入历史记录
                query = s;
                showRecyclerView(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)hideRecyclerView();
            }
        });

        // 去掉下划线
        View searchPlate = findViewById(R.id.search_plate);
        View submitArea = findViewById(R.id.submit_area);
        searchPlate.setBackground(null);
        submitArea.setBackground(null);
    }

    private void hideRecyclerView() {
        if(recyclerView.getVisibility() == View.VISIBLE) recyclerView.setVisibility(View.GONE);
    }

    private void showRecyclerView(String query) {
        if(recyclerView.getVisibility() == View.GONE) recyclerView.setVisibility(View.VISIBLE);
    }
}
