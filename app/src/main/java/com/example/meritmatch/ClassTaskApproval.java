package com.example.meritmatch;

public class ClassTaskApproval {
    String Resolver;
    int Points;

    public ClassTaskApproval(String resolver, int points) {
        Resolver = resolver;
        Points = points;
    }

    public String getResolver() {
        return Resolver;
    }

    public void setResolver(String resolver) {
        Resolver = resolver;
    }

    public int getPoints() {
        return Points;
    }

    public void setPoints(int points) {
        Points = points;
    }
}
