package com.example.meritmatch;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class TaskViewHolder extends RecyclerView.ViewHolder {
    TextView tvPostedBy, tvTitle, tvDescription, tvReward, tvStatus, tvResolver;

    public TaskViewHolder(View itemView) {
        super(itemView);
        tvPostedBy = itemView.findViewById(R.id.tvPostedBy);
        tvTitle = itemView.findViewById(R.id.tvTitle);
        tvDescription = itemView.findViewById(R.id.tvDescription);
        tvReward = itemView.findViewById(R.id.tvReward);
        tvStatus = itemView.findViewById(R.id.tvStatus);
        tvResolver = itemView.findViewById(R.id.tvResolver);
    }
}
