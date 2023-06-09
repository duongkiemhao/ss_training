package com.example.hao_activity_submission.TabFragment;

import android.nfc.Tag;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//import androidx.databinding.DataBindingUtil;
import com.example.hao_activity_submission.BeerListAdapter;
import com.example.hao_activity_submission.BeerModel;
import com.example.hao_activity_submission.R;
import com.example.hao_activity_submission.ViewModel.BeerViewModel;
import com.example.hao_activity_submission.databinding.FragmentTab1Binding;

//import com.example.hao_activity_submission.databinding.FragmentTab1Binding;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class TabFragment1 extends Fragment {

    private BeerViewModel beerViewModel;
    BeerListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private int page = 1;

    private static final String TAG = "TabActivity";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentTab1Binding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tab1, container, false);

        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setHasFixedSize(true);



        binding.swipeToRefresh.setOnRefreshListener(() -> {
            page = 1;
            getData(page);
            binding.swipeToRefresh.setRefreshing(false);
        });

        layoutManager = new LinearLayoutManager(getActivity());
        binding.recyclerView.setLayoutManager(layoutManager);

        adapter = new BeerListAdapter();
        binding.recyclerView.setAdapter(adapter);

        beerViewModel= ViewModelProviders.of(this).get(BeerViewModel.class);
        getData(page);


        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                    int visibleItemCount = binding.recyclerView.getLayoutManager().getChildCount();
                    int totalItemCount = binding.recyclerView.getLayoutManager().getItemCount();
                    int pastVisibleItems = ((LinearLayoutManager)binding.recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

                Log.d(TAG, String.valueOf(visibleItemCount));
                Log.d(TAG, String.valueOf(totalItemCount));
                Log.d(TAG, String.valueOf(pastVisibleItems));
                if (pastVisibleItems + visibleItemCount >= totalItemCount) {
                    page++;
                    getData(page);
                }



            }
        });
        return binding.getRoot();
    }



    private void getData(int paging) {

        BeerViewModel.getAllBeer(paging*10).observe(getViewLifecycleOwner(), userList -> {
            adapter.setBeerList((ArrayList<BeerModel>) userList);
        });
    }




}