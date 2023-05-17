package com.example.hao_activity_3_fixed;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.hao_activity_3_fixed.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;


    private String[] titles = new String[]{"One", "Two", "Three"};
    private int[] myImageList = new int[]{R.drawable.change_icon_fragment_1, R.drawable.change_icon_fragment_2, R.drawable.change_icon_fragment_3};
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