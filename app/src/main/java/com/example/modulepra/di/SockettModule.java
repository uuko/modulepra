package com.example.modulepra.di;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SockettModule {

    @Provides
    @Singleton
    @Named("APIServiceConnection")
    ApiSocketProvider apiSocketProviderConnect(ApiSocketConnect connect){
        return connect;
    }

    @Provides
    @Singleton
    @Named("totalAddress")
    SocketAddress provideTotalSocketAddress() {
        return new InetSocketAddress(5555);
    }
}
