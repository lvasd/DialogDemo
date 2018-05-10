package com.lj.demo.dialogfragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.lj.demo.DialogFragmentActivity;
import com.lj.demo.R;

public class EditDialogfragment extends AppCompatDialogFragment {

    private TextView tvTitle;
    private EditText et;
    private TextView tvConfirm;

    private String title;
    private String confirm;
    private DialogFragmentActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (DialogFragmentActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_edit, container, false);
        initView(view);
        initData();
        initListener();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setWindow();
    }

    private void setWindow() {
        //设置为点击外部不可取消
        Dialog dialog = getDialog();
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(params);
    }

    private void initListener() {

        tvConfirm.setOnClickListener(v -> {

            if (TextUtils.isEmpty(et.getText().toString().trim())) {
                activity.showToast("请输入内容");
            } else {
                activity.setDialogSelected(et.getText().toString().trim());
                dismiss();
            }
        });
    }

    private void initData() {

        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
        }

        if (!TextUtils.isEmpty(confirm)) {
            tvConfirm.setText(confirm);
        }
    }

    private void initView(View view) {
        tvTitle = view.findViewById(R.id.tv_title);
        et = view.findViewById(R.id.dialog_et);
        tvConfirm = view.findViewById(R.id.tv_confirm);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }
}
