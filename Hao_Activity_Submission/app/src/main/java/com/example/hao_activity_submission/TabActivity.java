package com.example.hao_activity_submission;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.example.hao_activity_submission.databinding.ActivityTabAcitivityBinding;

public class TabActivity extends AppCompatActivity {

    private ActivityTabAcitivityBinding binding;
    TabLayout tabLayout;

    LinearLayout tab ;
    private String[] titles = new String[]{"One", "Two", "Three"};
    private int[] myImageList = new int[]{R.drawable.change_icon_fragment_1, R.drawable.change_icon_fragment_2, R.drawable.change_icon_fragment_3};
    private int[] myImageList1 = new int[]{R.drawable.ic_baseline_1k_1, R.drawable.ic_baseline_4g_mobiledata_1, R.drawable.ic_baseline_access_time_1};
    private int[] myImageList2 = new int[]{R.drawable.ic_baseline_1k_2, R.drawable.ic_baseline_4g_mobiledata_2, R.drawable.ic_baseline_access_time_2};
    private int[] myText = new int[]{R.drawable.change_color_text};
    private int[] theInt = new int[]{1,2,3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_acitivity);
        binding = ActivityTabAcitivityBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        Context mContext = null;

        tab = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);

        init3();

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        tabLayout.setSelectedTabIndicatorColor(R.drawable.change_color_tab);
        ACustomTab();

    }





    private void init3() {
        // removing toolbar elevation
        getSupportActionBar().setElevation(0);

        binding.viewPager.setAdapter(new TabActivityAdapter(this));
        binding.viewPager.isUserInputEnabled();

        // attaching tab mediator
        new TabLayoutMediator(binding.tabLayout, binding.viewPager,
                (tab, position) -> { tab.setCustomView(R.layout.custom_tab);}).attach();

    }



    @SuppressLint({"ResourceAsColor", "ResourceType"})
    private void ACustomTab(){
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            // inflate the Parent LinearLayout Container for the tab
            // from the layout nav_tab.xml file that we created 'R.layout.nav_tab
            LinearLayout tab = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);

            // get child TextView and ImageView from this layout for the icon and label
            TextView tab_label = (TextView) tab.findViewById(R.id.nav_label);
            ImageView tab_icon = (ImageView) tab.findViewById(R.id.nav_icon);

            // set the label text by getting the actual string value by its id
            // by getting the actual resource value `getResources().getString(string_id)`
            tab_label.setText(titles[i]);
            tab_label.setTextColor(getResources().getColorStateList(R.drawable.change_color_text));
            tab_icon.setImageResource(myImageList[i]);
            tabLayout.getTabAt(i).setCustomView(tab);


    }}




}