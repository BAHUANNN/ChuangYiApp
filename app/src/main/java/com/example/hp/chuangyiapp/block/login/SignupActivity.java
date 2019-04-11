package com.example.hp.chuangyiapp.block.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hp.chuangyiapp.R;
import com.example.hp.chuangyiapp.base.BaseActivity;

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

    private void signup(String id, String account, String password) {

    }
}
