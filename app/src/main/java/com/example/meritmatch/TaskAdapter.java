package com.example.meritmatch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private List<ClassTask_database> tasks;
    private int selectedPosition = RecyclerView.NO_POSITION;
    private OnItemClickListener onItemClickListener;

    public TaskAdapter(List<ClassTask_database> tasks, OnItemClickListener onItemClickListener) {
        this.tasks = tasks;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return tasks.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? TYPE_HEADER : TYPE_ITEM;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_layout, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        if (position == 0) {
            holder.tvPostedBy.setText("PostedBy");
            holder.tvTitle.setText("Title");
            holder.tvDescription.setText("Description");
            holder.tvReward.setText("Reward");
            holder.tvStatus.setText("Status");
            holder.tvResolver.setText("Resolver");
        } else {
            ClassTask_database task = tasks.get(position - 1);
            holder.tvPostedBy.setText(task.getPostedBy());
            holder.tvTitle.setText(task.getTitle());
            holder.tvDescription.setText(task.getDescription());
            holder.tvReward.setText(String.valueOf(task.getReward()));
            holder.tvStatus.setText(task.getStatus());
            holder.tvResolver.setText(task.getResolver());


            holder.itemView.setBackgroundResource(position == selectedPosition ? R.color.colorRowPressed : R.color.colorRowDefault);


            holder.itemView.setOnClickListener(v -> {
                int previousPosition = selectedPosition;
                selectedPosition = holder.getAdapterPosition();


                notifyItemChanged(previousPosition);
                notifyItemChanged(selectedPosition);


                if (onItemClickListener != null && position != 0) {
                    onItemClickListener.onItemClick(task);
                }
            });
        }
    }

    public void updateTasks(List<ClassTask_database> newTasks) {
        this.tasks = newTasks;
        notifyDataSetChanged();
    }


    public interface OnItemClickListener {
        void onItemClick(ClassTask_database task);
    }
}
