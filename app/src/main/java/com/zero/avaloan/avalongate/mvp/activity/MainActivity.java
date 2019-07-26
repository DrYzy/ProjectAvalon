package com.zero.avaloan.avalongate.mvp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.zero.avaloan.avalongate.R;
import com.zero.avaloan.avalongate.base.BaseActivity;
import com.zero.avaloan.avalongate.mvp.presenter.MainPresenter;
import com.zero.avaloan.avalongate.mvp.view.MainCallBack;

import java.lang.ref.WeakReference;

/**
 *
 * 在MVP结构中，activity不再做程序业务处理的主体
 * 只作为控制页面的部分
 *
 * **/

public class MainActivity extends BaseActivity implements View.OnClickListener,MainCallBack {
    @BindView(R.id.ed_input)
    EditText mInputEd;
    @BindView(R.id.btn_confrom)
    Button mBtnCfrom;
    @BindView(R.id.tv_show)
    TextView mShowText;
    private MainPresenter presenter;
    private MyHandler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        super.init();
    }

    @Override
    protected void instantiation() {
        super.instantiation();
        presenter = new MainPresenter(this);
        handler = new MyHandler(this);
    }

    @Override
    protected void dataBind() {
        super.dataBind();
    }

    @Override
    protected void eventBind() {
        super.eventBind();
        mBtnCfrom.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_confrom:
                String url = mInputEd.getText().toString();
//                presenter.getUrlData(url);
                startActivity(new Intent(this,LoginActivity.class));
                break;
        }
    }

    @Override
    public void getMessage(String message) {
        Message msg = handler.obtainMessage(0, message);
        handler.sendMessage(msg);
    }

    @Override
    public void error() {
        Message msg = handler.obtainMessage(1, "error");
        handler.sendMessage(msg);
    }

    private static class MyHandler extends Handler {
        private WeakReference<MainActivity> reference;

        private MyHandler(MainActivity activity) {
            reference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MainActivity activity = reference.get();
            switch (msg.what) {
                case 0:
                    activity.mShowText.setText(msg.obj.toString());
                    break;
                case 1:
                    activity.mShowText.setText(msg.obj.toString());
                    break;
            }
        }
    }
}
