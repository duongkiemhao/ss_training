package com.example.hao_activity_submission.TabFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hao_activity_submission.BeerListAdapter;
import com.example.hao_activity_submission.BeerModel;
import com.example.hao_activity_submission.BeerModelRoom;
import com.example.hao_activity_submission.BeerRoomListAdapter;
import com.example.hao_activity_submission.Network.PunkApi;
import com.example.hao_activity_submission.Network.PunkRoomRepository;
import com.example.hao_activity_submission.R;
import com.example.hao_activity_submission.TabActivity;
import com.example.hao_activity_submission.Utils.SingleLiveEvent;
import com.example.hao_activity_submission.ViewModel.BeerRoomViewModel;
import com.example.hao_activity_submission.ViewModel.BeerViewModel;
import com.example.hao_activity_submission.databinding.FragmentTab1Binding;
import com.example.hao_activity_submission.databinding.FragmentTab2Binding;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TabFragment2 extends Fragment implements TabActivity.RefreshInterface2 {

    FragmentTab2Binding binding;

    private BeerRoomViewModel beerViewModel;
    private BeerRoomListAdapter adapter;
    private static final String TAG = "TabActivity";
    //for paging
    private int page = 1;
    protected boolean pagingLoading = true;
    protected int PAGE_LIMIT = 10;
    private PunkRoomRepository repository;
    private ArrayList<BeerModelRoom> userArrayList = new ArrayList<>();
    private SingleLiveEvent<List<BeerModelRoom>> mutableLiveData = new SingleLiveEvent<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tab2, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        beerViewModel = ViewModelProviders.of(this).get(BeerRoomViewModel.class);

        repository = new PunkRoomRepository(getActivity().getApplication());

        binding.recyclerView2.setHasFixedSize(true);
        binding.recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new BeerRoomListAdapter(userArrayList);
        adapter.setBeerList(new ArrayList<>());
        binding.recyclerView2.setAdapter(adapter);

        getData();

        binding.swipeToRefresh.setOnRefreshListener(() -> {
            refresh_fragment();
            binding.swipeToRefresh.setRefreshing(false);
        });

        binding.recyclerView2.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) { //check for scroll down
                    int pagingVisibleItemCount =
                            ((LinearLayoutManager) binding.recyclerView2.getLayoutManager()).getChildCount();
                    int pagingTotalItemCount =
                            ((LinearLayoutManager) binding.recyclerView2.getLayoutManager()).getItemCount();
                    int pagingPastVisibleItems =
                            ((LinearLayoutManager) binding.recyclerView2.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
                    if (pagingLoading) {
                        if (pagingVisibleItemCount + pagingPastVisibleItems >= pagingTotalItemCount) {
                            pagingLoading = false;
                            if (pagingTotalItemCount >= PAGE_LIMIT) {
                                page++;
                                getData();
                            }
                        }
                    }
                }
            }
        });
        getData();
    }

    @Override
    public void refresh_fragment() {
        page = 1;
        adapter.setBeerList(new ArrayList<>());
        adapter.notifyDataSetChanged();
        getData();
    }


    public void getData() {
        Log.d(TAG,"Get data Tab Fragment 2");
        makeRequest(page);


        beerViewModel.getAllCats().observe(getActivity(),userList -> {

                if (page != 1) {
                //    binding.recyclerView2.setAdapter(adapter);
                  //  adapter.getAllData(userArrayList);
                    adapter.getBeers().addAll(userList);
                    adapter.notifyItemInserted(adapter.getBeers().size() - userList.size());
                    pagingLoading = true;
                  //  mutableLiveData.setValue(userArrayList);
                    Log.d("main", "onChanged: "+userList);
                } else {
                    adapter.setBeerList((ArrayList<BeerModelRoom>) userList);
                    adapter.notifyDataSetChanged();
                }
        });
    }

    private void makeRequest(int t) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpclient = new OkHttpClient.Builder().addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.punkapi.com/v2/")
                .client(httpclient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PunkApi api = retrofit.create(PunkApi.class);
        Call<List<BeerModelRoom>> call = api.getBeers2(String.valueOf(t));
        call.enqueue(new retrofit2.Callback<List<BeerModelRoom>>() {
            @Override
            public void onResponse(Call<List<BeerModelRoom>> call, Response<List<BeerModelRoom>> response) {
                if (response.body() != null) {
//
                    repository.insert(response.body());
                    userArrayList = (ArrayList<BeerModelRoom>) response.body();

                }
            }

            @Override
            public void onFailure(Call<List<BeerModelRoom>> call, Throwable t) {
                Log.d("main", "onFailure: " + t.getMessage());
            }
        });
    }
}