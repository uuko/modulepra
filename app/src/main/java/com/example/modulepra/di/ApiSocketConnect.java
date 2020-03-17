package com.example.modulepra.di;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketAddress;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

@Singleton
public class ApiSocketConnect implements ApiSocketProvider {

    private Socket socket;
    private SocketAddress socketAddress;
    public static final String CONNECTING = "CONNECTING";
    public static final String CONNECTED = "CONNECTED";
    public static final String DISCONNECTED = "DISCONNECTED";

    private InputStream mInputStream;

    /*參數一定要component給予 不然就是無參數才能inject*/
    @Inject
    public ApiSocketConnect(@Named("totalAddress") SocketAddress mSocketAddress) {
        this.socketAddress = mSocketAddress;
    }


    @Override
    public Observable<String> openConnection() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                try {
                    e.onNext(CONNECTING);
                    socket = new Socket();
                    socket.connect(socketAddress, 5000);
                    e.onNext(CONNECTED);
                    /*
                    *                   if .... else
                    * */
                }catch (Exception err) {
                    closeConnection();
                    e.onError(err);
                }

            }
        });
    }

    @Override
    public void closeConnection() {
        try {
            if (mInputStream != null) {
                mInputStream.close();
                mInputStream = null;
            }

            if (socket != null && socket.isConnected()) {
                socket.close();
                socket = null;
            }
        } catch (IOException e) {
            Log.e("closeConnection", e.getMessage(), e);
        }
    }
}
