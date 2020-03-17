package com.example.modulepra;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    private AppCompatActivity appCompatActivity;
    public MainModule(AppCompatActivity appCompatActivity){
        this.appCompatActivity=appCompatActivity;
    }

    @Provides
    @MainActScoped
    AppCompatActivity provideActivity(){
        return appCompatActivity;
    }

    @Provides
    @MainActScoped
    Context provideContext(){
        return appCompatActivity;
    }

    @Provides
    @MainActScoped
    MainContract.Presenter<MainContract.View> providePresenter(MainPresenter<MainContract.View> presenter) {
        return presenter;
    }
}
