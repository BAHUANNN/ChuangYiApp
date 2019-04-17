package com.example.hp.chuangyiapp.block.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.chuangyiapp.R;
import com.example.hp.chuangyiapp.base.BaseActivity;
import com.example.hp.chuangyiapp.net.CampusFactory;
import com.example.hp.chuangyiapp.utils.LoginUtil;
import com.google.gson.Gson;

import java.util.HashMap;

import okhttp3.RequestBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
                counter.setText(num + "" + "/120");
                if(num > 120 )counter.setTextColor(Color.RED);
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
                if(contentText.getTextSize() > 120)return;
                postMessage(contentText.getText().toString());
            }
        });
    }

    private void postMessage(String content) {
        Gson gson = new Gson();
        HashMap<String,String> paramsMap = new HashMap<>();
        paramsMap.put("content",content);
        String strEntity = gson.toJson(paramsMap);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"),strEntity);
        Log.i("post ",strEntity);

        CampusFactory.getRetrofitService().addState(LoginUtil.getToken(),body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(AddActivity.this,"发送成功",Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(AddActivity.this,"网络似乎丢失了~~",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Object o) {

                    }
                });
    }
}
