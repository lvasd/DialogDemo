package com.lj.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lj.demo.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerActivity extends BaseActivity {

    @BindView(R.id.rv)
    RecyclerView rv;

    private ArrayList<String> mData;
    private BaseQuickAdapter<String, BaseViewHolder> mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recycler;
    }

    @Override
    protected void initView() {
        mData = new ArrayList<>();
        rv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = getAdapter();
        rv.setAdapter(mAdapter);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
    }

    @Override
    protected void initData() {
        for (int i = 0; i < 30; i++) {
            mData.add("position  = " + i);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initListener() {
        mAdapter.setOnItemClickListener((adapter, view, position) -> {

            ImageView img = (ImageView) mAdapter.getViewByPosition(rv, position, R.id.item_img);
            TextView tv = (TextView) mAdapter.getViewByPosition(rv, position, R.id.item_tv);
            //多个makeSceneTransitionAnimation
            Pair<View, String> imagePair = Pair.create(img, getString(R.string.start_activity_img));
            Pair<View, String> textPair = Pair.create(tv, getString(R.string.start_activity_tv));

            ActivityOptionsCompat compat5 = ActivityOptionsCompat
                    .makeSceneTransitionAnimation(this, imagePair, textPair);
            ActivityCompat.startActivity(this, new Intent(this, ThirdAcitivity.class),
                    compat5.toBundle());
        });
    }

    private BaseQuickAdapter<String, BaseViewHolder> getAdapter() {
        return new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_rv, mData) {

            @Override
            protected void convert(BaseViewHolder helper, String item) {
                ImageView item_img = helper.getView(R.id.item_img);
                TextView item_tv = helper.getView(R.id.item_tv);

                item_tv.setText(item);
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
