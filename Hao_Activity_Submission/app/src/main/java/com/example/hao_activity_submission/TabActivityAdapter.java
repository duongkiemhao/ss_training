package com.example.hao_activity_submission;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.hao_activity_submission.TabFragment.TabFragment1;
import com.example.hao_activity_submission.TabFragment.TabFragment2;
import com.example.hao_activity_submission.TabFragment.TabFragment3;

public class TabActivityAdapter extends FragmentStateAdapter {

    private String[] titles = new String[]{"First", "Second", "Third"};

    public TabActivityAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new TabFragment1();
            case 1:
                return new TabFragment2();
            case 2:
                return new TabFragment3();
        }
        return new TabFragment1();
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

}
