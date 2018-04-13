package com.ls.navy.insisitmvpart.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ls.navy.insisitmvpart.mvp.IPresenter;

import org.simple.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Android on 2018/4/13.
 */

public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity {
    protected final String TAG = this.getClass().getSimpleName();
    private Unbinder mUnbinder;
    protected P mPresenter;

    @Nullable
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = getPresenter();
        if (useEventBus())
            EventBus.getDefault().register(this);
            setContentView(initView());
            mUnbinder = ButterKnife.bind(this);
            initData();

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (mPresenter == null) {
            mPresenter = getPresenter();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.onDestory();
        if (mUnbinder != Unbinder.EMPTY) mUnbinder.unbind();
        if (useEventBus()) EventBus.getDefault().unregister(this);
        this.mPresenter = null;
        this.mUnbinder = null;
    }

    protected abstract void initData();

    protected abstract int initView();


    protected boolean useEventBus() {
        return true;
    }

    protected abstract P getPresenter();
}
