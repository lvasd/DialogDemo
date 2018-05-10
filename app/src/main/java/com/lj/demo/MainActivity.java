package com.lj.demo;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.lj.demo.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_btn1)
    Button mainBtn1;
    @BindView(R.id.main_btn2)
    Button mainBtn2;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
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

    @OnClick({R.id.main_btn1, R.id.main_btn2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_btn1:
                //最常用的AlertDialog
                startActivity(new Intent(mContext, AlertDialogActivity.class));
                break;
            case R.id.main_btn2:
                //DialogFragment
                startActivity(new Intent(mContext, DialogFragmentActivity.class));
                break;
        }
    }
}
