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
        void onResponse(ClassUser classUser);
    }
    public interface LoginCallback {
        void onResponse(ClassLoginResponse response);
    }
    public interface SignupCallback {
        void onResponse(ClassUser classUser);
    }
    public interface postTaskCallback {
        void onResponse(ClassTask classTask);
    }
    public interface getTaskStatusCallback {
        void onResponse(ClassStatus taskClassStatus);
    }

    public interface TaskApprovalCallback {
        void onResponse(ClassTaskApproval approval);
    }
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ApiService service = retrofit.create(ApiService.class);

    public void getUser(String User_name, Context context, final UserCallback callback) {
        Call<ClassUser> call = service.getUser(User_name);
        call.enqueue(new Callback<ClassUser>() {
            @Override
            public void onResponse(Call<ClassUser> call, Response<ClassUser> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ClassUser classUser = response.body();
                    Log.d("API Response", "User Name: " + classUser.getUser_name());
                    Log.d("API Response", "Karma: " + classUser.getKarma());
                    callback.onResponse(classUser);
                } else {
                    Log.e("API Response", "Response not successful or body is null");
                }
            }

            @Override
            public void onFailure(Call<ClassUser> call, Throwable t) {
                Log.e("API Response", "Error: " + t.getMessage());
            }
        });
    }

    public void getStatus(String User_name, Context context, final getTaskStatusCallback callback){
        Call<ClassStatus> call = service.getStatus(User_name);
        call.enqueue(new Callback<ClassStatus>() {
            @Override
            public void onResponse(Call<ClassStatus> call, Response<ClassStatus> response) {
                if(response.isSuccessful() && response.body() != null){
                    ClassStatus postedClassStatus = response.body();
                    callback.onResponse(postedClassStatus);
                }
            }

            @Override
            public void onFailure(Call<ClassStatus> call, Throwable t) {

            }
        });
    }

    public void reserve(String PostedBy,String Reserver,Context context,final getTaskStatusCallback callback){
        ClassReserveOperation classReserveOperation = new ClassReserveOperation(PostedBy,Reserver);
        Call<ClassStatus> call = service.reserve(classReserveOperation);
        call.enqueue(new Callback<ClassStatus>() {
            @Override
            public void onResponse(Call<ClassStatus> call, Response<ClassStatus> response) {
                if(response.isSuccessful() && response.body() != null){
                    ClassStatus reservedClassStatus = response.body();
                    callback.onResponse(reservedClassStatus);
                }
            }

            @Override
            public void onFailure(Call<ClassStatus> call, Throwable t) {

            }
        });
    }

    public void loginUser(String User_name, String Password, final LoginCallback callback) {
        ClassUserOperation classUserOperation = new ClassUserOperation(User_name, Password);
        Call<ClassLoginResponse> call = service.loginUser(classUserOperation);
        call.enqueue(new Callback<ClassLoginResponse>() {
            @Override
            public void onResponse(Call<ClassLoginResponse> call, Response<ClassLoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ClassLoginResponse classLoginResponse = response.body();
                    callback.onResponse(classLoginResponse);
                } else {
                    Log.e("API Response", "Response not successful or body is null");
                }
            }

            @Override
            public void onFailure(Call<ClassLoginResponse> call, Throwable t) {
                Log.e("API Response", "Error: " + t.getMessage());
            }
        });
    }
    public void signupUser(String User_name, String Password, final SignupCallback callback) {
        ClassUserOperation classUserOperation = new ClassUserOperation(User_name, Password);
        Call<ClassUser> call = service.signupUser(classUserOperation);
        call.enqueue(new Callback<ClassUser>() {
            @Override
            public void onResponse(Call<ClassUser> call, Response<ClassUser> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ClassUser classUser = response.body();
                    callback.onResponse(classUser);
                } else {
                    Log.e("API Response", "Response not successful or body is null");
                }
            }

            @Override
            public void onFailure(Call<ClassUser> call, Throwable t) {
                Log.e("API Response", "Error: " + t.getMessage());
            }
        });
    }
    public void postTask(String PostedBy, String Title, String Description,
    int Reward,final postTaskCallback callback){
        ClassTask toPosttask = new ClassTask(PostedBy,Title,Description,Reward);
        Call<ClassTask> call = service.postTask(toPosttask);
        call.enqueue(new Callback<ClassTask>() {
            @Override
            public void onResponse(Call<ClassTask> call, Response<ClassTask> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ClassTask postedClassTask = response.body();
                    callback.onResponse(postedClassTask);
                } else {
                    Log.e("API Response", "Response not successful or body is null");
                }
            }

            @Override
            public void onFailure(Call<ClassTask> call, Throwable t) {
                Log.e("API Response", "Error: " + t.getMessage());
            }
        });
    }

    public interface getTasksCallback {
        void onResponse(List<ClassTask_database> tasks);
    }
    public void getTasks(final getTasksCallback callback){
        Call<List<ClassTask_database>> call = service.getTasks();
        call.enqueue(new Callback<List<ClassTask_database>>() {
            @Override
            public void onResponse(Call<List<ClassTask_database>> call, Response<List<ClassTask_database>> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<ClassTask_database> tasks = response.body();
                    callback.onResponse(tasks);
                }
            }

            @Override
            public void onFailure(Call<List<ClassTask_database>> call, Throwable t) {

            }
        });
    }

    public void updateKarma(String PostedBy,String Reserver,Context context,
                            int Karma,final getTaskStatusCallback callback){
        ClassReserveOperation classReserveOperation = new ClassReserveOperation(PostedBy,Reserver);
        Call<ClassStatus> call = service.updateKarma(classReserveOperation,Karma);
        call.enqueue(new Callback<ClassStatus>() {
            @Override
            public void onResponse(Call<ClassStatus> call, Response<ClassStatus> response) {
                if(response.isSuccessful() && response.body() != null){
                    ClassStatus updationofKarma = response.body();
                    callback.onResponse(updationofKarma);
                }
            }

            @Override
            public void onFailure(Call<ClassStatus> call, Throwable t) {

            }
        });

    }
    public void getTask(String PostedBy,Context context,final TaskApprovalCallback callback){
        Call<ClassTaskApproval> call = service.getTask(PostedBy);
        call.enqueue(new Callback<ClassTaskApproval>() {
            @Override
            public void onResponse(Call<ClassTaskApproval> call, Response<ClassTaskApproval> response) {
                ClassTaskApproval approval = response.body();
                callback.onResponse(approval);
            }

            @Override
            public void onFailure(Call<ClassTaskApproval> call, Throwable t) {

            }
        });
    }

}

