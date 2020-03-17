package com.example.modulepra.di;


import android.content.Context;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/*
* Component ->Module
* Module -> provides
* Inject
* */
@Module
public class APIModule {

    /*Singleton 單例模式 在同一個component作用域下只有一個*/
    /*Provides 提供依賴實例*/
    /*
         * 使用 @Named 标记 Module 中生成类实例的方法
            使用 @Named 标记目标类中相应类实例
          * */

    @Provides
    @Singleton
    APIService provideUserApi(@Named("UserApi")Retrofit retrofit){
        return  retrofit.create(APIService.class);
    }

    @Provides
    @Singleton
    @Named("UserApi")
    Retrofit provideUserRetrofit(@Named("UserApiUrl") String url,
                                 @Named("UserApiClient") OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        //addCallAdapterFactory 是要支援有Observerable的
    }

    @Singleton
    @Provides
    @Named("UserApiUrl")
    String provideUserApiUrl(){
        return "https://jsonplaceholder.typicode.com";

        //如果要getResource要context.get ...
    }

    @Singleton
    @Provides
    @Named("UserApiClient")
    OkHttpClient provideUserOkhttpClient(HttpLoggingInterceptor httpLoggingInterceptor){
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .build();

    }

    //HttpLoggingInterceptor 有404 200 等連線狀態用的
    @Provides
    @Singleton
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }
}
