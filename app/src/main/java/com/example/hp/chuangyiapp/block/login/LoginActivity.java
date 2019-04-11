package com.example.hp.chuangyiapp.block.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp.chuangyiapp.App;
import com.example.hp.chuangyiapp.R;
import com.example.hp.chuangyiapp.base.BaseActivity;
import com.example.hp.chuangyiapp.utils.PreferenceUtil;

public class LoginActivity extends BaseActivity {
    private EditText userIdText;
    private EditText userPasText;
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
        userIdText = findViewById(R.id.user_id_text);
        userPasText = findViewById(R.id.user_pas_text);
        userPasText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(userIdText.getText().toString(),userPasText.getText().toString());
            }
        });
    }

    private void login(String id,String pas){
        if(isLoginSuccess(id,pas)){
            PreferenceUtil.saveBoolean(PreferenceUtil.IS_LOGIN,true);
            PreferenceUtil.saveString(PreferenceUtil.USER_ID,id);
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
