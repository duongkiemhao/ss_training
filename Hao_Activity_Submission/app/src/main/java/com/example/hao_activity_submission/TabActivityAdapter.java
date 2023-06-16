package com.example.hao_activity_submission;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.hao_activity_submission.TabFragment.TabFragment1;
import com.example.hao_activity_submission.TabFragment.TabFragment2;
import com.example.hao_activity_submission.TabFragment.TabFragment3;

public class TabActivityAdapter extends FragmentStateAdapter {

    private String[] titles = new String[]{"First", "Second", "Third"};

    public TabActivityAdapter(@NonNull FragmentActivity fragmentActivity,@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager,lifecycle);
    }

    private Fragment mCurrentFragment;

    public Fragment getCurrentFragment() {
        return mCurrentFragment;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                mCurrentFragment = new TabFragment1();
                break;
            case 1:
                mCurrentFragment = new TabFragment2();
                break;
            case 2:
                mCurrentFragment = new TabFragment3();
                break;
        }
        return mCurrentFragment;
    }




    @Override
    public int getItemCount() {
        return titles.length;
    }

}
