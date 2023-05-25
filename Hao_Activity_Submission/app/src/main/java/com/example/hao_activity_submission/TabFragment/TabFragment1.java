package com.example.hao_activity_submission.TabFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hao_activity_submission.Model.StudentModel;
import com.example.hao_activity_submission.R;
import com.example.hao_activity_submission.StudentListAdapter;
import com.example.hao_activity_submission.ViewModel.StudentListViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class TabFragment1 extends Fragment implements StudentListAdapter.ItemClickListener{
    private List<StudentModel> studentModelList;
    private StudentListAdapter adapter;
    private StudentListViewModel viewModel;
    private ViewGroup fragmentContainer;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ProgressBar mProgressBar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v = inflater.inflate(R.layout.fragment_tab1, container, false);
        //Loading Effect
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipeToRefresh);


        //Get Data
        recyclerView = v.findViewById(R.id.recyclerView);
        final TextView noresult = v.findViewById(R.id.noResultTv);
        layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
        adapter =  new StudentListAdapter(getActivity(), studentModelList, this);
        recyclerView.setAdapter(adapter);

        //progress bar
        mProgressBar = (ProgressBar) v.findViewById(R.id.progress_bar);

        //refresh
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                theReset();
                swipeRefreshLayout.setRefreshing(false);
            }
        });


        viewModel = ViewModelProviders.of(this).get(StudentListViewModel.class);
        viewModel.getStudentListObserver().observe(getViewLifecycleOwner(), new Observer<List<StudentModel>>() {
            @Override
            public void onChanged(List<StudentModel> movieModels) {
                if(movieModels != null) {
                    studentModelList = movieModels;
                    adapter.setStudentList(movieModels);
                    noresult.setVisibility(View.GONE);
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    noresult.setVisibility(View.VISIBLE);
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        });


        viewModel.makeApiCall();


        return v;
    }

    @Override
    public void onStudentClick(StudentModel student) {
        Toast.makeText(getActivity(), "Student ID is: " + student.getStudentId() + "; Name: " +student.getStudentName(), Toast.LENGTH_SHORT).show();
    }

    public void theReset() {

        layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new  StudentListAdapter(getActivity(), studentModelList, this);

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        viewModel.makeApiCall();
        mProgressBar.setVisibility(View.VISIBLE);
    }


}