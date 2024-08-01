package com.example.meritmatch;

import java.io.Serializable;

public class ClassUser implements Serializable {
    public String User_name;
    public int Karma;

    public String getUser_name() {
        return User_name;
    }

    public void setUser_name(String user_name) {
        User_name = user_name;
    }

    public int getKarma() {
        return Karma;
    }

    public void setKarma(int karma) {
        Karma = karma;
    }
}
