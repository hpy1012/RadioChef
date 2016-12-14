package com.hpy1012.radiochef.retrofit;

import com.hpy1012.radiochef.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hpy1012 on 2016/11/11.
 */

public class AppClient {
    public static Retrofit mRetrofit;

    public static Retrofit retrofit(String url){
        if(mRetrofit==null){
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            if(BuildConfig.DEBUG){
                //Log信息拦截器
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                //设置Debug Log模式
                builder.addInterceptor(loggingInterceptor);
            }
            OkHttpClient okHttpClient = builder.build();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    //设置RxJava适配器
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    //设置Json转换器
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return mRetrofit;
    }
}
