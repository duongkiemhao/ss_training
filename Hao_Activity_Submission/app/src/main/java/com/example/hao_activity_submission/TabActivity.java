package com.example.hao_activity_submission;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayoutMediator;
import com.example.hao_activity_submission.databinding.ActivityTabAcitivityBinding;

public class TabActivity extends AppCompatActivity {

    private ActivityTabAcitivityBinding binding;


    private String[] titles = new String[]{"One", "Two", "Three"};
    private int[] myImageList = new int[]{R.drawable.change_icon_fragment_1, R.drawable.change_icon_fragment_2, R.drawable.change_icon_fragment_3};
    private int[] myImageList1 = new int[]{R.drawable.ic_baseline_1k_1, R.drawable.ic_baseline_4g_mobiledata_1, R.drawable.ic_baseline_access_time_1};
    private int[] myImageList2 = new int[]{R.drawable.ic_baseline_1k_2, R.drawable.ic_baseline_4g_mobiledata_2, R.drawable.ic_baseline_access_time_2};
    private int[] theInt = new int[]{1,2,3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_acitivity);
    }

    private void init() {
        // removing toolbar elevation
        getSupportActionBar().setElevation(0);

        binding.viewPager.setAdapter(new TabActivityAdapter(this));

        // attaching tab mediator
        new TabLayoutMediator(binding.tabLayout, binding.viewPager,
                (tab, position) -> tab.setIcon(myImageList[position]).setText(titles[position])).attach();

    }
}