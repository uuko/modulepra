package com.example.modulepra.di;



import com.example.modulepra.model.Users;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface APIService {
    @GET("/users")
    Observable<List<Users>> getUsers();
}
