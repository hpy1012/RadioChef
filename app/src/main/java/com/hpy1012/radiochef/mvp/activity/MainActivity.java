package com.hpy1012.radiochef.mvp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hpy1012.radiochef.R;
import com.hpy1012.radiochef.base.BasePresenterImpl;
import com.hpy1012.radiochef.mvp.MvpActivity;
import com.hpy1012.radiochef.mvp.presenter.MainPresenter;

public class MainActivity extends MvpActivity<MainPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter() ;
    }


}
