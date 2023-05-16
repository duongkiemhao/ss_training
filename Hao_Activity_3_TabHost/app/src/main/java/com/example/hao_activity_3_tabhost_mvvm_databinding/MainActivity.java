package com.example.hao_activity_3_tabhost_mvvm_databinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 mViewPager;
    private TabLayout mTabLayout;
    private MyViewPagerAdapter viewPagerAdapter;
    private int[] tabIcons = {
            R.drawable.ic_baseline_login_24,
            R.drawable.ic_baseline_brightness_1_24,
            R.drawable.ic_baseline_brightness_2_24
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_paging);

        mTabLayout.addTab(mTabLayout.newTab().setText("Login"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Fragment 1"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Fragment 2"));

        mTabLayout.getTabAt(0).setIcon(tabIcons[0]);
        mTabLayout.getTabAt(1).setIcon(tabIcons[1]);
        mTabLayout.getTabAt(2).setIcon(tabIcons[2]);

        viewPagerAdapter = new MyViewPagerAdapter(this);


        mViewPager.setAdapter(viewPagerAdapter);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mTabLayout.selectTab(mTabLayout.getTabAt(position));
            }
        });
    }
}