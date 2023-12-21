package com.example.app.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.app.utils.CustomMenuGroupList;
import com.example.app.utils.CustomMenuItem;
import com.example.app.R;

import java.util.List;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private final Context context;
    private List<CustomMenuGroupList> expandableListTitle;
    private List<CustomMenuItem> expandableListDetail;
    private OnDetailDeleteClickListener listener;
    private int selectedChildPosition = -1;

    public interface OnDetailDeleteClickListener {
        void onDetailsClicked(int workId);
        void onDeleteClicked(int workId);
    }


    public CustomExpandableListAdapter(Context context, List<CustomMenuGroupList> expandableListTitle,
                                       List<CustomMenuItem> expandableListDetail) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
    }

    @Override
    public CustomMenuItem getChild(int listPosition, int expandedListPosition) {
        CustomMenuGroupList group = expandableListTitle.get(listPosition);
        List<CustomMenuItem> children = group.getChildren();

        if (expandedListPosition >= 0 && expandedListPosition < children.size()) {
            return children.get(expandedListPosition);
        } else {
            return null;
        }
    }


    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final CustomMenuItem expandedListItem = getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.custom_menu_item, null);
        }
        TextView expandedListTextView = convertView.findViewById(R.id.menu_item_name);
        expandedListTextView.setText(expandedListItem.getName());
        ImageView iconImageView = convertView.findViewById(R.id.menu_item_icon);
        iconImageView.setImageResource(expandedListItem.getIconResourceId());
        ImageView detailIcon = convertView.findViewById(R.id.detail_icon);
        detailIcon.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(context, v);
            MenuInflater inflater = popupMenu.getMenuInflater();
            inflater.inflate(R.menu.task_menu, popupMenu.getMenu());
            popupMenu.show();
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                if (menuItem.getItemId() == R.id.details) {
                    if (listener != null) {
                        listener.onDetailsClicked(expandedListItem.getWorkId());
                    }
                    return true;
                } else if (menuItem.getItemId() == R.id.delete) {
                    if (listener != null) {
                        listener.onDeleteClicked(expandedListItem.getWorkId());
                    }
                    return true;
                }
                return false;
            });
        });
        convertView.setSelected(expandedListPosition == selectedChildPosition);
        if (expandedListPosition == selectedChildPosition) {
            convertView.setBackgroundColor(ContextCompat.getColor(context, R.color.gray));
        } else {
            convertView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));
        }
        return convertView;
    }

    public void setSelectedChildPosition(int position) {
        selectedChildPosition = position;
        notifyDataSetChanged();
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return expandableListTitle.get(listPosition).getChildren().size();
    }

    @Override
    public CustomMenuGroupList getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        final CustomMenuGroupList listTitle = getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.custom_menu_group_list, null);
        }
        TextView listTitleTextView = convertView.findViewById(R.id.listTitle);
        ImageView iconImageView = convertView.findViewById(R.id.menu_item_icon);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle.getName());
        if(listPosition == 0 || listPosition == 1 || listPosition == 5) {
            iconImageView.setImageResource(listTitle.getIconResourceId());
        } else {
            iconImageView.setImageResource(isExpanded ? R.drawable.expand : R.drawable.collapse);
        }

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }

    public void setExpandableList(List<CustomMenuGroupList> expandableListTitle, List<CustomMenuItem> expandableListDetail) {
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
        notifyDataSetChanged();
    }

    public void setOnDetailDeleteClickListener(OnDetailDeleteClickListener listener) {
        this.listener = listener;
    }
}
