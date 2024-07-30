package com.example.meritmatch;

import android.content.Intent;

public class Task {
    String PostedBy;
    String Title;
    String Description;
    int Reward;

    public Task(String postedBy, String title, String description, int reward) {
        PostedBy = postedBy;
        Title = title;
        Description = description;
        Reward = reward;
    }

    public String getPostedBy() {
        return PostedBy;
    }

    public void setPostedBy(String postedBy) {
        PostedBy = postedBy;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getReward() {
        return Reward;
    }

    public void setReward(int reward) {
        Reward = reward;
    }
}
