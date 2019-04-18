package com.example.hp.chuangyiapp.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;

import com.example.hp.chuangyiapp.App;

import java.lang.ref.WeakReference;

public class BaseFragment extends Fragment {
    private LoginStatusReceiver receiver;

    class LoginStatusReceiver extends BroadcastReceiver {
        private WeakReference<BaseFragment> weakFragment;

        public LoginStatusReceiver(BaseFragment baseFragment){
            this.weakFragment = new WeakReference(baseFragment);
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            weakFragment.get().loginSuccess();
        }
    }

    public void loginSuccess(){
        //todo
    }

    protected void registerLoginStatusReceiver(){
        receiver = new LoginStatusReceiver(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(App.LOGIN_SUCCESS);
        getActivity().registerReceiver(receiver,intentFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (receiver != null) {
            getActivity().unregisterReceiver(receiver);
        }
    }


}
