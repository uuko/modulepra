package com.example.modulepra.di;

import android.content.Context;

/*自定義全局變量 Application
* 要在manifest加上android:name=""
* */
public class Application extends android.app.Application {

    private ApplicationComponent applicationComponent;

    public static Application get(Context context)
    {
        return (Application)context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent=DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .aPIModule(new APIModule())
                .sockettModule(new SockettModule())
                .build();
        applicationComponent.inject(this);
    }
    public ApplicationComponent getApplicationComponent(){
        return applicationComponent;
    }
}
