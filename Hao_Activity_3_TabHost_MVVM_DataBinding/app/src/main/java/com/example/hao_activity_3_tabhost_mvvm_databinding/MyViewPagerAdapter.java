package com.example.hao_activity_3_tabhost_mvvm_databinding;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyViewPagerAdapter extends FragmentStateAdapter {

    public MyViewPagerAdapter(@NonNull FragmentActivity fm){
        super(fm);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position){
        switch (position){
            case 0:
                return new Fragment_Login();

            case 1:
                return new Fragment_1();

            case 2:
                return new Fragment_2();

            default:
                return new Fragment_Login();
        }

    }



    @Override
    public int getItemCount() {
        return 3;
    }
}
