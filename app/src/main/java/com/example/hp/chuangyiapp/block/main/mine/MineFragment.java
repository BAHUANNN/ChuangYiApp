package com.example.hp.chuangyiapp.block.main.mine;

import android.app.Dialog;
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
import com.example.hp.chuangyiapp.utils.DialogUtil;
import com.example.hp.chuangyiapp.utils.LoginUtil;
import com.example.hp.chuangyiapp.utils.PreferenceUtil;

import de.hdodenhof.circleimageview.CircleImageView;

public class MineFragment extends BaseFragment {

    private TextView idText;
    private TextView settingText;
    private TextView aboutText;
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
        settingText = root.findViewById(R.id.setting_text);
        settingText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LoginUtil.isLogin()) quitLogined();
                else goLogin();
            }
        });
        circleImageView = root.findViewById(R.id.mine_photo);

        aboutText = root.findViewById(R.id.about_text);
        aboutText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AboutActivity.startAboutActivity(getContext());
            }
        });

        if(LoginUtil.isLogin())logined();
    }

    private void logined(){
        String name = PreferenceUtil.getString(PreferenceUtil.USER_NAME);
        idText.setText(name);
        idText.setTextColor(getResources().getColor(R.color.textColorPrimary));
        settingText.setText("退出登陆");
        circleImageView.setImageDrawable(getResources().getDrawable(R.drawable.head_portrait));
    }


    private void goLogin() {
        LoginActivity.startLoginActivity(getContext());
    }

    private void quitLogined(){
        DialogUtil.showNormalDialog(getContext(), "您确定要退出登陆吗？", new DialogUtil.OnClick() {
            @Override
            public void onClickPositive() {
                LoginUtil.quitLogin();
                idText.setText("暂未登录");
                idText.setTextColor(getResources().getColor(R.color.textColorSecondary));
                settingText.setText("登陆账号");
                circleImageView.setImageDrawable(getResources().getDrawable(R.drawable.head_portrait_without));
            }
            @Override
            public void onClickNegative() {
                //do nothing
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
