package com.hpy1012.radiochef.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragment预加载：
 * 1.可以懒加载的Fragment
 * 2.切换到其他页面时停止加载数据（可选）
 * Created by hpy1012 on 2016/11/23.
 */

public abstract class LazyLoadFragment extends Fragment {
    /**
     * 视图是否已经初始化
     */
    protected boolean isInit = false;
    protected boolean isLoad = false;
    protected final String TAG = "LazyLoadFragment";
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


}
