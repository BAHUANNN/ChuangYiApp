package com.example.hp.chuangyiapp.block.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.chuangyiapp.App;
import com.example.hp.chuangyiapp.R;
import com.example.hp.chuangyiapp.base.BaseActivity;
import com.example.hp.chuangyiapp.utils.LoginUtil;
import com.example.hp.chuangyiapp.utils.PreferenceUtil;

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

    private void login(String account,String pas){
        if(isLoginSuccess(account,pas)){
            LoginUtil.login();
            Intent intent = new Intent();
            intent.setAction(App.LOGIN_SUCCESS);
            sendBroadcast(intent);

            finish();//自动关闭页
        }else {
            Toast.makeText(this,"账号或密码错误",Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isLoginSuccess(String id,String pas) {
        return id.equals("id") && pas.equals("pas");
    }
}
