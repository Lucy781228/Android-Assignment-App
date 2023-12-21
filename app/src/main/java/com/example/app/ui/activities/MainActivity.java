package com.example.app.ui.activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.app.databinding.ActivityMainBinding;
import com.example.app.utils.CustomMenuGroupList;
import com.example.app.utils.CustomMenuItem;
import com.example.app.R;
import com.example.app.adapter.CustomExpandableListAdapter;
import com.example.app.adapter.MyPagerAdapter;
import com.example.app.database.AndroidNCDatabase;
import com.example.app.database.dao.TaskDAO;
import com.example.app.database.dao.WorkItemDAO;
import com.example.app.database.model.Task;
import com.example.app.database.model.WorkItem;
import com.example.app.database.viewmodel.WorkItemViewModel;
import com.example.app.databinding.DialogAddTaskBinding;
import com.example.app.databinding.DialogAddWorkBinding;
import com.example.app.databinding.DialogWorkDetailsBinding;
import com.example.app.ui.fragments.TabFragment;
import com.example.app.ui.fragments.TaskFragment;
import com.google.android.material.tabs.TabLayout;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements CustomExpandableListAdapter.OnDetailDeleteClickListener {

    private CustomExpandableListAdapter expandableListAdapter;
    private MyPagerAdapter adapter;
    private Dialog dialog;
    private Calendar calendar;
    private final List<CustomMenuItem> menuItems = new ArrayList<>();
    private final List<CustomMenuItem> menuItemsTodo = new ArrayList<>();
    private final List<CustomMenuItem> menuItemsDoing = new ArrayList<>();
    private final List<CustomMenuItem> menuItemsDone = new ArrayList<>();
    private final List<CustomMenuItem> emptyMenuItems = new ArrayList<>();
    private final List<CustomMenuGroupList> menuGroupLists = new ArrayList<>();
    private final List<Task> taskList1 = new ArrayList<>();
    private final List<Task> taskList2 = new ArrayList<>();
    private final List<Task> taskList3 = new ArrayList<>();
    private int selectedWorkId = 0;
    private final TabFragment fragment1 = new TabFragment(taskList1, 1);
    private final TabFragment fragment2 = new TabFragment(taskList2, 2);
    private final TabFragment fragment3 = new TabFragment(taskList3, 3);
    private ViewPager viewPager;
    private String username;
    private WorkItemDAO workItemDAO;
    private TaskDAO taskDAO;
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        calendar = Calendar.getInstance();
        workItemDAO = AndroidNCDatabase.getDatabase(this).workItemDAO();
        taskDAO = AndroidNCDatabase.getDatabase(this).taskDAO();

        updateMenuGroupList();
        WorkItemViewModel workItemViewModel = new ViewModelProvider(this).get(WorkItemViewModel.class);

        workItemViewModel.getAllWorkItems().observe(this, workItems -> {
            menuItems.clear();
            menuItemsTodo.clear();
            menuItemsDoing.clear();
            menuItemsDone.clear();
            menuGroupLists.clear();
            for (WorkItem w : workItems) {
                if (w.getUser_create().equals(username)) {
                    menuItems.add(new CustomMenuItem(w.getWork_name(), w.getWork_id(), R.drawable.work_blue, w.getLevel_id()));
                    if (w.getStatus_id() == 0) {
                        menuItemsTodo.add(new CustomMenuItem(w.getWork_name(), w.getWork_id(), R.drawable.work_red, w.getLevel_id()));
                    } else if (w.getStatus_id() == 1) {
                        menuItemsDoing.add(new CustomMenuItem(w.getWork_name(), w.getWork_id(), R.drawable.work_orange, w.getLevel_id()));
                    } else {
                        menuItemsDone.add(new CustomMenuItem(w.getWork_name(), w.getWork_id(), R.drawable.work_blue, w.getLevel_id()));
                    }
                }
            }
            updateMenuGroupList();
            expandableListAdapter.setExpandableList(menuGroupLists, menuItems);
            getAllTask();
        });

        binding.etSearch.setOnClickListener(v -> binding.etSearch.setCursorVisible(true));

        // Create and set the custom adapter for the ListView
        expandableListAdapter = new CustomExpandableListAdapter(this, menuGroupLists, menuItems);
        expandableListAdapter.setOnDetailDeleteClickListener(this);
        binding.drawerListView.setAdapter(expandableListAdapter);
        binding.drawerListView.setOnGroupExpandListener(this::onNoChildGroup);

        binding.drawerListView.setOnGroupCollapseListener(this::onNoChildGroup);

        binding.drawerListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            selectedWorkId = expandableListAdapter.getChild(groupPosition, childPosition).getWorkId();
            expandableListAdapter.setSelectedChildPosition(childPosition);
            runOnUiThread(this::getAllTask);
            return false;
        });

        viewPager = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);

        // Initialize MyPagerAdapter
        adapter = new MyPagerAdapter(getSupportFragmentManager());
        // Add fragments to the adapter
        adapter.addFragment(fragment1, "Cần làm", "tab1");
        adapter.addFragment(fragment2, "Đang thực hiện", "tab2");
        adapter.addFragment(fragment3, "Hoàn thành", "tab3");
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        ImageView iconMenu = findViewById(R.id.iconMenu);

        iconMenu.setOnClickListener(v -> {
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                binding.drawerLayout.openDrawer(GravityCompat.START);
            }
        });


    }

    public void onNoChildGroup(int position) {
        if (position == 0) {
            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(loginIntent);
            finish();
        } else if (position == 1) {
            openAddWorkDialog();
        }
    }

    public void updateMenuGroupList() {
        menuGroupLists.add(new CustomMenuGroupList("Đăng xuất", R.drawable.logout_black, emptyMenuItems));
        menuGroupLists.add(new CustomMenuGroupList("Thêm công việc", R.drawable.add_black, emptyMenuItems));
        menuGroupLists.add(new CustomMenuGroupList("Cần làm", 0, menuItemsTodo));
        menuGroupLists.add(new CustomMenuGroupList("Đang thực hiện", 0, menuItemsDoing));
        menuGroupLists.add(new CustomMenuGroupList("Hoàn thành", 0, menuItemsDone));
        menuGroupLists.add(new CustomMenuGroupList("Thông tin", R.drawable.info_black, emptyMenuItems));
    }

    public void getAllTask() {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                taskList1.clear();
                taskList2.clear();
                taskList3.clear();
                List<Task> taskList = taskDAO.getAllTasks();
                for (Task w : taskList) {
                    if (w.getWork_id() == selectedWorkId) {
                        int statusId = w.getStatus_id();
                        if (statusId == 1) {
                            taskList1.add(w);
                        } else if (statusId == 2) {
                            taskList2.add(w);
                        } else {
                            taskList3.add(w);
                        }
                    }
                }
                runOnUiThread(() -> {
                    fragment1.setTaskList(taskList1);
                    fragment2.setTaskList(taskList2);
                    fragment3.setTaskList(taskList3);
                    viewPager.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                });
            } catch (Exception e) {
               Log.e("MainActivity", "Error: " + e.getMessage());
            }
        });
    }

    public void onButtonAddNewTaskClicked(View view) {
        openAddTaskDialog();
    }

    private void openAddTaskDialog() {
        // Inflate the dialog layout using DataBinding
        DialogAddTaskBinding dialogBinding = DataBindingUtil.inflate(
                LayoutInflater.from(this), R.layout.dialog_add_task, null, false);

        // Create a new Task object
        LocalDate localDate = LocalDate.now();
        Date today = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Task newTask = new Task("", "", 1, selectedWorkId, 0, today, today,
                username, "", "");
        dialogBinding.setTask(newTask);
        dialogBinding.setActivity(this);

        // Create and show the dialog
        dialog = new Dialog(this);
        dialog.setContentView(dialogBinding.getRoot());
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(layoutParams);
        dialog.show();
    }

    public void onCancelTask() {
        dialog.dismiss();
    }

    public void onSaveTask(Task task) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                taskDAO.insertTask(task);
                getAllTask();
                runOnUiThread(() -> {
                    Toast.makeText(this, "Tạo thành công!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                });
            } catch (Exception e) {
                Log.e("MainActivity", "Error: " + e.getMessage());
            }
        });
    }

    public void onDeleteTask(Task task) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("CHÚ Ý");
        builder.setMessage("Bạn chắc chắn muốn xóa tác vụ này?");
        builder.setPositiveButton("Xóa", (dialog, which) -> {
            // User clicked OK, delete the item
            Executor executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> {
                try {
                    taskDAO.deleteTask(task);
//                        getAllTask();
                    Toast.makeText(MainActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Log.e("MainActivity", "Error: " + e.getMessage());
                }
            });
        });

        builder.setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void openAddWorkDialog() {
        // Inflate the dialog layout using DataBinding
        DialogAddWorkBinding dialogBinding = DataBindingUtil.inflate(
                LayoutInflater.from(this), R.layout.dialog_add_work, null, false);

        // Create a new WorkItem object
        int levelId = 2;
        WorkItem newWorkItem = new WorkItem("", "", levelId, 0, username);
        dialogBinding.setWorkItem(newWorkItem);
        dialogBinding.setActivity(this);

        // Create and show the dialog
        dialog = new Dialog(this);
        dialog.setContentView(dialogBinding.getRoot());
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT; // Adjust width as needed
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT; // Adjust height as needed
        dialog.getWindow().setAttributes(layoutParams);
        dialog.show();
    }

    public void onCancelWork() {
        dialog.dismiss();
    }

    public void onSaveWork(WorkItem workItem) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                workItemDAO.insertWorkItem(workItem);
                runOnUiThread(() -> dialog.dismiss());
            } catch (Exception e) {
                Log.e("MainActivity", "Error: " + e.getMessage());
            }
        });
    }

    @Override
    public void onDetailsClicked(int workId) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                WorkItem workItem = workItemDAO.getWorkItemById(workId);
                if (workItem != null) {
                    runOnUiThread(() -> displayWorkDetailsDialog(workItem));
                } else {
                    runOnUiThread(() -> Toast.makeText(this, "Không tìm thấy công việc", Toast.LENGTH_SHORT).show());
                }
            } catch (Exception e) {
                Log.e("MainActivity", "Error: " + e.getMessage());
            }
        });
    }

    private void displayWorkDetailsDialog(WorkItem workItem) {
        // Inflate the dialog layout using DataBinding
        DialogWorkDetailsBinding dialogBinding = DataBindingUtil.inflate(
                LayoutInflater.from(this), R.layout.dialog_work_details, null, false);

        // Set the WorkItem to the binding variable
        dialogBinding.setWorkItem(workItem);

        // Create and show the dialog
        dialog = new Dialog(this);
        dialog.setContentView(dialogBinding.getRoot());
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT; // Adjust width as needed
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT; // Adjust height as needed
        dialog.getWindow().setAttributes(layoutParams);
        dialog.show();
    }

    @Override
    public void onDeleteClicked(int workId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("CHÚ Ý");
        builder.setMessage("Bạn chắc chắn muốn xóa công việc này?");
        builder.setPositiveButton("Xóa", (dialog, which) -> {
            Executor executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> {
                try {
                    WorkItem workItem = workItemDAO.getWorkItemById(workId);
                    workItemDAO.deleteWorkItem(workItem);
                    taskDAO.deleteTaskByWordId(workId);
                    runOnUiThread(() -> Toast.makeText(MainActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show());
                } catch (Exception e) {
                    Log.e("MainActivity", "Error: " + e.getMessage());
                }
            });
        });

        builder.setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            binding.etSearch.setCursorVisible(false);
        }
        return super.dispatchTouchEvent(ev);
    }

    public void showTaskDetailsDialog(Task task) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        TaskFragment taskFragment = new TaskFragment(task);

        taskFragment.show(fragmentManager, "task_dialog");
    }

    public void onButtonUpdateTaskClicked() {

    }

    public void showStartDatePicker(View view) {
        showDatePicker();
    }

    public void showEndDatePicker(View view) {
        showDatePicker();
    }

    private void showDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, monthOfYear, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        };

        // Create a DatePickerDialog with the current date as default
        new DatePickerDialog(
                this,
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        ).show();
    }

}

