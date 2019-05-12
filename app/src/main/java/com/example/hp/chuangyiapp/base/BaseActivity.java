package com.example.hp.chuangyiapp.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.hp.chuangyiapp.App;
import com.example.hp.chuangyiapp.R;

import java.lang.ref.WeakReference;

public class BaseActivity extends AppCompatActivity {

    private LoginStatusReceiver receiver;
    private LinearLayout requestOut;

    class LoginStatusReceiver extends BroadcastReceiver{
        private WeakReference<BaseActivity> weakActivty;

        public LoginStatusReceiver(BaseActivity baseActivity){
            this.weakActivty = new WeakReference(baseActivity);
        }

        @Override
        public void onReceive(Context context, Intent intent) {
                weakActivty.get().loginSuccess();
        }
    }

    public void loginSuccess(){
        //todo
    }

    protected void registerLoginStatusReceiver(){
        receiver = new LoginStatusReceiver(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(App.LOGIN_SUCCESS);
        registerReceiver(receiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(receiver != null)unregisterReceiver(receiver);
    }

    public void whenRequestOut(){
        requestOut = (LinearLayout)LayoutInflater.from(this).inflate(R.layout.request_out,null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        this.addContentView(requestOut,lp);
    }

    public void whenRequstIn(){
        if(requestOut != null)requestOut.setVisibility(View.GONE);
    }
}
