package com.zero.avaloan.avalongate.mvp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import butterknife.ButterKnife;
import com.zero.avaloan.avalongate.R;
import com.zero.avaloan.avalongate.base.BaseActivity;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
        },"登录");
    }

    @Override
    protected void dataBind() {
        super.dataBind();
    }

    @Override
    protected void eventBind() {
        super.eventBind();
    }
}
