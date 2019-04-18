package com.example.hp.chuangyiapp.ui.main.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.hp.chuangyiapp.R;
import com.example.hp.chuangyiapp.base.BaseActivity;

public class AboutActivity extends BaseActivity {
    public static void startAboutActivity(Context context){
        Intent intent = new Intent(context,AboutActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }


}
