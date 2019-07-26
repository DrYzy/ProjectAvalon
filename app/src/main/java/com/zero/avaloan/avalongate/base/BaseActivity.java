package com.zero.avaloan.avalongate.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.zero.avaloan.avalongate.R;
import com.zero.avaloan.avalongate.bean.UserInfo;

public class BaseActivity extends Activity {
    protected UserInfo userInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userInfo = UserInfo.getInstance();
    }
    /**
     * 实例化开始
     * 子类都要重写onCreate
     * 功能的入口让子类自己调用
     */
    protected void init() {
        instantiation();
        dataBind();
        eventBind();
    }

    /**
     * 对象实例化
     */
    protected void instantiation() {
        // TODO 将布局文件中的控件名称转换成控件对象
    }

    /**
     * 数据绑定
     */
    protected void dataBind() {
        // TODO 为界面打开时就需要赋值的控件对象赋值
    }

    /**
     * 事件绑定
     */
    protected void eventBind() {
        // TODO 编写事件处理程序，关联控件对象的相应事件类型
    }
    /**
     * 设置标题
     */
    protected void setHeader(View.OnClickListener mListener,String titleStr){
        LinearLayout linearBack = (LinearLayout)findViewById(R.id.linear_back);
        TextView tvTitle = (TextView)findViewById(R.id.tv_title);
        linearBack.setOnClickListener(mListener);
        tvTitle.setText(titleStr);
    }
}
