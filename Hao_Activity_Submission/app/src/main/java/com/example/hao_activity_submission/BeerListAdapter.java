package com.example.hao_activity_submission;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hao_activity_submission.databinding.FragmentTabTableRow1Binding;

import java.util.ArrayList;
import java.util.List;

public class BeerListAdapter extends RecyclerView.Adapter<BeerListAdapter.BeerViewHolder>{

    private ArrayList<BeerModel> beers;
    private Context context;


//    public void add(int position, BeerModel item) {
//        beers.add(position, item);
//        notifyItemInserted(position);
//    }
//
//    public void remove(int position) {
//        beers.remove(position);
//        notifyItemRemoved(position);
//    }




    public void setData(List<BeerModel> beers) {
        this.beers = (ArrayList<BeerModel>) beers;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public BeerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        FragmentTabTableRow1Binding itemUserBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.fragment_tab_table_row_1, parent, false);
        return new BeerViewHolder(itemUserBinding);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(BeerViewHolder holder,int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final BeerModel beer = beers.get(position);
        holder.itemBeerBinding.setBeerModel(beer);
        holder.itemBeerBinding.setImageUrl(beer.getImageUrl());
//        Glide.with(holder.imageView.getContext())
//                .load(url)
//                .into(holder.imageView);

//        Glide.with(context).load(beer.getImageUrl()).into(holder.icon);

    }



    public class BeerViewHolder extends RecyclerView.ViewHolder {

        private FragmentTabTableRow1Binding itemBeerBinding;

        public BeerViewHolder(@NonNull FragmentTabTableRow1Binding itemBeerBinding) {
            super(itemBeerBinding.getRoot());
            this.itemBeerBinding = itemBeerBinding;
        }
    }


    @Override
    public int getItemCount() {
        if (beers != null) {
            return beers.size();
        } else {
            return 0;
        }
    }

    public List<BeerModel> getBeers() {
        return beers;
    }

    public void setBeerList(ArrayList<BeerModel> beers) {
        this.beers = beers;
        notifyDataSetChanged();
    }
    public BeerModel getCurrentItemAt(int position) {
        return beers.get(position);
    }
}
