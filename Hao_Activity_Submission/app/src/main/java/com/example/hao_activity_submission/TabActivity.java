package com.example.hao_activity_submission;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hao_activity_submission.TabFragment.TabFragment1;
import com.example.hao_activity_submission.TabFragment.TabFragment2;
import com.example.hao_activity_submission.TabFragment.TabFragment3;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.example.hao_activity_submission.databinding.ActivityTabAcitivityBinding;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import java.util.Objects;

public class TabActivity extends AppCompatActivity {

    private ActivityTabAcitivityBinding binding;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    LinearLayout tab ;
    private String[] TabText = new String[]{"One", "Two", "Three"};
    private int[] ChangeTabIconFragment = new int[]{R.drawable.change_tab_icon_fragment_1, R.drawable.change_tab_icon_fragment_2, R.drawable.change_tab_icon_fragment_3};
    RefreshInterface refreshInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_tab_acitivity);
        binding = ActivityTabAcitivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Context mContext = null;
        tab = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        init();
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setSelectedTabIndicatorColor(R.drawable.change_color_tab);
        ACustomTab();

//        Fragment page = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.viewPager + ":" + binding.viewPager.getCurrentItem());
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab1) {
                switch (tab1.getPosition()){
                        case 0:
                            if(getSupportFragmentManager().findFragmentByTag("f" + tab1.getPosition()) instanceof RefreshInterface)
                                ((RefreshInterface)getSupportFragmentManager().findFragmentByTag("f" + tab1.getPosition())).refresh_fragment();
                            break;
                        case 1:
                            TabFragment2 twoFragment = new TabFragment2();
                            break;
                        case 2:
                            TabFragment3 threeFragment = new TabFragment3();
                            break;
                    }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab1) {
//                binding.viewPager.setOffscreenPageLimit(1);
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab1) {
//                binding.viewPager.setCurrentItem(tab1.getPosition());
//                binding.tabLayout.getTabAt(tab1.getPosition());
                Toast.makeText(getApplicationContext(), String.valueOf(tab1.getPosition()), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void initialiseRefreshInterface(RefreshInterface refreshInterface){
        this.refreshInterface = refreshInterface;
    }

    public interface RefreshInterface {
        void refresh_fragment();
    }


    private void init() {
        // removing toolbar elevation
        getSupportActionBar().setElevation(0);
        binding.viewPager.setOffscreenPageLimit(2);
        binding.viewPager.setAdapter(new TabActivityAdapter(getSupportFragmentManager(),getLifecycle()));
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
            tab_label.setText(TabText[i]);
            tab_label.setTextColor(getResources().getColorStateList(R.drawable.change_color_text));
            tab_icon.setImageResource(ChangeTabIconFragment[i]);
            tabLayout.getTabAt(i).setCustomView(tab);
    }}




}