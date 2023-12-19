package com.example.app.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> titleList = new ArrayList<>();
    private final Map<Integer, String> fragmentTags = new HashMap<>();

    private FragmentManager fragmentManager;

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }
    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public MyPagerAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    public void addFragment(Fragment fragment, String title, String tag) {
        fragmentList.add(fragment);
        titleList.add(title);
        fragmentTags.put(fragmentList.size() - 1, tag);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Use the position as a title or customize as needed
        return titleList.get(position);
    }

    public String getFragmentTag(int position) {
        return fragmentTags.get(position);
    }

    public int getPositionByFragmentTag(String tag) {
        for (Map.Entry<Integer, String> entry : fragmentTags.entrySet()) {
            if (entry.getValue().equals(tag)) {
                return entry.getKey();
            }
        }
        return -1; // Tag not found
    }
}
