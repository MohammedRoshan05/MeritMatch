package com.example.meritmatch;

public class ClassTask_database {
    String PostedBy;
    String Title;
    String Description;
    int Reward;
    String Status;
    String Resolver;

    public ClassTask_database(String postedBy, String title, String description,
                              int reward, String status, String resolver) {
        PostedBy = postedBy;
        Title = title;
        Description = description;
        Reward = reward;
        Status = status;
        Resolver = resolver;
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

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getResolver() {
        return Resolver;
    }

    public void setResolver(String resolver) {
        Resolver = resolver;
    }
}
