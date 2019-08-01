package com.zero.avaloan.avalongate.mvp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import butterknife.ButterKnife;
import com.zero.avaloan.avalongate.R;
import com.zero.avaloan.avalongate.base.BaseActivity;
import com.zero.avaloan.avalongate.bean.SubjectBean;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

import java.util.ArrayList;
import java.util.List;

public class RxjavaActivity extends BaseActivity {
    private List<SubjectBean> mSubjectList ;
    private int a = 0;
    private int b = 0;
    private int c = 0;
    private int d = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        ButterKnife.bind(this);
        super.init();
    }

    @Override
    protected void instantiation() {
        super.instantiation();
        mSubjectList = new ArrayList<>();
        showRxjavaSimple();
    }

    private void showRxjavaSimple(){
        for(int i = 0;i<9;i++){
            SubjectBean mBean = new SubjectBean();
            mSubjectList.add(mBean);
        }

        //声明的时候不能只写数据类型，而是要写他们的封装类
        String[] mNameArray = {"语文","数学","物理","化学","生物","英语","历史","政治","地理","体育"};
        String[] mTeacherArray = {"杜老师","王老师","韩老师","杜老师","李老师","冯老师","张老师","王老师","韩老师","王老师"};
        Integer[] mScoreArray = {120,130,99,77,57,120,70,85,60,30};
        Boolean[] mNecessary = {true,true,true,true,true,false,false,false,false,true};

        //rxjava 的写法，rxjava2并不支持
        Observable.from(mNameArray)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        mSubjectList.get(a).setSubName(s);
                        a++;
                        System.out.println("onNext--> " + s);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        System.out.println("onError--> " + throwable.getMessage());
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        System.out.println("onComplete");
                    }
                });
        Observable.from(mTeacherArray)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        mSubjectList.get(b).setTeacherName(s);
                        b++;
                    }
                });

        Observable.from(mScoreArray)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer s) {
                        mSubjectList.get(c).setScore(s);
                        c++;
                    }
                });

        Observable.from(mNecessary)
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Boolean s) {
                        mSubjectList.get(d).setNecessary(s);
                        d++;
                    }
                });

        if(mSubjectList.size()>0){

        }

        /**
         * Observer  和 subscriber 的区别
         *
         * 在 RxJava 的 subscribe 过程中，
         * Observer 也总是会先被转换成一个 Subscriber 再使用。
         * 所以如果你只想使用基本功能，
         * 选择 Observer 和 Subscriber 是完全一样的
         *
         * Subscriber是Observer的实现类
         * 区别：
         * Subscriber 中有 onStart():开始
         * Subscriber 中有 unSubscribe():解绑
         *
         * */
    }
}
