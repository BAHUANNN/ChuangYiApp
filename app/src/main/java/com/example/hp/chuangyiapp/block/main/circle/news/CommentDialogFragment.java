package com.example.hp.chuangyiapp.block.main.circle.news;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.hp.chuangyiapp.R;

import java.util.Timer;
import java.util.TimerTask;

public class CommentDialogFragment extends DialogFragment {

    public interface OnPost{
        void post();
    }

    private Dialog mDialog;
    private EditText commentEditText;
    private ImageView postButton;

    private OnPost onPost;

   public void setOnPost(OnPost onPost){
       this.onPost = onPost;
   }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDialog = new Dialog(getActivity());

        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.fragment_comment_dialog);
        mDialog.setCanceledOnTouchOutside(true);

        Window window = mDialog.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();

        // 布局属性位于整个窗口底部
        layoutParams.gravity = Gravity.BOTTOM;

        // 布局属性宽度填充满整个窗口宽度，默认是有 margin 值的
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(layoutParams);

        commentEditText = mDialog.findViewById(R.id.comment_edit);
        showSoftKeyboard();

        postButton = mDialog.findViewById(R.id.post_button);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onPost != null)onPost.post();
                dismiss();
            }
        });

        return mDialog;
    }

    private void post(String s) {
        //todo
    }

    private void showSoftKeyboard() {

        // 为 EditText 获取焦点
        commentEditText.setFocusable(true);
        commentEditText.setFocusableInTouchMode(true);
        commentEditText.requestFocus();
        commentEditText.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputMethodManager.showSoftInput(commentEditText, 0)) {
                    commentEditText.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });
    }

}


