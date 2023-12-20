package com.example.app.ui.fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app.R;
import com.example.app.database.model.Task;
import com.example.app.databinding.FragmentTaskEditBinding;

public class EditTaskFragment extends Fragment {

    private Task task;
    private  String startDate, endDate;
    private FragmentTaskEditBinding binding;

    public EditTaskFragment(Task task, String startDate, String endDate) {
        this.task = task;
        this.endDate = endDate;
        this.startDate = startDate;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentTaskEditBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_task_edit, container, false);
        binding.setTask(task);
        binding.setStartDate(startDate);
        binding.setEndDate(endDate);
        return binding.getRoot();
    }

    public void updateDates(String newStartDate, String newEndDate) {
        // Update your UI components or perform any necessary actions with the new dates
        binding.setStartDate(newStartDate);
        binding.setEndDate(newEndDate);
    }
}