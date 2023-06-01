package com.example.hao_activity_submission.TabFragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

//import androidx.databinding.DataBindingUtil;
import com.example.hao_activity_submission.BeerListAdapter;
import com.example.hao_activity_submission.Controller.Tab1Controller;
import com.example.hao_activity_submission.Model.BeerModel;
import com.example.hao_activity_submission.Model.StudentModel;
import com.example.hao_activity_submission.R;
import com.example.hao_activity_submission.Singletons;
import com.example.hao_activity_submission.StudentListAdapter;
import com.example.hao_activity_submission.ViewModel.StudentListViewModel;

//import com.example.hao_activity_submission.databinding.FragmentTab1Binding;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class TabFragment1 extends Fragment implements BeerListAdapter.OnItemClickListener, BeerListAdapter.OnBottomReachedListener{

//    private FragmentTab1Binding binding;
//    private List<StudentModel> studentModelList;
//    private StudentListAdapter adapter;
//    private StudentListViewModel viewModel;
//    private ViewGroup fragmentContainer;
    private SwipeRefreshLayout swipeRefreshLayout;
//    RecyclerView recyclerView;
//    private LinearLayoutManager layoutManager;
//    @BindView(R.id.noResultTv)
//   TextView noresult;

    private ProgressBar mProgressBar;
    private BeerListAdapter beerListAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Tab1Controller controller;
    private RecyclerView recyclerView;
    List<BeerModel> beers;
    private View v;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
       v = inflater.inflate(R.layout.fragment_tab1, container, false);
        //Loading Effect
        //Loading Effect
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipeToRefresh);

        //progress bar
        mProgressBar = (ProgressBar) v.findViewById(R.id.progress_bar);

        //Get Data
        recyclerView = v.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
     //   beerListAdapter = new BeerListAdapter(beers, (BeerListAdapter.OnItemClickListener) this, (BeerListAdapter.OnBottomReachedListener) this, getActivity());
        recyclerView.setAdapter(beerListAdapter);


        // non-view related
        controller = new Tab1Controller(this, Singletons.getGson(), Singletons.getPunkRepository(this));
        controller.onStart();


        swipeRefreshLayout.setOnRefreshListener(() -> {
            controller = new Tab1Controller(this, Singletons.getGson(), Singletons.getPunkRepository(this));
            controller.onStart();
            mProgressBar.setVisibility(View.VISIBLE);
            swipeRefreshLayout.setRefreshing(false);
        });


        return v;
    }





    public void showList(List<BeerModel> beers) {
        recyclerView = v.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        beerListAdapter = new BeerListAdapter(beers, this, this, getContext());
        recyclerView.setAdapter(beerListAdapter);
        mProgressBar.setVisibility(View.GONE);
    }

    public void updateList(List<BeerModel> beers) {
        if (beers != null && recyclerView != null) {
            int oldSize = beerListAdapter.getBeers().size();
            beerListAdapter.getBeers().addAll(beers);
            recyclerView.setAdapter(beerListAdapter);
            mProgressBar.setVisibility(View.GONE);
            recyclerView.scrollToPosition(oldSize - 5);
        }
    }

    public void showSearchResults(List<BeerModel> beers) {
        if (beers != null && recyclerView != null) {
            beerListAdapter.setData(beers);
            recyclerView.setAdapter(beerListAdapter);
        }
    }


    @Override
    public void onItemClick(BeerModel beer) {
        controller.onItemClick(beer);
    }

    @Override
    public void onBottomReached(int position) {
//        controller.onBottomReached();
        controller.onStart2();
    }




}