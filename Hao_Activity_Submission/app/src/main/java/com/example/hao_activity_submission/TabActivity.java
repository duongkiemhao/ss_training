package com.example.hao_activity_submission;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.example.hao_activity_submission.databinding.ActivityTabAcitivityBinding;

public class TabActivity extends AppCompatActivity {

    private ActivityTabAcitivityBinding binding;
    TabLayout tabLayout;
    View view;
    TextView theText;
    private String[] titles = new String[]{"One", "Two", "Three"};
    private int[] myImageList = new int[]{R.drawable.change_icon_fragment_1, R.drawable.change_icon_fragment_2, R.drawable.change_icon_fragment_3};
    private int[] myImageList1 = new int[]{R.drawable.ic_baseline_1k_1, R.drawable.ic_baseline_4g_mobiledata_1, R.drawable.ic_baseline_access_time_1};
    private int[] myImageList2 = new int[]{R.drawable.ic_baseline_1k_2, R.drawable.ic_baseline_4g_mobiledata_2, R.drawable.ic_baseline_access_time_2};
    private int[] theInt = new int[]{1,2,3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_acitivity);
        binding = ActivityTabAcitivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        createTabIcons2();
//        tabLayout.getTabAt(1).select();
//        tabLayout.getTabAt(2).select();
//        tabLayout.getTabAt(0).select();
//        tabLayout.getTabAt(0).setIcon(myImageList1[0]);
//        tabLayout.getTabAt(1).setIcon(myImageList2[1]);
//        tabLayout.getTabAt(2).setIcon(myImageList2[2]);

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

        binding.viewPager.setAdapter(new TabActivityAdapter(this));
        binding.viewPager.isUserInputEnabled();

        // attaching tab mediator
        new TabLayoutMediator(binding.tabLayout, binding.viewPager,
                (tab, position) -> tab.setIcon(myImageList[position]).setText(titles[position])).attach();

    }

    private void init2() {
        // removing toolbar elevation
        getSupportActionBar().setElevation(0);

        binding.viewPager.setAdapter(new TabActivityAdapter(this));
        binding.viewPager.isUserInputEnabled();

        // attaching tab mediator
        new TabLayoutMediator(binding.tabLayout, binding.viewPager,
                (tab, position) -> {tab.select();
            createTabIcons2();}).attach();

    }

    private void createTabIcons(int theA) {

        if(theA == 0){
            TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
            tabOne.setText("Tab 1");
            tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_baseline_1k_2, 0, 0);
            tabLayout.getTabAt(0).setCustomView(tabOne);
        } else if (theA == 1){
            TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
            tabTwo.setText("Tab 2");

            tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_baseline_4g_mobiledata_2, 0, 0);
            tabLayout.getTabAt(1).setCustomView(tabTwo);
        } else if (theA == 2){
            TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
            tabThree.setText("Tab 3");
            tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_baseline_access_time_2, 0, 0);
            tabLayout.getTabAt(2).setCustomView(tabThree);
        }






    }

    private void createTabIcons2() {


            TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
            tabOne.setText("Tab 1");
            tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_baseline_1k_2, 0, 0);
            tabLayout.getTabAt(0).setCustomView(tabOne);

            TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
            tabTwo.setText("Tab 2");
            tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_baseline_4g_mobiledata_2, 0, 0);
            tabLayout.getTabAt(1).setCustomView(tabTwo);

            TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
            tabThree.setText("Tab 3");
            tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_baseline_access_time_2, 0, 0);
            tabLayout.getTabAt(2).setCustomView(tabThree);







    }
//
//    private void setupTab() {
//        binding.viewPager.setAdapter(new TabActivityAdapter(this));
//        binding.viewPager.isUserInputEnabled();
//      //  binding.viewPager.offscreenPageLimit = getTabCount();
//        new TabLayoutMediator(
//                binding.tabLayout, binding.viewPager,
//          (tab, position) ->{
//                  view = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
//                theText = (TextView)view.findViewById(R.id.tab_custom_tab);
//                    theText.setText(titles[Integer.parseInt(position)]);
//
//        }.attach();
//    }

}