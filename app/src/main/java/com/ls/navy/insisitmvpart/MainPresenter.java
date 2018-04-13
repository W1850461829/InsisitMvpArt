package com.ls.navy.insisitmvpart;

import com.ls.navy.insisitmvpart.mvp.BasePresenter;
import com.ls.navy.insisitmvpart.mvp.Message;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Android on 2018/4/13.
 */

public class MainPresenter extends BasePresenter {
    public void request(Message message) {
        addSubscribe(Observable.just(message)
                .filter(new Func1<Message, Boolean>() {
                    @Override
                    public Boolean call(Message message) {
                        return message != null;
                    }
                })
                .subscribe(new Action1<Message>() {
                    @Override
                    public void call(Message message) {
                        message.what = 0;
                        message.arg1 = R.color.colorPrimaryDark;
                        message.HandleMessageToTargetUnrecycle();
                        message.what = 1;
                        message.str = "hello" + message.obj;
                        message.HandleMessageToTarget();

                    }
                }));
    }

    public void request2(Message message) {
        addSubscribe(rx.Observable.just(message)
                .filter(new Func1<Message, Boolean>() {
                    @Override
                    public Boolean call(Message message) {
                        return message != null;
                    }
                }).subscribe(new Action1<Message>() {
                    @Override
                    public void call(Message message) {
                        message.what = 2;
                        message.HandleMessageToTarget();
                    }
                }));

    }

}
