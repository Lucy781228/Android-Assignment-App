package com.example.app.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app.utils.DateUtils;
import com.example.app.R;
import com.example.app.adapter.DialogTaskPagerAdapter;
import com.example.app.database.model.Task;
import com.google.android.material.tabs.TabLayout;

public class TaskFragment extends DialogFragment {

    public TaskFragment() {
        // Required empty public constructor
    }

    private Task task;
    private EditTaskFragment editTaskFragment;
    private CommentTaskFragment commentTaskFragment;
    private FileTaskFragment fileTaskFragment;
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
        editTaskFragment = new EditTaskFragment(task, DateUtils.formatDate(task.getTask_start()),
                DateUtils.formatDate(task.getTask_end()));
        commentTaskFragment = new CommentTaskFragment(null);
        fileTaskFragment = new FileTaskFragment();
        adapter.addFragment(editTaskFragment, "Edit");
        adapter.addFragment(commentTaskFragment, "Comment");
        adapter.addFragment(fileTaskFragment, "Upload");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    public void updateTaskFragmentEditDates(String newStartDate, String newEndDate) {
        if (editTaskFragment != null) {
            editTaskFragment.updateDates(newStartDate, newEndDate);
        }
    }
}