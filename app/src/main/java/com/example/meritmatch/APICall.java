package com.example.meritmatch;

import android.content.Context;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APICall {
    public interface UserCallback{
        void onResponse(User user);
    }

    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://chasedeux.vercel.app/")
            .addConverterFactory(GsonConverterFactory.create()).build();
    ApiService service =retrofit.create(ApiService.class);

    public void getUser(Context context,final UserCallback callback){
        Call<User> call= service.getUser();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    User user = response.body();
                    Log.d("API Response", "User Name: " + user.getUser_name());
                    Log.d("API Response", "Karma: " + user.getKarma());
                } else {
                    Log.e("API Response", "Response not successful or body is null");
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("API Response", "Error: " + t.getMessage());
            }
        });
    }
}
