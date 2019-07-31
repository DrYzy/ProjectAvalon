package com.zero.avaloan.avalongate.mvp.activity;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.animation.*;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.zero.avaloan.avalongate.R;
import com.zero.avaloan.avalongate.base.BaseActivity;

public class AnimationActivity extends BaseActivity {
    @BindView(R.id.img_anim1)
    ImageView mImgAni1;
    @BindView(R.id.img_anim2)
    ImageView mImgAni2;
    @BindView(R.id.img_anim3)
    ImageView mImgAni3;
    @BindView(R.id.img_anim4)
    ImageView mImgAni4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        ButterKnife.bind(this);//没有这行，view虽然可以正常声明，但指针为空
        super.init();
        super.setHeader(view->{
            finish();
        },"动画城");
    }

    @Override
    protected void instantiation() {
        super.instantiation();
    }

    @Override
    protected void dataBind() {
        super.dataBind();
    }

    @Override
    protected void eventBind() {
        super.eventBind();
        checkFrameAnim();//帧动画
        checkTweenAnim();//补间动画
        checkRunAnim();//组合 跑步动画
        checkCircleAnim();//组合 转圈动画
    }

    private void checkFrameAnim(){
        AnimationDrawable animationDrawable = (AnimationDrawable) mImgAni1.getBackground();
        animationDrawable.start();//开启动画
//        animationDrawable.stop();//停止动画
    }

    private void checkTweenAnim(){
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.tween_anim);
        //设置动画结束后保留结束状态
        anim.setFillAfter(true);//是否最终停留在动画停止的位置
        mImgAni2.startAnimation(anim);
    }

    private void checkRunAnim(){
        AnimationDrawable animationDrawable = (AnimationDrawable) mImgAni3.getBackground();
        animationDrawable.start();//开启动画

        Animation anim = AnimationUtils.loadAnimation(this,R.anim.run_anim);
        //设置动画结束后保留结束状态
        anim.setFillAfter(true);//是否最终停留在动画停止的位置
        mImgAni3.startAnimation(anim);

    }

    private void checkCircleAnim(){
        AnimationDrawable animationDrawable = (AnimationDrawable) mImgAni4.getBackground();
        animationDrawable.start();//开启帧动画

//        Animation anim = AnimationUtils.loadAnimation(this,R.anim.circle_anim);
//        //设置动画结束后保留结束状态
//        anim.setFillAfter(true);//是否最终停留在动画停止的位置
//        mImgAni4.startAnimation(anim);

        RotateAnimation rotateAnimation = new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,-0.5f);
        rotateAnimation.setDuration(3000);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setRepeatMode(Animation.RESTART);
        //让旋转动画一直转，不停顿的重点
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setRepeatCount(-1);
        mImgAni4.startAnimation(rotateAnimation);

    }




    /**
     * 帧动画的各种操作与逻辑
     *
     * */
    private void initFrameAnim(){
        ImageView frame_image;
        AnimationDrawable animationDrawable;

        frame_image = findViewById(R.id.img_anim1);
        // 获取 AnimationDrawable 对象
        // animationDrawable = (AnimationDrawable) frame_image.getBackground();

        animationDrawable = new AnimationDrawable();

        //插帧
        for (int i = 1; i < 10; i ++ ){
            int id = getResources().getIdentifier("frame0" + i, "drawable", getPackageName());
            Drawable drawable = getResources().getDrawable(id);
            animationDrawable.addFrame(drawable, 100);
        }

        for (int i = 10; i < 19; i ++){
            int id = getResources().getIdentifier("frame" + i, "drawable", getPackageName());
            Drawable drawable = getResources().getDrawable(id);
            animationDrawable.addFrame(drawable, 100);
        }

        /**
         * 开始播放
         * */
        // animationDrawable.start();//最简单的开启
        animationDrawable.setOneShot(true);
        frame_image.setImageDrawable(animationDrawable);
        // 获取资源对象
        animationDrawable.stop();
        // 特别注意：在动画start()之前要先stop()，不然在第一次动画之后会停在最后一帧，这样动画就只会触发一次
        animationDrawable.start();
        // 启动动画

        /**
         * 停止播放
         * */
        // animationDrawable.stop();
        animationDrawable.setOneShot(true);
        frame_image.setImageDrawable(animationDrawable);
        animationDrawable.stop();

    }
}
