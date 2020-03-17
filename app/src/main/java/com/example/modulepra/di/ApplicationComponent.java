package com.example.modulepra.di;

import android.app.Application;

import java.net.SocketAddress;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import io.reactivex.disposables.CompositeDisposable;

@Singleton
@Component(modules = {APIModule.class
                        ,ApplicationModule.class
                        ,SockettModule.class})
public interface ApplicationComponent {
    /*depency需求者可以呼叫*/
    void  inject(Application application);

    /*跟其他的component是依賴關係 所以提供給他們*/
    APIService getUserApi();

    CompositeDisposable getCompositediposable();


    /*如果有name的話一定要記得給*/
    @Named("APIServiceConnection")
    ApiSocketProvider apiSocketProviderConnect();




}
