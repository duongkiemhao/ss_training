package com.example.hao_activity_submission.CommunicationFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hao_activity_submission.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class CommunicationFragment1 extends Fragment {


        Button red, blue, green;

    public CommunicationFragment1() {
            // Required empty public constructor
        }



        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_communication1, container, false);

            red = (Button) view.findViewById(R.id.red);
            blue= (Button) view.findViewById(R.id.blue);
            green = (Button) view.findViewById(R.id.green);

            red.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Navigation.findNavController(view).navigate(R.id.action_communicationFragment1_to_communicationFragmentA);
                }
            });

            blue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Navigation.findNavController(view).navigate(R.id.action_communicationFragment1_to_communicationFragmentB);
                }
            });

            green.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Navigation.findNavController(view).navigate(R.id.action_communicationFragment1_to_communicationFragmentC);
                }
            });


            return view;
        }
}