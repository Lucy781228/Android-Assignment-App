package com.example.app.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app.R;
import com.example.app.utils.VerticalSpaceItemDecoration;
import com.example.app.adapter.TaskListAdapter;
import com.example.app.database.model.Task;

import java.util.List;

public class TabFragment extends Fragment{


    public interface OnDeleteTaskListener {
        void onDeleteTask(Task task);
    }

    private OnDeleteTaskListener deleteTaskListener;

    private RecyclerView recyclerView;
    private int statusId;
    private List<Task> taskList;
    private TaskListAdapter adapter;

    public TabFragment(List<Task> taskList, int statusId) {
        this.taskList = taskList;
        this.statusId = statusId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab, container, false);
        recyclerView = rootView.findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(50));
        adapter = new TaskListAdapter(getActivity(), taskList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        return rootView;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void setDeleteTaskListener(OnDeleteTaskListener listener) {
        this.deleteTaskListener = listener;
    }
}
