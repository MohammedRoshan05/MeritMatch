package com.example.meritmatch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
    public class GridAdapter extends BaseAdapter {
        private Context context;
        private List<Task_database> tasks;
        private static final int TYPE_HEADER = 0;
        private static final int TYPE_ITEM = 1;

        public GridAdapter(Context context, List<Task_database> tasks) {
            this.context = context;
            this.tasks = tasks;
        }

        @Override
        public int getCount() {
            return tasks.size() + 1; // +1 for the header row
        }

        @Override
        public Object getItem(int position) {
            return position == 0 ? null : tasks.get(position - 1); // Header has no data item
        }

        @Override
        public long getItemId(int position) {
            return position - 1;
        }

        @Override
        public int getItemViewType(int position) {
            return position == 0 ? TYPE_HEADER : TYPE_ITEM;
        }

        @Override
        public int getViewTypeCount() {
            return 2; // Header and Item
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            int type = getItemViewType(position);
            if (type == TYPE_HEADER) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
                }
                ((TextView) convertView.findViewById(R.id.tvPostedBy)).setText("PostedBy");
                ((TextView) convertView.findViewById(R.id.tvTitle)).setText("Title");
                ((TextView) convertView.findViewById(R.id.tvDescription)).setText("Description");
                ((TextView) convertView.findViewById(R.id.tvReward)).setText("Reward");
                ((TextView) convertView.findViewById(R.id.tvStatus)).setText("Status");
                ((TextView) convertView.findViewById(R.id.tvResolver)).setText("Resolver");
            } else {
                Task_database task = tasks.get(position - 1);
                if (convertView == null) {
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
                }
                ((TextView) convertView.findViewById(R.id.tvPostedBy)).setText(task.getPostedBy());
                ((TextView) convertView.findViewById(R.id.tvTitle)).setText(task.getTitle());
                ((TextView) convertView.findViewById(R.id.tvDescription)).setText(task.getDescription());
                ((TextView) convertView.findViewById(R.id.tvReward)).setText(String.valueOf(task.getReward()));
                ((TextView) convertView.findViewById(R.id.tvStatus)).setText(task.getStatus());
                ((TextView) convertView.findViewById(R.id.tvResolver)).setText(task.getResolver());
            }
            return convertView;
        }
        public void updateTasks(List<Task_database> newTasks) {
            this.tasks = newTasks;
            notifyDataSetChanged(); // Notify the adapter to refresh the GridView
        }
    }
