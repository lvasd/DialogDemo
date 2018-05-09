package com.lj.demo.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder unbinder;
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeSetContentView();
        setContentView(getLayoutId());
        //绑定控件
        unbinder = ButterKnife.bind(this);
        mContext = this;
        initView();
        initData();
        initListener();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();

    /**
     * 预留方法
     * 防止某些设置需要在{@link #setContentView(int)}前执行
     */
    protected void beforeSetContentView() {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();//butterknife解绑
    }
}
