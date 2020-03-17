package com.example.modulepra;

import com.example.modulepra.model.Users;
import com.example.modulepra.ui.base.BaseAttachImpl;
import com.example.modulepra.ui.base.BaseContract;

import java.util.List;

public interface MainContract {
    interface  View extends BaseContract{
        void setData(List<Users> usersList);
    }
    interface  Presenter<V extends  View> extends BaseAttachImpl<V> {
        void getUserList();
    }
}
