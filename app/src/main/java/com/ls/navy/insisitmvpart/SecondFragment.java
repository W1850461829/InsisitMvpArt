package com.ls.navy.insisitmvpart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ls.navy.insisitmvpart.mvp.IView;
import com.ls.navy.insisitmvpart.mvp.Message;

/**
 * Created by Android on 2018/4/13.
 */

public class SecondFragment extends BaseFragment<MainPresenter> implements IView {
    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void handleMessage(Message message) {
        switch (message.what) {
            case 0:
                mRootView.setBackgroundResource(message.arg1);
                break;
            case 1:
                showMessage(message.str);
                break;
        }
    }

    @Override
    protected void initData() {
        mPresenter.request(Message.obtain(this, "world!"));
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter();
    }
}
