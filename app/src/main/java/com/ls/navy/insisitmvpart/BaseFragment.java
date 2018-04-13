package com.ls.navy.insisitmvpart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ls.navy.insisitmvpart.mvp.IPresenter;

import org.simple.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Android on 2018/4/13.
 */

public abstract class BaseFragment<P extends IPresenter> extends Fragment {
    protected View mRootView;
    protected P mPresenter;
    private Unbinder mUnbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = getPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = initView(inflater, container);
        mUnbinder = ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (useEventBus())
            EventBus.getDefault().register(this);
        initData();

    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (mPresenter == null) {
            mPresenter = getPresenter();

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != Unbinder.EMPTY) mUnbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.onDestory();
        if (useEventBus())
            EventBus.getDefault().unregister(this);
        this.mPresenter = null;
        this.mRootView = null;
        this.mUnbinder = null;
    }

    protected abstract void initData();

    private boolean useEventBus() {
        return true;
    }

    protected abstract View initView(LayoutInflater inflater, ViewGroup container);

    protected abstract P getPresenter();

}
