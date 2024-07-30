package com.example.meritmatch;

public class ReserveOperation {
    String PostedBy;
    String Reserver;

    public ReserveOperation(String postedBy, String reserver) {
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
