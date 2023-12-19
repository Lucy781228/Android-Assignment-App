package com.example.app;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app.adapter.DialogTaskPagerAdapter;
import com.example.app.database.model.Task;
import com.google.android.material.tabs.TabLayout;

import java.util.Date;

public class TaskFragment extends DialogFragment {

    public TaskFragment() {
        // Required empty public constructor
    }

    private Task task;
    private TaskFragmentEdit taskFragmentEdit;
    private TaskFragmentComment taskFragmentComment;
    private TaskFragmentFile taskFragmentFile;
    // Create a public constructor with arguments
    public TaskFragment(Task task) {
        this.task = task;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task, container, false);

        ViewPager viewPager = view.findViewById(R.id.dialogViewPager);
        TabLayout tabLayout = view.findViewById(R.id.dialogTabLayout);

        DialogTaskPagerAdapter adapter = new DialogTaskPagerAdapter(getChildFragmentManager());
        taskFragmentEdit = new TaskFragmentEdit(task, DateUtils.formatDate(task.getTask_start()),
                DateUtils.formatDate(task.getTask_end()));
        taskFragmentComment = new TaskFragmentComment(null);
        taskFragmentFile = new TaskFragmentFile();
        adapter.addFragment(taskFragmentEdit, "Edit");
        adapter.addFragment(taskFragmentComment, "Comment");
        adapter.addFragment(taskFragmentFile, "Upload");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    public void updateTaskFragmentEditDates(String newStartDate, String newEndDate) {
        if (taskFragmentEdit != null) {
            taskFragmentEdit.updateDates(newStartDate, newEndDate);
        }
    }
}