package com.example.hp.chuangyiapp.block.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hp.chuangyiapp.R;
import com.example.hp.chuangyiapp.base.BaseActivity;

public class AddActivity extends BaseActivity {

    private EditText contentText;
    private TextView cancer;
    private Button post;
    private TextView counter;

    public static void startAddActivity(Context context){
        Intent intent = new Intent(context,AddActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
    }

    private void initView() {
        contentText = findViewById(R.id.content_text);
        cancer = findViewById(R.id.cancer_text);
        post = findViewById(R.id.post_text);
        counter = findViewById(R.id.number_text);

        contentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int num = s.length();
                counter.setText(num + "" + "/60");
                if(num > 60 )counter.setTextColor(Color.RED);
                else counter.setTextColor(R.attr.titleTextColor);
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });

        cancer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postMessage(contentText.getText().toString());
            }
        });
    }

    private void postMessage(String content) {

    }
}
