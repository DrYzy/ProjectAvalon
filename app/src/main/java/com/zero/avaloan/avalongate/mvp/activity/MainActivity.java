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
import com.egoo.sdk.GlobalManager;
import com.egoo.sdk.entiy.FunConfig;
import com.egoo.sdk.entiy.User;
import com.zero.avaloan.avalongate.R;
import com.zero.avaloan.avalongate.base.BaseActivity;
import com.zero.avaloan.avalongate.mvp.presenter.MainPresenter;
import com.zero.avaloan.avalongate.mvp.view.MainCallBack;
import com.zero.avaloan.avalongate.utils.ToastUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.UUID;

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
            public void onOrderClick() {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }

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
//        user.setSkillGroup("egoo_xa");
        user.setSkillGroup("jd_daojia");//京东在线
//        user.setSkillGroup("jd_yiyao");
        user.setSilentGroup("egoo_silent");
        user.setBizType("200");
        user.setTenantId("egoo");//egoo
//        user.setTenantId("dada");//达达
        user.setFromUserName("18710998518");
        user.setChannelType("appchat");

        user.setUserPin(UUID.randomUUID().toString());

        user.setFromUserName("13753102373");
        user.setUserName("13753102373");

        GlobalManager.getInstance().init(user, true, MainActivity.this);
        GlobalManager.getInstance().startConnect();

    }

    /**
     * android 开启页面
     *
     * 注意权限 和app里的初始化
     *
     * */
    private void jump2Page(){
        JSONObject json = new JSONObject();
        try {
//            json.put("time","2018-01-02  15.12.30");
            json.put("time",null);
            json.put("orderid","127837867723");
            json.put("money","￥18.6");
            json.put("cancelReason", "态度不好");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        GlobalManager.getInstance().startChat(this,json.toString(), new FunConfig(true));
    }

}
