package com.zero.avaloan.avalongate.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.zero.avaloan.avalongate.R;
import com.zero.avaloan.avalongate.mvp.activity.AnimationActivity;
import com.zero.avaloan.avalongate.mvp.activity.LoginActivity;
import com.zero.avaloan.avalongate.mvp.activity.RxjavaActivity;

public class HomeActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.btn_mvp)
    Button mBtnMvp;
    @BindView(R.id.btn_animation)
    Button mBtnAni;
    @BindView(R.id.btn_rx)
    Button mBtnRxj;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        super.init();
    }

    @Override
    protected void instantiation() {
        super.instantiation();
        setHeader(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        },"首页");

    }

    @Override
    protected void dataBind() {
        super.dataBind();
    }

    @Override
    protected void eventBind() {
        super.eventBind();
        mBtnMvp.setOnClickListener(this);
        mBtnAni.setOnClickListener(this);
        mBtnRxj.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_mvp:
                startActivity(new Intent(HomeActivity.this,LoginActivity.class));
                break;
            case R.id.btn_animation:
                startActivity(new Intent(HomeActivity.this,AnimationActivity.class));
                break;
            case R.id.btn_rx:
                startActivity(new Intent(HomeActivity.this,RxjavaActivity.class));
                break;
        }
    }


}
