package com.hpy1012.radiochef.mvp;

import android.os.Bundle;

import com.hpy1012.radiochef.base.BaseActivity;
import com.hpy1012.radiochef.base.BasePresenterImpl;

/**
 * Created by hpy1012 on 2016/11/11.
 */

public abstract class MvpActivity<P extends BasePresenterImpl> extends BaseActivity {
    protected P mvpPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }
    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }
}
