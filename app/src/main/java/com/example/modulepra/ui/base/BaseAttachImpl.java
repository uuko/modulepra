package com.example.modulepra.ui.base;

public interface BaseAttachImpl<V extends BaseContract> {
    void onAttached(V view);
    void  onDetached();
}
