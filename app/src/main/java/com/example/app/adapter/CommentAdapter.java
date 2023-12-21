package com.example.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.R;
import com.example.app.database.model.Comment;
import com.example.app.databinding.ListItemCommentBinding;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private final List<Comment> comments;
    private final LayoutInflater inflater;

    public CommentAdapter(Context context, List<Comment> comments) {
        this.inflater = LayoutInflater.from(context);
        this.comments = comments;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        ListItemCommentBinding binding = DataBindingUtil.inflate(inflater, R.layout.list_item_comment, parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Comment comment = comments.get(position);
        holder.binding.setComment(comment);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ListItemCommentBinding binding;

        ViewHolder(View view) {
            super(view);
            binding = DataBindingUtil.bind(view);
        }
    }
}
