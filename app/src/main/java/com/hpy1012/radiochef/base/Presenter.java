package com.hpy1012.radiochef.base;

/**
 * Created by hpy1012 on 2016/11/11.
 */

public interface Presenter<V> {
    void attachView(V view);

    void detachView();
}
