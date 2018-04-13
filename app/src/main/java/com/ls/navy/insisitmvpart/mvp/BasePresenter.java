package com.ls.navy.insisitmvpart.mvp;

import org.simple.eventbus.EventBus;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Android on 2018/4/13.
 */

public class BasePresenter implements IPresenter {
    protected CompositeSubscription compositeSubscription;

    public BasePresenter() {
        onStart();
    }

    @Override
    public void onStart() {
        if (useEventBus()) {
            EventBus.getDefault().register(this);
        }
    }

    protected boolean useEventBus() {
        return true;
    }

    @Override
    public void onDestory() {
        if (useEventBus())
            EventBus.getDefault().unregister(this);
            unSubscribe();
            this.compositeSubscription = null;

    }

    protected void unSubscribe() {
        if (compositeSubscription != null) {
            compositeSubscription.unsubscribe();
        }

    }


    protected void addSubscribe(Subscription subscription) {
        if (compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();

        }
        compositeSubscription.add(subscription);
    }
}
