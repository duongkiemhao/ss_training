package com.example.hao_activity_3_fixed;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import com.example.hao_activity_3_fixed.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;


    private String[] titles = new String[]{"One", "Two", "Three"};
    private int[] myImageList = new int[]{R.drawable.change_icon_fragment_1, R.drawable.change_icon_fragment_2, R.drawable.change_icon_fragment_3};
    private int[] myImageList1 = new int[]{R.drawable.ic_baseline_1k_1, R.drawable.ic_baseline_4g_mobiledata_1, R.drawable.ic_baseline_access_time_1};
    private int[] myImageList2 = new int[]{R.drawable.ic_baseline_1k_2, R.drawable.ic_baseline_4g_mobiledata_2, R.drawable.ic_baseline_access_time_2};
    private int[] theInt = new int[]{1,2,3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.getTabAt(1).select();
        tabLayout.getTabAt(2).select();
        tabLayout.getTabAt(0).select();
        tabLayout.getTabAt(0).setIcon(myImageList1[0]);
        tabLayout.getTabAt(1).setIcon(myImageList2[1]);
        tabLayout.getTabAt(2).setIcon(myImageList2[2]);

       tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
           @Override
           public void onTabSelected(TabLayout.Tab tab) {
               int position = tab.getPosition();

                if(position == 0) {
                    tabLayout.getTabAt(0).setIcon(myImageList1[0]);
                    tabLayout.getTabAt(1).setIcon(myImageList2[1]);
                    tabLayout.getTabAt(2).setIcon(myImageList2[2]);
                }

                else if (position == 1){
                    tabLayout.getTabAt(1).setIcon(myImageList1[1]);
                    tabLayout.getTabAt(0).setIcon(myImageList2[0]);
                    tabLayout.getTabAt(2).setIcon(myImageList2[2]);
                }

                else if (position == 2) {
                    tabLayout.getTabAt(2).setIcon(myImageList1[2]);
                    tabLayout.getTabAt(0).setIcon(myImageList2[0]);
                    tabLayout.getTabAt(1).setIcon(myImageList2[1]);
                }


           }

           @Override
           public void onTabUnselected(TabLayout.Tab tab) {

           }

           @Override
           public void onTabReselected(TabLayout.Tab tab) {

           }
       });
    }

    private void init() {
        // removing toolbar elevation
        getSupportActionBar().setElevation(0);

        binding.viewPager.setAdapter(new MainActivityAdapter(this));

        // attaching tab mediator
        new TabLayoutMediator(binding.tabLayout, binding.viewPager,
                (tab, position) -> tab.setIcon(myImageList[position]).setText(titles[position])).attach();

    }
}