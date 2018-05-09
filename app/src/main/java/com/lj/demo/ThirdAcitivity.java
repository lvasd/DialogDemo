package com.lj.demo;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.widget.ImageView;
import android.widget.TextView;

import com.lj.demo.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;


public class ThirdAcitivity extends BaseActivity {

    @BindView(R.id.btn_float)
    FloatingActionButton btnFloat;
    @BindView(R.id.second_img)
    ImageView secondImg;
    @BindView(R.id.second_tv)
    TextView secondTv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_third;
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

    @OnClick(R.id.btn_float)
    public void onViewClicked() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityCompat.finishAfterTransition(this);
    }
}
