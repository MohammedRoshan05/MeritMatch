package com.example.meritmatch;

public class ClassReserveOperation {
    String PostedBy;
    String Reserver;

    public ClassReserveOperation(String postedBy, String reserver) {
        PostedBy = postedBy;
        Reserver = reserver;
    }

    public String getPostedBy() {
        return PostedBy;
    }

    public void setPostedBy(String postedBy) {
        PostedBy = postedBy;
    }

    public String getReserver() {
        return Reserver;
    }

    public void setReserver(String reserver) {
        Reserver = reserver;
    }
}
