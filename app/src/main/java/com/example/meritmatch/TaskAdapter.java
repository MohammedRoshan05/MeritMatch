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

    private List<Task_database> tasks;
    private int selectedPosition = RecyclerView.NO_POSITION; // No position selected by default
    private OnItemClickListener onItemClickListener; // Listener for item clicks

    public TaskAdapter(List<Task_database> tasks, OnItemClickListener onItemClickListener) {
        this.tasks = tasks;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return tasks.size() + 1; // +1 for the header row
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
            Task_database task = tasks.get(position - 1);
            holder.tvPostedBy.setText(task.getPostedBy());
            holder.tvTitle.setText(task.getTitle());
            holder.tvDescription.setText(task.getDescription());
            holder.tvReward.setText(String.valueOf(task.getReward()));
            holder.tvStatus.setText(task.getStatus());
            holder.tvResolver.setText(task.getResolver());

            // Highlight the selected row
            holder.itemView.setBackgroundResource(position == selectedPosition ? R.color.colorRowPressed : R.color.colorRowDefault);

            // Set click listener for each row
            holder.itemView.setOnClickListener(v -> {
                int previousPosition = selectedPosition;
                selectedPosition = holder.getAdapterPosition();

                // Notify the adapter to refresh the rows
                notifyItemChanged(previousPosition);
                notifyItemChanged(selectedPosition);

                // Notify the listener
                if (onItemClickListener != null && position != 0) {
                    onItemClickListener.onItemClick(task); // Pass the clicked task
                }
            });
        }
    }

    public void updateTasks(List<Task_database> newTasks) {
        this.tasks = newTasks;
        notifyDataSetChanged(); // Notify the adapter to refresh the RecyclerView
    }

    // Interface to handle item clicks
    public interface OnItemClickListener {
        void onItemClick(Task_database task);
    }
}
