package com.lj.demo.bottomsheet;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lj.demo.R;
import com.lj.demo.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * app:layout_behavior="@string/bottom_sheet_behavior"的使用
 */
public class BottomSheetsActivity extends BaseActivity {

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.bottom_sheet)
    NestedScrollView bottomSheet;
    @BindView(R.id.btn)
    Button btn;

    private ArrayList<String> mData;
    private BaseQuickAdapter<String, BaseViewHolder> mAdapter;
    private BottomSheetBehavior behavior;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bottom_sheets;
    }

    @Override
    protected void initView() {
        behavior = BottomSheetBehavior.from(bottomSheet);

    }

    @Override
    protected void initData() {
        mData = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            mData.add("位置 " + i);
        }

        rv.setLayoutManager(new GridLayoutManager(mContext, 3));
        mAdapter = getAdapter();
        rv.setAdapter(mAdapter);
        rv.setNestedScrollingEnabled(false);
    }

    @Override
    protected void initListener() {

        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                //这里是bottomSheet 状态的改变回调
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                //这里是拖拽中的回调，根据slideOffset可以做一些动画
            }
        });


        btn.setOnClickListener(v -> {
            if (behavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            } else {
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            showToast("点击了 item " + position);
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        });
    }

    private BaseQuickAdapter<String, BaseViewHolder> getAdapter() {
        return new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_bottom_sheets_rv, mData) {
            @Override
            protected void convert(BaseViewHolder holder, String item) {
                TextView tv = holder.getView(R.id.item_tv);
                tv.setText(item);
            }
        };
    }

}
