package com.lj.demo.bottomsheet;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lj.demo.BottomSheetActivity;
import com.lj.demo.R;

import java.util.ArrayList;

/**
 * {@link BottomSheetDialogFragment}
 */
public class CustomBottomSheetFragment extends BottomSheetDialogFragment {

    private TextView title;
    private RecyclerView rv;
    private ArrayList<String> mData;
    private BaseQuickAdapter<String, BaseViewHolder> mAdapter;
    private BottomSheetActivity activity;
    private BottomSheetDialog dialog;
    private View rootView;
    private BottomSheetBehavior<View> mBehavior;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (BottomSheetActivity) context;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //解除缓存View和当前ViewGroup的关联
        ((ViewGroup) (rootView.getParent())).removeView(rootView);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //每次打开都调用该方法 类似于onCreateView 用于返回一个Dialog实例
        dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        if (rootView == null) {
            //缓存下来的View 当为空时才需要初始化 并缓存
            rootView = View.inflate(getContext(), R.layout.dialog_fragment_bottom_sheet, null);
            initView();
        }
        initData();
        initListener();
        //设置View重新关联
        dialog.setContentView(rootView);
        mBehavior = BottomSheetBehavior.from((View) rootView.getParent());

        mBehavior.setHideable(true);
        //圆角边的关键 设置dialog 背景为透明
        //((View) rootView.getParent()).setBackgroundColor(Color.TRANSPARENT);
        rootView.post(() -> {
            /**
             * PeekHeight默认高度256dp 会在该高度上悬浮
             */
            //获取屏幕的高度
            int height = getResources().getDisplayMetrics().heightPixels;
            //设置默认弹出高度为屏幕的0.4倍
            mBehavior.setPeekHeight((int) (0.4 * height));
        });
        return dialog;
    }


    private void initView() {
        title = rootView.findViewById(R.id.title);
        rv = rootView.findViewById(R.id.rv);
    }

    private void initData() {

        mData = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            mData.add("位置 " + i);
        }

        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = getAdapter();
        rv.setAdapter(mAdapter);
    }

    private void initListener() {
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            activity.setBottomSheetFragmentSelected(mData.get(position));
            dismiss();
        });
    }

    private BaseQuickAdapter<String, BaseViewHolder> getAdapter() {
        return new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_dialog_bottom_sheet_fragment, mData) {
            @Override
            protected void convert(BaseViewHolder holder, String item) {
                TextView tv = holder.getView(R.id.item_tv);
                tv.setText(item);
            }
        };
    }
}
