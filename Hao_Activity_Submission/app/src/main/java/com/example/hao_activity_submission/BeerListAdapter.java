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
import com.example.hao_activity_submission.databinding.FragmentTabTableRow2Binding;

import java.util.ArrayList;
import java.util.List;

public class BeerListAdapter extends RecyclerView.Adapter{

    private ArrayList<BeerModel> beers;
    ArrayList<BeerModelRoom> beers2;
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

    public BeerListAdapter( ArrayList<BeerModelRoom> beers) {
        this.beers2 = beers;
    }


    public void setData(List<BeerModel> beers) {
        this.beers = (ArrayList<BeerModel>) beers;
    }



    public static class BeerViewHolder1 extends RecyclerView.ViewHolder {

        FragmentTabTableRow1Binding itemBeerBinding;

        public BeerViewHolder1(@NonNull FragmentTabTableRow1Binding itemBeerBinding) {
            super(itemBeerBinding.getRoot());
            this.itemBeerBinding = itemBeerBinding;
        }
    }

    public static class BeerViewHolder2 extends RecyclerView.ViewHolder {

        FragmentTabTableRow2Binding itemBeerBinding2;

        public BeerViewHolder2(@NonNull FragmentTabTableRow2Binding itemBeerBinding2) {
            super(itemBeerBinding2.getRoot());
            this.itemBeerBinding2 = itemBeerBinding2;
        }
    }


    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view


        switch (viewType) {
            case BeerModel.TYPE_ODD:
                FragmentTabTableRow1Binding itemUserBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.fragment_tab_table_row_1, parent, false);
                return new BeerViewHolder1(itemUserBinding);
            case BeerModel.TYPE_EVEN:
                FragmentTabTableRow2Binding itemUserBinding2 = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.fragment_tab_table_row_2, parent, false);
                return new BeerViewHolder2(itemUserBinding2);

        }
        return null;


    }




    @Override
    public int getItemViewType(int position) {

        switch (position%2) {
            case 0:
                return BeerModel.TYPE_ODD;
            case 1:
                return BeerModel.TYPE_EVEN;
            default:
                return -1;
        }
    }


    //Inside the Adapter class
    @Override
    public long getItemId(int position) {
        return beers.get(position).getId();
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final BeerModel beer = beers.get(position);

        if (beer != null) {
            switch (position % 2) {
                case BeerModel.TYPE_ODD:
                    // ((TextTypeViewHolder) holder).txtType.setText(object.text);
                    ((BeerViewHolder1) holder).itemBeerBinding.setBeerModel(beer);
                    ((BeerViewHolder1) holder).itemBeerBinding.setImageUrl(beer.getImageUrl());
                    break;
                case BeerModel.TYPE_EVEN:
                    ((BeerViewHolder2) holder).itemBeerBinding2.setBeerModel(beer);
                    ((BeerViewHolder2) holder).itemBeerBinding2.setImageUrl(beer.getImageUrl());
                    break;

            }


//        holder.itemBeerBinding.setBeerModel(beer);
//        holder.itemBeerBinding.setImageUrl(beer.getImageUrl());
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

    public List<BeerModelRoom> getBeers2() {
        return beers2;
    }

    public void setBeerList(ArrayList<BeerModel> beers) {
        this.beers = beers;
//     notifyDataSetChanged();
//        notifyAll();
    }
    public void remove(){
        beers.clear();
    }

    public BeerModel getCurrentItemAt(int position) {
        return beers.get(position);
    }
}
