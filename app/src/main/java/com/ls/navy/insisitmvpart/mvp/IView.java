package com.ls.navy.insisitmvpart.mvp;

/**
 * Created by Android on 2018/4/13.
 */

public interface IView {
    void showLoading();

    void hideLoading();

    void showMessage(String message);

    void handleMessage(Message message);
}
