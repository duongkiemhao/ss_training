package com.example.hao_activity_3_fixed;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


public class MainActivityAdapter extends FragmentStateAdapter {

    private String[] titles = new String[]{"First", "Second", "Third"};

    public MainActivityAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new FragmentFirst();
            case 1:
                return new FragmentSecond();
            case 2:
                return new FragmentThird();
        }
        return new FragmentFirst();
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}

