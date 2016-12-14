package com.hpy1012.radiochef.base;


import android.app.Activity;

import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hpy1012.radiochef.R;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by hpy1012 on 2016/11/11.
 */
public abstract class BaseFragment extends Fragment {
    public Activity mActivity;

    /**
     * 视图是否已经初始化
     */
    protected boolean isInit = false;
    protected boolean isLoad = false;
    protected final String TAG = "BaseFragment";
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(setContentView(), container, false);
        isInit = true;
        /**初始化的时候去加载数据**/
        isCanLoadData();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mActivity = getActivity();
    }

    /**
     * @return 布局的id
     */
    protected abstract int setContentView();

    protected View getContentView() {
        return view;
    }


    /**
     * 视图是否已经对用户可见，系统的方法
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isCanLoadData();
    }

    public void isCanLoadData() {
        if (!isInit) {
            return;
        }
        if (getUserVisibleHint()) {
            lazyLoad();
            isLoad = true;
        } else {
            if (isLoad) {
                stopLoad();
            }
        }
    }

    /**
     * 视图销毁的时候将Fragment是否初始化的状态变为false
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isInit = false;
        isInit = false;
    }

    /**
     * 当视图初始化且对用户可见的时候去真正加载数据
     */
    protected abstract void lazyLoad();

    /**
     * 当视图已经对用户不可见并且加载过数据，如果需要在切换的时候停止加载数据，可以覆写该方法
     */
    protected void stopLoad() {

    }


    public void toastShow(int resId) {
        Toast.makeText(mActivity, resId, Toast.LENGTH_SHORT).show();
    }

    public void toastShow(String resId) {
        Toast.makeText(mActivity, resId, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        onUnsubscribe();
    }

    private CompositeSubscription mCompositeSubscription;

    public void onUnsubscribe() {
        //取消注册，以避免内存泄露
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    public void addSubscription(Subscription subscription) {
//        if (mCompositeSubscription == null) {
        mCompositeSubscription = new CompositeSubscription();
//        }
        mCompositeSubscription.add(subscription);
    }

}
