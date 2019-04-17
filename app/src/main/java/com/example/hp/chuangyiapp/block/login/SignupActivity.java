package com.example.hp.chuangyiapp.block.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.chuangyiapp.R;
import com.example.hp.chuangyiapp.base.BaseActivity;
import com.example.hp.chuangyiapp.net.CampusFactory;
import com.google.gson.Gson;

import java.util.HashMap;

import okhttp3.RequestBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SignupActivity extends BaseActivity {

    private EditText userIdText;
    private EditText userAcText;
    private EditText userPasText;
    private TextView loginText;
    private Button signupButton;

    public static void startSignupActivity(Context context){
        Intent intent = new Intent(context,SignupActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initView();
    }

    private void initView() {
        userIdText = findViewById(R.id.user_id_text);
        userAcText = findViewById(R.id.user_ac_text);
        userPasText = findViewById(R.id.user_pas_text);
        signupButton = findViewById(R.id.signup_button);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup(userIdText.getText().toString(),userAcText.getText().toString(),userPasText.getText().toString());
            }
        });

        loginText = findViewById(R.id.link_login);
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void signup(String username, String account, String password) {
        Gson gson = new Gson();
        HashMap<String,String> paramsMap = new HashMap<>();
        paramsMap.put("username",username);
        paramsMap.put("password",password);
        paramsMap.put("account",account);
        String strEntity = gson.toJson(paramsMap);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"),strEntity);
        Log.i("post ",strEntity);

        CampusFactory.getRetrofitService().signup(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(SignupActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("WHATEVER",e.getMessage());
                        Toast.makeText(SignupActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Object o) {

                    }
                });
    }
}
