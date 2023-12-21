package com.example.app.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app.R;
import com.example.app.adapter.CommentAdapter;
import com.example.app.database.model.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentTaskFragment extends Fragment {

    private List<Comment> commentList;
    private CommentAdapter commentAdapter;

    public CommentTaskFragment(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_comment, container, false);
        commentList = new ArrayList<>();
        commentList.add(new Comment(1, 1,"abc", "First Message"));
        commentList.add(new Comment(2, 1, "nva", "Second Message"));

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        commentAdapter = new CommentAdapter(getContext(), commentList);
        recyclerView.setAdapter(commentAdapter);
        return view;
    }
}
