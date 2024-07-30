package com.example.meritmatch;

public class UserOperation {

    public String User_name;
    public String Password;

    public UserOperation(String user_name, String password) {
        User_name = user_name;
        Password = password;
    }
    public String getUser_name() {
        return User_name;
    }

    public void setUser_name(String user_name) {
        User_name = user_name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }


}
