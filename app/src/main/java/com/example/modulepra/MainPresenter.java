package com.example.modulepra;

import android.util.Log;

import com.example.modulepra.di.APIService;
import com.example.modulepra.model.Users;
import com.example.modulepra.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter <V extends  MainContract.View> extends BasePresenter<V> implements MainContract.Presenter<V> {

    /*@Inject 一般情况下是标注成员属性和构造函数
        标注的成员属性不能是private，Dagger 2 还支持方法注入
        @Inject还可以标注方法*/

    /*不能使用的狀況有
        * 接口没有构造函数
            *第三方库的类不能被标注
            *构造函数中的参数必须配置*/
  @Inject
   public  MainPresenter(APIService apiService, CompositeDisposable compositeDisposable){
       super(apiService,compositeDisposable);
   }



    @Override
    public void getUserList() {
       getView().showProgressDialog("loading");
       /*CompositeDisposable 多個訂閱時使用 方便管理*/
        getCompositeDisposable().add(getApiService()
        .getUsers()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new DisposableObserver<List<Users>>() {
            @Override
            public void onNext(List<Users> value) {
                getView().setData(value);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("kk", "onError: "+e);
            }

            @Override
            public void onComplete() {
                getView().dismissProgressDialog();
            }
        }));


    }


}
