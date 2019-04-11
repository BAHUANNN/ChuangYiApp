package com.example.hp.chuangyiapp.block.main.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp.chuangyiapp.App;
import com.example.hp.chuangyiapp.R;
import com.example.hp.chuangyiapp.base.BaseFragment;
import com.example.hp.chuangyiapp.block.login.LoginActivity;
import com.example.hp.chuangyiapp.utils.LoginUtil;
import com.example.hp.chuangyiapp.utils.PreferenceUtil;

import de.hdodenhof.circleimageview.CircleImageView;

public class MineFragment extends BaseFragment {

    private TextView idText;
    private TextView settingText;
    private CircleImageView circleImageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mine, container, false);
        initView(root);
        registerLoginStatusReceiver();//通过BaseFragment注册广播
        return root;
    }

    @Override
    public void loginSuccess() {
        logined();
    }

    private void initView(View root) {
        idText = root.findViewById(R.id.mine_id);
        idText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.startLoginActivity(getContext());
            }
        });
        settingText = root.findViewById(R.id.setting_text);
        settingText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quitLogined();
            }
        });
        circleImageView = root.findViewById(R.id.mine_photo);

        if(LoginUtil.isLogin())logined();
    }

    private void logined(){
        String id = PreferenceUtil.getString(PreferenceUtil.USER_ID,"游客");
        idText.setClickable(false);
        idText.setText(id);
        idText.setTextColor(getResources().getColor(R.color.textColorPrimary));
        circleImageView.setImageDrawable(getResources().getDrawable(R.drawable.head_portrait));
    }


    private void quitLogined(){
        PreferenceUtil.saveBoolean(PreferenceUtil.IS_LOGIN,false);
        idText.setClickable(true);
        idText.setText("点击登录");
        idText.setTextColor(getResources().getColor(R.color.textColorSecondary));
        circleImageView.setImageDrawable(getResources().getDrawable(R.drawable.head_portrait_without));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
