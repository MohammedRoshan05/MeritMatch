package com.example.meritmatch;

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
    Call<ClassUser> getUser(@Path("User_name") String userName);

    @POST("user/login")
    Call<ClassLoginResponse> loginUser(@Body ClassUserOperation user);

    @POST("user/signup")
    Call<ClassUser> signupUser(@Body ClassUserOperation user);

    //Task Endpoints
    @POST("task/postTask")
    Call<ClassTask> postTask(@Body ClassTask classTask);

    @GET("/task/getStatus/{User_name}")
    Call<ClassStatus> getStatus(@Path("User_name") String userName);

    @PUT("/task/reserve")
    Call<ClassStatus> reserve(@Body ClassReserveOperation reserve);

    @GET("/tasks")
    Call<List<ClassTask_database>> getTasks();

    @PUT("/task/updateKarma")
    Call<ClassStatus> updateKarma(@Body ClassReserveOperation classReserveOperation, @Query("Karma") int karma);
    @GET("/task/{User_name}")
    Call<ClassTaskApproval> getTask(@Path("User_name") String userName);

}

