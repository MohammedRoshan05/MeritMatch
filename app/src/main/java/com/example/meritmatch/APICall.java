package com.example.meritmatch;

import android.content.Context;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APICall{
    public interface UserCallback {
        void onResponse(User user);
    }
    public interface LoginCallback {
        void onResponse(LoginResponse response);
    }
    public interface SignupCallback {
        void onResponse(User user);
    }
    public interface postTaskCallback {
        void onResponse(Task task);
    }
    public interface getTaskStatus {
        void onResponse(Status taskStatus);
    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8000/") // Ensure it's http if you are not using HTTPS
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ApiService service = retrofit.create(ApiService.class);

    public void getUser(String User_name, Context context, final UserCallback callback) {
        Call<User> call = service.getUser(User_name);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    User user = response.body();
                    Log.d("API Response", "User Name: " + user.getUser_name());
                    Log.d("API Response", "Karma: " + user.getKarma());
                    callback.onResponse(user);
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

    public void getStatus(String User_name, Context context, final getTaskStatus callback){
        Call<Status> call = service.getStatus(User_name);
        call.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                if(response.isSuccessful() && response.body() != null){
                    Status postedStatus = response.body();
                    callback.onResponse(postedStatus);
                }
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {

            }
        });

    }

    public void loginUser(String User_name, String Password, final LoginCallback callback) {
        UserOperation userOperation = new UserOperation(User_name, Password);
        Call<LoginResponse> call = service.loginUser(userOperation);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse loginResponse = response.body();
                    callback.onResponse(loginResponse);
                } else {
                    Log.e("API Response", "Response not successful or body is null");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("API Response", "Error: " + t.getMessage());
            }
        });
    }
    public void signupUser(String User_name, String Password, final SignupCallback callback) {
        UserOperation userOperation = new UserOperation(User_name, Password);
        Call<User> call = service.signupUser(userOperation);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    User user = response.body();
                    callback.onResponse(user);
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
    public void postTask(String PostedBy, String Title, String Description,
    int Reward,final postTaskCallback callback){
        Task toPosttask = new Task(PostedBy,Title,Description,Reward);
        Call<Task> call = service.postTask(toPosttask);
        call.enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Task postedTask = response.body();
                    callback.onResponse(postedTask);
                } else {
                    Log.e("API Response", "Response not successful or body is null");
                }
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {
                Log.e("API Response", "Error: " + t.getMessage());
            }
        });
    }

    public interface getTasksCallback {
        void onResponse(List<Task_database> tasks);
    }
    public void getTasks(final getTasksCallback callback){
        Call<List<Task_database>> call = service.getTasks();
        call.enqueue(new Callback<List<Task_database>>() {
            @Override
            public void onResponse(Call<List<Task_database>> call, Response<List<Task_database>> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<Task_database> tasks = response.body();
                    callback.onResponse(tasks);
                }
            }

            @Override
            public void onFailure(Call<List<Task_database>> call, Throwable t) {

            }
        });
    }

}

