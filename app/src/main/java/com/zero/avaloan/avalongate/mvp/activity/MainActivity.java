package com.zero.avaloan.avalongate.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.egoo.chat.listener.ChatListener;
import com.egoo.chat.ui.activity.ChattingResolvedActivity;
import com.egoo.sdk.GlobalManager;
import com.lc.commonlib.User;
import com.zero.avaloan.avalongate.R;
import com.zero.avaloan.avalongate.base.BaseActivity;
import com.zero.avaloan.avalongate.mvp.presenter.MainPresenter;
import com.zero.avaloan.avalongate.mvp.view.MainCallBack;
import com.zero.avaloan.avalongate.utils.ToastUtils;

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
        initChat();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_confrom:
                String url = mInputEd.getText().toString();
//                presenter.getUrlData(url);
                ToastUtils.showToastSafeShort("前往登录");
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




    /**
     * android 初始化 客服
     *
     * */
    private void initChat(){
        GlobalManager.getInstance().addGlobalListener(new ChatListener() {

            @Override
            public void onConnectOpen(String status) {
//                Log.e("HomeActivity", status);
                if ("nomal".equals(status)) {

                } else if ("queued".equals(status)) {
                    ToastUtils.showToastSafeShort("你已经在排队中了");
                } else if ("established".equals(status)) {
                    ToastUtils.showToastSafeShort("您已经有会话在了");
                }
            }
        });

        User user = new User();
        user.setClientLevel(1);
        user.setSkillGroup("egoo_test");
        user.setSilentGroup("egoo_silent");
        user.setBizType("378");
        user.setTenantId("egoo");//egoo
        user.setFromUserName("18710998518");
        user.setChannelType("appchat");

        GlobalManager.getInstance().init(user, true, MainActivity.this);
        GlobalManager.getInstance().startConnect();
        jump2Page();

    }

    /**
     * android 开启页面
     *
     * 注意权限 和app里的初始化
     *
     * */
    private void jump2Page(){
        String jsonStr = "{\n" +
                "\"bizNo\": \"1234\", " +
                "\"productInfo\": \"豆豆钱\"," +
                "\"phone\": \"155**** ***1\"," +
                "\"ip\": \"192.168.10.0\"," +
                "\"clientSys\": \"app\"," +
                "\"channelType\": \"Appchat\"," +
                "\"productId\": \"kkd\"\n" +
                "}";
        ChattingResolvedActivity.startChat(this,jsonStr);
        finish();
    }

}
