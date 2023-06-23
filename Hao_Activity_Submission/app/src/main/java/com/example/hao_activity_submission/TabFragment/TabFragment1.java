package com.example.hao_activity_submission.TabFragment;

import static androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY;
import  androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//import androidx.databinding.DataBindingUtil;
import com.example.hao_activity_submission.BeerListAdapter;
import com.example.hao_activity_submission.BeerModel;
import com.example.hao_activity_submission.R;
import com.example.hao_activity_submission.TabActivity;
import com.example.hao_activity_submission.ViewModel.BeerViewModel;
import com.example.hao_activity_submission.databinding.FragmentTab1Binding;

//import com.example.hao_activity_submission.databinding.FragmentTab1Binding;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class TabFragment1 extends Fragment implements TabActivity.RefreshInterface {

    private BeerViewModel beerViewModel;
    BeerListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private int page = 1;
    TabFragment1 tabFragment1;
    TabActivity tabActivity;
    private static final String TAG = "TabActivity";
    //interface refresh fragment
    TabActivity.RefreshInterface refreshInterface;
    FragmentTab1Binding binding;
    private ArrayList<BeerModel> beerArrayList = new ArrayList<>();
    Handler handler = new Handler();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tab1, container, false);

        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(layoutManager);



        adapter = new BeerListAdapter();
        adapter.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY);
        beerViewModel= ViewModelProviders.of(this).get(BeerViewModel.class);
        adapter.setHasStableIds(true);
        clearList();
        getData(page);



        binding.swipeToRefresh.setOnRefreshListener(() -> {
            page = 1;
            refresh_fragment();
            binding.swipeToRefresh.setRefreshing(false);
        });


//        adapter.getBeers().addAll(beers);
        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                    int visibleItemCount = binding.recyclerView.getLayoutManager().getChildCount();
                    int totalItemCount = binding.recyclerView.getLayoutManager().getItemCount();
                    int pastVisibleItems = ((LinearLayoutManager)binding.recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

                Log.d(TAG,"Visible Item Count: " + visibleItemCount);
                Log.d(TAG, "Total Item Count: " +String.valueOf(totalItemCount));
                Log.d(TAG, "Past Visible Items: " +String.valueOf(pastVisibleItems));


                if ((visibleItemCount + pastVisibleItems) >=
                        totalItemCount && pastVisibleItems >= 0 ) {


                    page++;
                    getData(page);
                    binding.recyclerView.scrollToPosition(page*10-10);
//                    binding.recyclerView.setNestedScrollingEnabled(true);
                }



            }
        });

        refreshInterface = this;
        return binding.getRoot();
    }

    @Override
    public void setUserVisibleHint(boolean isVisible) {
        super.setUserVisibleHint(isVisible);
        if (isVisible) {
            FragmentTransaction ftr = getFragmentManager().beginTransaction();
            ftr.detach(this).attach(this).commit();
        }
    }

    @Override
    public void refresh_fragment() {
        page =1;
        adapter.remove();
        clearList();
        adapter.getBeers().clear();
        adapter.notifyDataSetChanged();
        getData(1);
        //binding.recyclerView.scrollToPosition(0);
    }

    public void initialiseRefreshInterface(TabActivity.RefreshInterface refreshInterface){
        this.refreshInterface = refreshInterface;
    }
    private void launchChildFragment(){
        tabFragment1 = new TabFragment1();
        tabFragment1.initialiseRefreshInterface(refreshInterface);

    }


    public void clearList(){
        beerArrayList.clear();

    }


    public void getData(int paging) {

            BeerViewModel.getAllBeer(paging).observe(getActivity(), userList -> {

                Parcelable recycleViewState = binding.recyclerView.getLayoutManager().onSaveInstanceState();

                Log.d(TAG,"userList: " + userList.toString());
                beerArrayList.addAll(userList);
                adapter.setBeerList(beerArrayList);
                adapter.notifyItemRangeInserted(adapter.getItemCount(), beerArrayList.size() -1);
                adapter.notifyItemInserted(userList.size() - 1);
                binding.recyclerView.setAdapter(adapter);
//                adapter.notifyItemInserted(paging*10 - 11);

                adapter.notifyItemChanged( beerArrayList.size() - 1);

                Log.d(TAG, "New beer Array List Size: " +beerArrayList.size() );
//                adapter.notifyItemRangeChanged(binding.recyclerView.getLayoutManager().getItemCount() - 10, beerArrayList.size() - 1);
//                binding.recyclerView.getLayoutManager().onRestoreInstanceState(recycleViewState);


//                binding.recyclerView.scrollToPosition(binding.recyclerView.getLayoutManager().getItemCount() - 10);
//                adapter.notifyDataSetChanged();

            });
    }





//    public void getData(int paging) {
//        if(paging == 1){
////            adapter.getBeers();
//            beerArrayList.clear();
//            BeerViewModel.getAllBeer(paging).observe(getViewLifecycleOwner(), userList -> {
//
//                layoutManager = new LinearLayoutManager(getActivity());
//                binding.recyclerView.setHasFixedSize(true);
//                binding.recyclerView.setLayoutManager(layoutManager);
//            //    binding.recyclerView.setAdapter(adapter);
//                beerArrayList.addAll(userList);
//                adapter.setBeerList((ArrayList<BeerModel>) userList);
//                binding.recyclerView.setAdapter(adapter);
//            });
//        } else if (paging > 1){
////            ArrayList<BeerModel> theBeer = null;
////            for (int i = 1 ; i <= paging; i++ ){
//                BeerViewModel.getAllBeer(paging).observe(getViewLifecycleOwner(), userList -> {
////                adapter.setBeerList((ArrayList<BeerModel>) userList);
////                    theBeer.addAll(userList);
//                    beerArrayList.addAll(userList);
//                    adapter.setBeerList(beerArrayList);
////                    adapter.notifyDataSetChanged();
////                adapter.setBeerList((ArrayList<BeerModel>) userList);
////                adapter.getBeers().addAll(userList);
////                    adapter.getBeers().add()
//                    binding.recyclerView.setAdapter(adapter);
//                });
////            }binding.recyclerView.setAdapter(adapter);
//
//
////            adapter.getBeers().addAll(theBeer);
////            BeerViewModel.getAllBeer(paging).observe(getViewLifecycleOwner(), userList -> {
//////                adapter.setBeerList((ArrayList<BeerModel>) userList);
////                theBeer.add((BeerModel) userList);
//
////
////            });
//
//        }
//
//       // binding.recyclerView.smoothScrollToPosition(3);
//    }

//    public void getData2(int paging, FragmentTab1Binding binding) {
//
//        BeerViewModel.getAllBeer(paging*10).observe(getViewLifecycleOwner(), userList -> {
////            adapter.getBeers().addAll(userList);
//            adapter.setBeerList((ArrayList<BeerModel>) userList);
//        });
//        binding.recyclerView.smoothScrollToPosition(0);
//    }

    public void print(){
        Log.d("ff","from One fragment");
    }


}