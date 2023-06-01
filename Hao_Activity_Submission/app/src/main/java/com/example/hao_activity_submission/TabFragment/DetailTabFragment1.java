package com.example.hao_activity_submission.TabFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hao_activity_submission.R;


public class DetailTabFragment1 extends Fragment {


    public DetailTabFragment1() {
        // Required empty public constructor
    }


    public static DetailTabFragment1 newInstance(String param1, String param2) {
        DetailTabFragment1 fragment = new DetailTabFragment1();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_table_row_1, container, false);
    }
}