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

    /*@Inject 一般情況下是標注成員屬性和構造函數
    標注的成員屬性不能是private，Dagger 2 還支持方法注入
    @Inject還可以標注方法
*/

    /**contract presenter -> implement  (di  goto true)
     *  contract view -> getView( )->getfun implement->activity
     *  attacher bind view*/
    /*不能使用的狀況有
     * 介面沒有構造函數
     *第三方repository/library的類不能被標注
     *構造函數中的參數必須配置
     */
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
