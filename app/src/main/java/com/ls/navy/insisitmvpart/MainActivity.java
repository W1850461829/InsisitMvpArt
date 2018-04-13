package com.ls.navy.insisitmvpart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ls.navy.insisitmvpart.base.BaseActivity;
import com.ls.navy.insisitmvpart.mvp.IPresenter;
import com.ls.navy.insisitmvpart.mvp.IView;
import com.ls.navy.insisitmvpart.mvp.Message;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresenter> implements IView {
    @BindView(R.id.tv_main)
    TextView mTextView;
    @BindView(R.id.activity_main)
    RelativeLayout mRoot;

    @Override
    protected void initData() {

    }

    @Override
    protected int initView() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void handleMessage(Message message) {
        switch (message.what) {
            case 0:
                mRoot.setBackgroundResource(message.arg1);
                break;
            case 1:
                mTextView.setText(message.str);
                break;

        }
    }

    @OnClick({R.id.btn_request, R.id.btn_second, R.id.btn_third, R.id.btn_fourth})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_request:
                mPresenter.request(Message.obtain(this, "jess!"));
                break;
            case R.id.btn_second:
                startActivity(new Intent(getApplicationContext(),SecondActivity.class));
                break;
            case R.id.btn_third:
                break;
            case R.id.btn_fourth:
                break;

        }
    }
}
