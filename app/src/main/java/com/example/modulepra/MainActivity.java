package com.example.modulepra;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.modulepra.db.LoadDBListener;
import com.example.modulepra.db.MainDbMgr;
import com.example.modulepra.db.MainTable;
import com.example.modulepra.di.APIService;
import com.example.modulepra.di.Application;
import com.example.modulepra.model.Users;
import com.example.modulepra.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity implements MainContract.View
        , LoadDBListener.DeletListener,LoadDBListener.InsertListener,LoadDBListener.SearchListener {

    @Inject
    APIService getApiService;

    @Inject
    MainContract.Presenter<MainContract.View> presenter;

    public CompositeDisposable compositeDisposable;
    private MainComponent mainComponent;
    private TextView tv;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainComponent = DaggerMainComponent.builder()
                .applicationComponent(((Application) getApplication()).getApplicationComponent())
                .mainModule(new MainModule(this))
                .build();

        mainComponent.inject(this);
        tv=findViewById(R.id.tv);
        button=findViewById(R.id.button);
        presenter.onAttached(this);
        //getUserList();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getUserList();
            }
        });

    }

    public void getUserList(){
        getApiService.getUsers().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Users>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Users> value) {
                        Log.e("tv", "onNext: "+value.get(0).getEmail() );
                        tv.setText(value.get(0).getUsername());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("yy", "onError: "+e );
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void setData(List<Users> usersList) {
        Log.e("yy", "setData: "+usersList.get(0).getEmail());
        tv.setText(usersList.get(0).getUsername());
        List<MainTable> mainTableList=new ArrayList<>();
        for (Users users:usersList){
            MainTable mainTable=new MainTable(users.getName(),users.getUsername(),users.getEmail(),users.getPhone()
                    ,users.getWebsite());
            mainTableList.add(mainTable);
        }

        MainDbMgr.getInstance().insertSharesItem(this,this,mainTableList);

    }

    @Override
    public void onComplete(String a) {

    }

    @Override
    public void onSharedFinished(List<MainTable> sharedEntityList) {

    }

    @Override
    public void onDeleteComplete() {

    }

    @Override
    public void onError(String a) {

    }
}
