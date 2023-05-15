package com.example.hao_activity_2;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button createFragment, removeFragment;
 private static  final String FRAGMENT_TAG  = "Fragment_Tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createFragment =(Button) findViewById(R.id.btnCreateFragment);
        removeFragment =(Button) findViewById(R.id.btnRemoveFragment);


        createFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new Fragment1());
            }
        });
        removeFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removingFragment(new Fragment1());
            }
        });
    }

    private void loadFragment(Fragment fragment) {
// create a FragmentManager
        FragmentManager fm = getFragmentManager();
// create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
// replace the FrameLayout with new Fragment
        fragmentTransaction.add(R.id.frameLayout, fragment, FRAGMENT_TAG);
        fragmentTransaction.commit(); // save the changes
    }

    private void removingFragment(Fragment fragment) {

        FragmentManager fm = getFragmentManager();
        fragment = fm.findFragmentByTag(FRAGMENT_TAG);
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        fragmentTransaction.remove(fragment);
        fragmentTransaction.commit(); // save the changes
    }
}