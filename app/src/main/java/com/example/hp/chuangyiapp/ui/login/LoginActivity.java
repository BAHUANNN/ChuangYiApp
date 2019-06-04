package com.example.hp.chuangyiapp.ui.login;

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

import com.example.hp.chuangyiapp.App;
import com.example.hp.chuangyiapp.R;
import com.example.hp.chuangyiapp.base.BaseActivity;
import com.example.hp.chuangyiapp.net.CampusFactory;
import com.example.base.net.bean.LoginBean;
import com.example.hp.chuangyiapp.utils.LoginUtil;
import com.google.gson.Gson;

import java.util.HashMap;

import okhttp3.RequestBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginActivity extends BaseActivity {
    private EditText userAcText;
    private EditText userPasText;
    private TextView signupText;
    private Button loginButton;

    public static void startLoginActivity(Context context){
        Intent intent = new Intent(context,LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        userAcText = findViewById(R.id.user_ac_text);
        userPasText = findViewById(R.id.user_pas_text);
        loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(userAcText.getText().toString(),userPasText.getText().toString());
            }
        });

        signupText = findViewById(R.id.link_signup);
        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignupActivity.startSignupActivity(LoginActivity.this);
            }
        });
    }

    private void login(String account,String password){
        Gson gson = new Gson();
        HashMap<String,String> paramsMap = new HashMap<>();
        paramsMap.put("account",account);
        paramsMap.put("password",password);
        String strEntity = gson.toJson(paramsMap);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"),strEntity);
        Log.i("post ",strEntity);

        CampusFactory.getRetrofitService().signin(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                        finish();//自动关闭页
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("WHATEVER",e.getMessage());
                        Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        LoginUtil.login(loginBean.getToken(),loginBean.getUsername());
                        Intent intent = new Intent();
                        intent.setAction(App.LOGIN_SUCCESS);
                        sendBroadcast(intent);
                    }
                });
    }
}
