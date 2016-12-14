package com.hpy1012.radiochef.retrofit;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hpy1012 on 2016/11/11.
 */

public abstract class RetrofitCallBack<M> implements Callback<M> {

    public abstract void onSuccess(M model);

    public abstract void onFailure(int code,String msg);

    public abstract void onThrowable(Throwable throwable);

    public abstract void onFinish();

    @Override
    public void onResponse(Call<M> call, Response<M> response) {
        if(response.isSuccessful()){
            onSuccess(response.body());
        }
        else {
            onFailure(response.code(),response.errorBody().toString());
        }
        onFinish();
    }

    @Override
    public void onFailure(Call<M> call, Throwable t) {
        onThrowable(t);
        onFinish();
    }
}
