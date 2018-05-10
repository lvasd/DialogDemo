package com.lj.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lj.demo.base.BaseActivity;
import com.lj.demo.dialogfragment.BottomDialogFragment;
import com.lj.demo.dialogfragment.ConfirmDialogFragment;
import com.lj.demo.dialogfragment.EditDialogfragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * dialogFragment
 */
public class DialogFragmentActivity extends BaseActivity {

    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;

    private BottomDialogFragment bottomDialogFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dialog_fragment;
    }

    @Override
    protected void initView() {

        //底部弹出的dialog
        bottomDialogFragment = BottomDialogFragment.getInstance();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

        //底部弹出dialog的监听
        bottomDialogFragment.setOnItemClickListener(this::showToast);
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                ConfirmDialogFragment dialogFragment = new ConfirmDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putString("title", "标题党");
                bundle.putString("message", "内容不可为空");
                bundle.putString("confirm", "知道了");
                dialogFragment.setArguments(bundle);
                dialogFragment.show(getSupportFragmentManager(), "只是个tag");
                break;
            case R.id.btn2:
                EditDialogfragment dialog = new EditDialogfragment();
                dialog.show(getSupportFragmentManager(), "只是个tag");
                break;
            case R.id.btn3:
                bottomDialogFragment.show(getSupportFragmentManager(), BottomDialogFragment.class.getSimpleName());
                break;
        }
    }

    public void setDialogSelected(String msg) {
        showToast(msg);
    }
}
