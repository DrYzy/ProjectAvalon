package com.zero.avaloan.avalongate.mvp.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.zero.avaloan.avalongate.R;
import com.zero.avaloan.avalongate.base.BaseActivity;
import com.zero.avaloan.avalongate.mvp.model.LoginModel;
import com.zero.avaloan.avalongate.mvp.presenter.LoginPresenter;
import com.zero.avaloan.avalongate.mvp.view.LoginContract;
import com.zero.avaloan.avalongate.net.ServerError;
import com.zero.avaloan.avalongate.net.ServerException;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends BaseActivity implements LoginContract.View,View.OnClickListener {
    @BindView(R.id.ed_username)
    EditText mEdUserName;
    @BindView(R.id.ed_password)
    EditText mEdPassWord;
    @BindView(R.id.btn_login)
    Button mBtnLogin;

    private ProgressDialog mDialog;//加载用的弹框
    private LoginPresenter presenter;//登录的presenter

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

        presenter = new LoginPresenter(this, new LoginModel());
    }

    @Override
    protected void dataBind() {
        super.dataBind();
    }

    @Override
    protected void eventBind() {
        super.eventBind();
        mBtnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                login();
                break;
        }
    }

    private void login(){
        presenter.login(mEdUserName.getText().toString(), mEdPassWord.getText().toString());
    }



    @Override
    public void setLoading(boolean v) {
        if (mDialog == null) {
            mDialog = new ProgressDialog(this);
            mDialog.setMessage("正在加载 ...");
            mDialog.setCanceledOnTouchOutside(false);
        }
        if (v) {
            mDialog.show();
        } else {
            mDialog.hide();
        }
    }

    @Override
    public void callback(String contentStr) {
        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void errorCallback(ServerException e) {
        switch (e.code) {
            case ServerError.NO_USER:
                Toast.makeText(this, "没有该用户", Toast.LENGTH_LONG).show();
                break;
            case ServerError.UNAUTHORIZED:
                Toast.makeText(this, "密码错误", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
