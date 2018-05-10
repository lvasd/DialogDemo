package com.lj.demo.dialogfragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.TextUtils;

import com.lj.demo.DialogFragmentActivity;

/**
 * dialogFragment
 * 一个简单的确认取消提示
 */
public class ConfirmDialogFragment extends AppCompatDialogFragment {


    private DialogFragmentActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (DialogFragmentActivity) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        /*
            Fragment 与 Activity的传递事件  消息  怎样顺手怎样写 也可以写get set方法
         */
        Bundle bundle = getArguments();
        String title = bundle.getString("title");
        String message = bundle.getString("message");
        String cancel = bundle.getString("cancel");
        String confirm = bundle.getString("confirm");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(TextUtils.isEmpty(title) ? "提示" : title)
                .setMessage(TextUtils.isEmpty(message) ? "这是内容" : message)
                //如果只需要一个键  可以 setPositiveButton()或setNegativeButton() 只设置一个
                .setPositiveButton(TextUtils.isEmpty(confirm) ? "确认" : confirm, (dialog, which) -> activity.setDialogSelected("知道了"));

        return builder.create();
    }
}
