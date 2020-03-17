package com.example.modulepra.di;

import io.reactivex.Observable;

public interface ApiSocketProvider {
    Observable<String> openConnection();
    void closeConnection();
}
