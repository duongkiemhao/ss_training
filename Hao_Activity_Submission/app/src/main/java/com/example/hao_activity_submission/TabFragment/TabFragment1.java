package com.example.hao_activity_submission.TabFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
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


    private SwipeRefreshLayout swipeRefreshLayout;

    private BeerViewModel beerViewModel;
    BeerListAdapter adapter;
    RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private int page = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentTab1Binding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tab1, container, false);

        recyclerView = binding.recyclerView;
        binding.recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        swipeRefreshLayout = (SwipeRefreshLayout) binding.swipeToRefresh.findViewById(R.id.swipeToRefresh);



        swipeRefreshLayout.setOnRefreshListener(() -> {
            page = 1;
            getData(page);
            swipeRefreshLayout.setRefreshing(false);
        });

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new BeerListAdapter();
        recyclerView.setAdapter(adapter);

        beerViewModel= ViewModelProviders.of(this).get(BeerViewModel.class);
        getData(page);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    if (!recyclerView.canScrollVertically(RecyclerView.FOCUS_DOWN)) {
                        layoutManager.scrollToPosition(0);
                        page++;
                        getData(page);
                    }
                }

            }
        });
        return binding.getRoot();
    }



    private void getData(int paging) {
        BeerViewModel.getAllBeer(paging).observe(getViewLifecycleOwner(), userList -> {
            adapter.setBeerList((ArrayList<BeerModel>) userList);
        });
    }




}