package com.example.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.app.R;
import com.example.app.ui.activities.MainActivity;
import com.example.app.database.model.Task;
import com.example.app.databinding.ListItemTaskBinding;

import java.util.List;


public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {

    private List<Task> taskList;
    private LayoutInflater layoutInflater;

    public TaskListAdapter(Context context, List<Task> taskList) {
        this.taskList = taskList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemTaskBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item_task, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task currentTask = taskList.get(position);
        int color = ColorGenerator.MATERIAL.getColor(currentTask.getTask_name());

        // Build the TextDrawable
        TextDrawable drawable;
        if (currentTask.getUser_respond().isEmpty()) {
            drawable = TextDrawable.builder()
                    .buildRound("X", color);
        } else {
            drawable = TextDrawable.builder()
                    .buildRound(currentTask.getUser_respond().substring(0, 1).toUpperCase(), color);
        }

        holder.bind(currentTask, drawable);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ListItemTaskBinding binding;
        private ImageView avatar;

        public ViewHolder(ListItemTaskBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.avatar = binding.avatar;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Task currentTask = taskList.get(getAdapterPosition());
                    ((MainActivity) v.getContext()).showTaskDetailsDialog(currentTask);
                }
            });

        }

        public void bind(Task task, TextDrawable drawable) {
            binding.setTask(task);
            avatar.setImageDrawable(drawable);  // Set the TextDrawable to the ImageView
            binding.executePendingBindings();
        }

    }
}
