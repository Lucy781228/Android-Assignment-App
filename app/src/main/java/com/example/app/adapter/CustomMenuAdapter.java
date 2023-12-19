package com.example.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.app.CustomMenuItem;
import com.example.app.R;

import java.util.ArrayList;
import java.util.List;

public class CustomMenuAdapter extends ArrayAdapter<CustomMenuItem> {

    public interface OnMenuItemClickListener {
        void onDetailsClicked(int workId);
        void onDeleteClicked(int workId);
    }

    private OnMenuItemClickListener listener;
    private int selectedItem = -1;
    public CustomMenuAdapter(Context context, List<CustomMenuItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_menu_item, parent, false);
        }

        CustomMenuItem item = getItem(position);

        ImageView iconImageView = convertView.findViewById(R.id.menu_item_icon);
        TextView nameTextView = convertView.findViewById(R.id.menu_item_name);
        ImageView detailIcon = convertView.findViewById(R.id.detail_icon);

        if (position == selectedItem) {
            convertView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.gray));
            nameTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
        } else {
            convertView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.primary));
            nameTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        }


        iconImageView.setImageResource(item.getIconResourceId());
        nameTextView.setText(item.getName());
        if (item.shouldShowDetailIcon()) {
            detailIcon.setVisibility(View.VISIBLE);
            // Add a click listener to the detail_icon
            detailIcon.setOnClickListener(v -> {
                // Show the submenu here
                PopupMenu popupMenu = new PopupMenu(getContext(), v);
                MenuInflater inflater = popupMenu.getMenuInflater();
                inflater.inflate(R.menu.task_menu, popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(menuItem -> {
                    if (menuItem.getItemId() == R.id.details) {
                        if (listener != null) {
                            listener.onDetailsClicked(item.getWorkId());
                        }
                        return true;
                    } else if (menuItem.getItemId() == R.id.delete) {
                        if (listener != null) {
                            listener.onDeleteClicked(item.getWorkId());
                        }
                        return true;
                    }
                    return false;
                });
            });
        } else {
            detailIcon.setVisibility(View.GONE);
        }
        return convertView;
    }
    public void setOnMenuItemClickListener(OnMenuItemClickListener listener) {
        this.listener = listener;
    }

    public void setSelectedItem(int position) {
        selectedItem = position;
        notifyDataSetChanged();
    }
}
