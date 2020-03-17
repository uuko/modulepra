package com.example.modulepra.ui.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity implements BaseContract  {

    private ProgressDialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    public void init() {

    }

    @Override
    public void showProgressDialog(String s) {
        dismissProgressDialog();
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("waiting");
        /*dialog彈出後會點擊螢幕或物理返回鍵，dialog不消失*/
        progressDialog.setCancelable(false);
        /*dialog彈出後會點擊螢幕，dialog不消失；點擊物理返回鍵dialog消失*/
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        if (progressDialog !=null && progressDialog.isShowing()){
            progressDialog.cancel();
        }

    }

    @Override
    public void showToast(String s) {
        Toast.makeText(this,s,Toast.LENGTH_SHORT);
    }
}
