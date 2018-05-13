package com.lj.demo;

import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lj.demo.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * AlertDialog
 */
public class AlertDialogActivity extends BaseActivity {

    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;

    @BindView(R.id.btn4)
    Button btn4;
    @BindView(R.id.btn5)
    Button btn5;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_alert_dialog;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                //简单的确认取消
                showCommonDialog();
                break;
            case R.id.btn2:
                //带有EditText的确认取消
                showEditDialog();
                break;
            case R.id.btn3:
                //列表dialog
                showListDialog();
                break;
            case R.id.btn4:
                showSingleDialog();
                break;
            case R.id.btn5:
                showMoreDialog();
                break;


        }
    }

    private final String[] items = {"item 0 ", "item 1 ", "item 2 ", "item 3 ", "item 4 "};
    final boolean checkedItems[] = {true, false, false, false, false};

    /**
     * 多选
     */
    private void showMoreDialog() {
        AlertDialog dialog = new AlertDialog.Builder(mContext)
                .setTitle("多选dialog")
                .setMultiChoiceItems(items, checkedItems, (dialog1, which, isChecked) -> checkedItems[which] = isChecked)
                .setPositiveButton("确认", (dialog12, which) -> {

                    StringBuffer s = new StringBuffer("选中了 ");
                    for (int i = 0, len = checkedItems.length; i < len; i++) {
                        if (checkedItems[i]) {
                            s.append(items[i]);
                        }
                    }

                    showToast(s.toString());
                })
                .setNegativeButton("取消", null)
                .create();
        dialog.show();
    }

    /**
     * 单选
     */
    int pos = 0;

    private void showSingleDialog() {

        AlertDialog dialog = new AlertDialog.Builder(mContext)
                .setTitle("单选dialog")
                .setSingleChoiceItems(items, pos, (dialog1, which) -> pos = which)
                .setPositiveButton("确认", (dialog12, which) -> showToast(items[pos]))
                .setNegativeButton("取消", null)
                .create();
        dialog.show();
    }


    /**
     * 列表dialog
     */
    private void showListDialog() {
        AlertDialog dialog = new AlertDialog.Builder(mContext)
                .setTitle("列表dialog")
                .setItems(items, (dialog1, which) -> showToast(items[which]))
                .create();
        dialog.show();
    }

    /**
     * 带有EditText的dialog
     * emmm 半自定义布局
     * 因为没有设置 title PositiveButton NegativeButton Message 所以看起来像自定义 的
     */
    private void showEditDialog() {

        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_edit, null);

        TextView tvTitle = view.findViewById(R.id.tv_title);
        EditText et = view.findViewById(R.id.dialog_et);
        TextView tvConfirm = view.findViewById(R.id.tv_confirm);
        tvTitle.setText("editText的dialog");
        AlertDialog dialog = new AlertDialog.Builder(mContext)
                .setView(view)
                .create();
        /*
            带有EditText  最好设置 点击外部消失 false
         */
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        tvConfirm.setOnClickListener(v -> {
            if (TextUtils.isEmpty(et.getText().toString().trim())) {
                showToast("请输入内容");
            } else {
                showToast(et.getText().toString().trim());
                dialog.dismiss();
            }
        });
    }

    /**
     * 简单的一个确认取消AlertDialog
     */
    private void showCommonDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(mContext)
                .setTitle("这是标题")
                .setIcon(R.mipmap.ic_pic_test)
                .setMessage("这是内容")
                .setPositiveButton("确认", (dialog, which) -> showToast("确认"))
                .setNegativeButton("取消", (dialog, which) -> showToast("取消"))
                .create();//listener可传 null
        alertDialog.show();
    }
}
