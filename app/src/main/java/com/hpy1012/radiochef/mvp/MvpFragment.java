package com.hpy1012.radiochef.mvp;

import android.os.Bundle;
import android.view.View;

import com.hpy1012.radiochef.base.BaseFragment;
import com.hpy1012.radiochef.base.BasePresenterImpl;

/**
 * Created by hpy1012 on 2016/11/11.
 */

public abstract class MvpFragment<P extends BasePresenterImpl> extends BaseFragment {
    protected P mvpPresenter;
    private boolean isLazyLoad;
    private boolean isPrepared;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mvpPresenter = createPresenter();
    }
    protected abstract P createPresenter();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }
}
