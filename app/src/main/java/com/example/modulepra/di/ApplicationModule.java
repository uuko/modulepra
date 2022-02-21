package com.example.modulepra.di;

import android.app.Application;
import android.content.Context;

import com.example.modulepra.ui.base.BaseContract;
import com.example.modulepra.ui.base.BasePresenter;

import java.util.Observable;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public abstract class ApplicationModule {

    private Application mApplication;


//    /*從全局 application拿到application*/
//    public ApplicationModule(Application application){
//        this.mApplication=application;
//    }

    /*提供整個應用程序的上下文*/
    @Provides
    public static Context provideContext(com.example.modulepra.di.Application mApplication){
        return mApplication.getApplicationContext();
    }

    /*提供整個應用程序的application*/
    @Provides
    public static Application provideApplication(){
        return new com.example.modulepra.di.Application();
    }

    /*提供Composiable 為了給別的Module使用*/
    @Provides
    public static  CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }
}
