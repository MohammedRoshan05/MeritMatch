package com.example.meritmatch;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

interface ApiService {
    //User Endpoints
    @GET("user/{User_name}")
    Call<User> getUser(@Path("User_name") String userName);

    @POST("user/login")
    Call<LoginResponse> loginUser(@Body UserOperation user);

    @POST("user/signup")
    Call<User> signupUser(@Body UserOperation user);

    //Task Endpoints
    @POST("task/postTask")
    Call<Task> postTask(@Body Task task);

    @GET("/task/getStatus/{User_name}")
    Call<Status> getStatus(@Path("User_name") String userName);

    @PUT("/task/reserve")
    Call<Status> reserve(@Body ReserveOperation reserve);

    @GET("/tasks")
    Call<List<Task_database>> getTasks();

    @PUT("/task/updateKarma")
    Call<Status> updateKarma(@Body ReserveOperation reserveOperation, @Query("Karma") int karma);
    @GET("/task/{User_name}")
    Call<TaskApproval> getTask(@Path("User_name") String userName);

}

