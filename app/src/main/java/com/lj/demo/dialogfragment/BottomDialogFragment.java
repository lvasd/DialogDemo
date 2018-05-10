package com.lj.demo.dialogfragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.lj.demo.R;

/**
 * dialogFargment
 * DialogFragment 有好多不同的包 可根据自己的需求选择
 */
public class BottomDialogFragment extends AppCompatDialogFragment {

    private TextView tvCancel;
    private TextView tvTitle;
    private TextView tvCamera;
    private TextView tvPhoto;

    private String title;
    private String photo;
    private String camera;
    private String cancel;
    private OnItemClickListener listener;

    public BottomDialogFragment() {
    }

    public static BottomDialogFragment getInstance() {
        return new BottomDialogFragment();
    }

    /*
        onCreateDialog() 此方法不能与 onCreateView()同时使用
        onCreateDoalog() 返回一个dialog  相当于将Dialog当做Dialog 使用
        onCreateView()  返回 view  相当于将Dialog当做一个Fragment使用
     */
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        return super.onCreateDialog(savedInstanceState);
//    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_bottom, container, false);
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

    /**
     * 在{@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}方法中设置自定义布局
     * 如果想要布局宽度为屏幕宽度,必须在{@link #onStart()}方法中设置 width = WindowManager.LayoutParams.MATCH_PARENT;
     * <p>
     * 设置宽度为屏幕宽度
     * <p>
     * 如项目布局所示 在取消与相册之间 有段margin 以正常逻辑 当dialog 弹出时这段间距应该是跟dialog半透明背景一样
     * 如不设置 window.setBackground()这段间距不会显示出来的，会被布局的背景或项目的 windowbackground填满的
     * <p>
     */
    private void setWindow() {

        //设置为点击外部不可取消
        Dialog dialog = getDialog();
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);

        Window window = dialog.getWindow();
        /*
            这个方法需要跟下面的  params.width = WindowManager.LayoutParams.MATCH_PARENT一起说明
            如果2个都不设置 布局会使用wrap_content 不管xml中 最外层是怎么写的

            只设置match_parent  dialog会使用xml布局的背景或项目的 windowbackground  并且会发现与屏幕两侧 有段间距

                如项目所示 在取消与相册之间 有段margin 以正常逻辑 当dialog 弹出时这段间距应该是跟dialog半透明背景一样
                这个半透明的间距是不会显示的 会被xml布局的背景或项目的 windowbackground 填充
                当然没有跟本项目这样这段透明的需求  可以不设置background 如项目中的EditDialogFragment

           只设置background 只有半透明的背景

           两个一起设置既可以消除两侧的间距 还可以正常加载布局
         */
        window.setBackgroundDrawableResource(android.R.color.transparent);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        //设置位置在屏幕底部 （可选  不设置会在屏幕中间）
        params.gravity = Gravity.BOTTOM;
        window.setAttributes(params);
        //设置动画  （可选）
        window.setWindowAnimations(R.style.AnimBottom);
    }

    private void initListener() {

        tvCancel.setOnClickListener(v -> {
            dismiss();
        });

        tvCamera.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItenClick("拍照");
            }
            dismiss();
        });

        tvPhoto.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItenClick("相册");
            }
            dismiss();
        });
    }

    private void initData() {

        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
        }

        if (!TextUtils.isEmpty(photo)) {
            tvPhoto.setText(photo);
        }

        if (!TextUtils.isEmpty(camera)) {
            tvCamera.setText(camera);
        }

        if (!TextUtils.isEmpty(cancel)) {
            tvCancel.setText(cancel);
        }
    }

    private void initView(View view) {
        tvCancel = view.findViewById(R.id.dialog_bottom_tv_cancel);
        tvTitle = view.findViewById(R.id.dialog_bottom_tv_title);
        tvCamera = view.findViewById(R.id.dialog_bottom_tv_camera);
        tvPhoto = view.findViewById(R.id.dialog_bottom_tv_photo);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {

        void onItenClick(String s);
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public void setCancel(String cancel) {
        this.cancel = cancel;
    }
}
