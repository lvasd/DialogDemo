package com.lj.demo;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lj.demo.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * startActivity动画效果展示界面
 * 不要在意Activity的名字
 */
public class SecondActivity extends BaseActivity {

    @BindView(R.id.img_content)
    ImageView imgContent;
    @BindView(R.id.tv)
    TextView tv;
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
    @BindView(R.id.btn6)
    Button btn6;

    /**
     * 注：使用这些方法 需要 targetSdkVersion 在 21以上才可以
     * 并设置开启
     * 方法 1 如下方法 在setCOntentView()前添加 下面注释的方法
     * 方法 2 如本项目所示 创建 v21的style
     */
    @Override
    protected void beforeSetContentView() {
//        requestWindowFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        //创建各种动画
        Transition explode = TransitionInflater.from(this).inflateTransition(android.R.transition.explode);
        Transition fade = TransitionInflater.from(this).inflateTransition(android.R.transition.fade);
        Transition move = TransitionInflater.from(this).inflateTransition(android.R.transition.move);
        Transition slide_left = TransitionInflater.from(this).inflateTransition(android.R.transition.slide_left);

        //使用动画时机
//        getWindow().setExitTransition(slide_left);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_second;
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

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                //makeCustomAnimation
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeCustomAnimation(
                        this, R.anim.activity_start_zoom_in, R.anim.activity_start_zoom_out);
                ActivityCompat.startActivity(this, new Intent(this, ThirdAcitivity.class),
                        compat.toBundle());
                break;
            case R.id.btn2:
                //makeScaleUpAnimation
                ActivityOptionsCompat compat2 = ActivityOptionsCompat.makeScaleUpAnimation(
                        view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
                ActivityCompat.startActivity(this, new Intent(this, ThirdAcitivity.class),
                        compat2.toBundle());
                break;
            case R.id.btn3:
                //makeThumbnailScaleUpAnimation
                ActivityOptionsCompat compat3 = ActivityOptionsCompat.makeThumbnailScaleUpAnimation(
                        imgContent, ((BitmapDrawable) imgContent.getDrawable()).getBitmap(), 0, 0);
                ActivityCompat.startActivity(this, new Intent(this, ThirdAcitivity.class),
                        compat3.toBundle());

                break;
            case R.id.btn4:
                //单一makeSceneTransitionAnimation
                ActivityOptionsCompat compat4 =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                                imgContent, getString(R.string.start_activity_img));
                ActivityCompat.startActivity(this, new Intent(this,
                        ThirdAcitivity.class), compat4.toBundle());
                break;
            case R.id.btn5:
                //多个makeSceneTransitionAnimation
                Pair<View, String> imagePair = Pair.create(imgContent, getString(R.string.start_activity_img));
                Pair<View, String> textPair = Pair.create(tv, getString(R.string.start_activity_tv));

                ActivityOptionsCompat compat5 = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(this, imagePair, textPair);
                ActivityCompat.startActivity(this, new Intent(this, ThirdAcitivity.class),
                        compat5.toBundle());
                break;
            case R.id.btn6:
                ActivityOptionsCompat compat6 = ActivityOptionsCompat.makeScaleUpAnimation(
                        view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
                ActivityCompat.startActivity(this, new Intent(this, RecyclerActivity.class),
                        compat6.toBundle());
                break;
        }
    }
}
