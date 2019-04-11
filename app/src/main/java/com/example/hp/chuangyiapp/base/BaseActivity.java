package com.example.hp.chuangyiapp.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;

import com.example.hp.chuangyiapp.App;

import java.lang.ref.WeakReference;

public class BaseActivity extends AppCompatActivity {

    private LoginStatusReceiver receiver;

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
}
