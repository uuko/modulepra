package com.example.modulepra.db;

import android.content.Context;
import android.util.Log;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainDbMgr {
    private static MainDbMgr instance;
    private final String TAG = "[AlertDBMgr]";
    CompositeDisposable mDisposable = new CompositeDisposable();

    public static MainDbMgr getInstance() {
        if (instance == null) {
            instance = new MainDbMgr();
        }
        return instance;
    }




    public CompositeDisposable getmDisposable() {
        return mDisposable;
    }
    public void updateSharesItem(final Context mContext, final LoadDBListener.InsertListener listener, MainTable entity) {
        MainDBDataBase database = MainDBDataBase.getInstance(mContext);
        Log.d("updateSharesItem", "insertSharesItem: ");
        mDisposable.add(database.mainDbDao().updateUsers(entity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        listener.onComplete("ok");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("updateSharesItem", "insertSharesItem: " + throwable);
                        listener.onError("Error" + throwable);
                    }
                }));
    }


    public void insertSharesItem(final Context mContext, final LoadDBListener.InsertListener listener, List<MainTable> entity) {
        MainDBDataBase database = MainDBDataBase.getInstance(mContext);
        Log.d("dbbbbbbbbbbbbbbb", "insertSharesItem: ");
        mDisposable.add(database.mainDbDao().insertAllPrivateData(entity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        listener.onComplete("ok");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("dbbbbbbbbbbbbbbb", "insertSharesItem: " + throwable);
                        listener.onError("Error" + throwable);
                    }
                }));
    }

    public void loadAllSharedDataByQbeeID(Context mContext, int QbeeID, final LoadDBListener.SearchListener listener) {
        MainDBDataBase database = MainDBDataBase.getInstance(mContext);
        database.mainDbDao().loadAllSharedDataByQbeeID(QbeeID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<MainTable>>() {
                    @Override
                    public void onSubscribe( Disposable d) {
                        Log.d("loadAllS", "onSubscribe: ");
                    }

                    @Override
                    public void onSuccess( List<MainTable> sharedEntityList) {
                        Log.d("loadAll", "onSuccess: ");
                        listener.onSharedFinished(sharedEntityList);
                    }

                    @Override
                    public void onError( Throwable e) {
                        Log.d("loadAll", "onError: ");
                        listener.onError("" + e);
                    }
                });


    }

    public void deletSharedData(final Context mContext, final LoadDBListener.DeletListener listener, MainTable entity) {
        MainDBDataBase database = MainDBDataBase.getInstance(mContext);
        mDisposable.add(database.mainDbDao().deleteUsers(entity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d("dbbbbbbbbbbbbbbb", "onDeleteComplete: ");
                        listener.onDeleteComplete();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("dbbbbbbbbbbbbbbb", "onDeleteaccept: ");
                        listener.onError("Error" + throwable);
                    }
                }));
    }

}
