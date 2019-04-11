package com.example.hp.chuangyiapp.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import com.example.hp.chuangyiapp.R;

public class DialogUtil {
    public interface OnClick{
        void onClickPositive();
        void onClickNegative();
    }

    public static void showNormalDialog(Context context, String message, final OnClick onClick){
        AlertDialog.Builder normalMoreButtonDialog = new AlertDialog.Builder(context);
        normalMoreButtonDialog.setMessage(message);

        //设置按钮
        normalMoreButtonDialog.setPositiveButton("当然"
                , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onClick.onClickPositive();
                        dialog.dismiss();
                    }
                });
        normalMoreButtonDialog.setNegativeButton("暂时不"
                , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onClick.onClickNegative();
                        dialog.dismiss();
                    }
                });
        normalMoreButtonDialog.create().show();

    }
}
