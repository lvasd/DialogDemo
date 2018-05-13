package com.lj.demo;

import android.content.Intent;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lj.demo.base.BaseActivity;
import com.lj.demo.bottomsheet.BottomSheetsActivity;
import com.lj.demo.bottomsheet.CustomBottomSheetFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class BottomSheetActivity extends BaseActivity {

    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    private BottomSheetDialog dialog;
    private RecyclerView rv;
    private BaseQuickAdapter<String, BaseViewHolder> mAdapter;
    private ArrayList<String> mData;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bottom_sheet;
    }

    @Override
    protected void initView() {
        initBottomSheetDialog();
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            showToast("点击了 item " + position);
            dialog.dismiss();
        });
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                startActivity(new Intent(mContext, BottomSheetsActivity.class));
                break;
            case R.id.btn2:
                dialog.show();
                break;
            case R.id.btn3:
                CustomBottomSheetFragment dialogFragment = new CustomBottomSheetFragment();
                dialogFragment.show(getSupportFragmentManager(), "tag");
                break;
        }
    }


    public void setBottomSheetFragmentSelected(String s) {
        showToast(s);
    }

    /**
     * 因为要设置数据 提前初始化 但是不 show 在需要的时候show就可以
     */
    private void initBottomSheetDialog() {

        //获取屏幕的高度
        int height = getResources().getDisplayMetrics().heightPixels;

        dialog = new BottomSheetDialog(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_bottom_sheet, null);
        dialog.setContentView(view);
        //下面这句可以设置 BottomSheetDialog 的背景为透明  已达到像拍照 相册dialog那种 取消上面的透明
//        dialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet).setBackgroundColor(ContextCompat.getColor(mContext,android.R.color.transparent));
        //设置点击外部消失 false
        dialog.setCanceledOnTouchOutside(false);
        BottomSheetBehavior mBehavior = BottomSheetBehavior.from((View) view.getParent());
        //设置默认弹出高度为屏幕的0.4倍
        mBehavior.setPeekHeight((int) (0.4 * height));

        rv = view.findViewById(R.id.rv);
        mData = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            mData.add("位置 " + i);
        }

        rv.setLayoutManager(new GridLayoutManager(mContext, 3));
        mAdapter = getAdapter();
        rv.setAdapter(mAdapter);

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
