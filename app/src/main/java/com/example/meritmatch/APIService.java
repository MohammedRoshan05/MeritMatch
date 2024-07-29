package com.example.meritmatch;

import retrofit2.Call;
import retrofit2.http.GET;

interface ApiService {
    @GET("/user")
    Call<User> getUser();
}

