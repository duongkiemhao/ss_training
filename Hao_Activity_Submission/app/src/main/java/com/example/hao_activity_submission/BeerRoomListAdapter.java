package com.example.hao_activity_submission;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hao_activity_submission.databinding.FragmentTab2TableRow1Binding;
import com.example.hao_activity_submission.databinding.FragmentTab2TableRow2Binding;
import com.example.hao_activity_submission.databinding.FragmentTabTableRow1Binding;
import com.example.hao_activity_submission.databinding.FragmentTabTableRow2Binding;

import java.util.ArrayList;
import java.util.List;

public class BeerRoomListAdapter extends RecyclerView.Adapter {
     ArrayList<BeerModelRoom> beers;
     Context context;


//    public void add(int position, BeerModel item) {
//        beers.add(position, item);
//        notifyItemInserted(position);
//    }
//
//    public void remove(int position) {
//        beers.remove(position);
//        notifyItemRemoved(position);
//    }

    public BeerRoomListAdapter( ArrayList<BeerModelRoom> beers) {
        this.beers = beers;
    }


    public void setData(List<BeerModelRoom> beers) {
        this.beers = (ArrayList<BeerModelRoom>) beers;
    }



    public static class BeerViewHolder1 extends RecyclerView.ViewHolder {

        private FragmentTab2TableRow1Binding itemBeerBinding;

        public BeerViewHolder1(@NonNull FragmentTab2TableRow1Binding itemBeerBinding) {
            super(itemBeerBinding.getRoot());
            this.itemBeerBinding = itemBeerBinding;
        }
    }

    public static class BeerViewHolder2 extends RecyclerView.ViewHolder {

        private FragmentTab2TableRow2Binding itemBeerBinding2;

        public BeerViewHolder2(@NonNull FragmentTab2TableRow2Binding itemBeerBinding2) {
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
            case BeerModelRoom.TYPE_ODD:
                FragmentTab2TableRow1Binding itemUserBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.fragment_tab2_table_row_1, parent, false);
                return new BeerRoomListAdapter.BeerViewHolder1(itemUserBinding);
            case BeerModelRoom.TYPE_EVEN:
                FragmentTab2TableRow2Binding itemUserBinding2 = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.fragment_tab2_table_row_2, parent, false);
                return new BeerRoomListAdapter.BeerViewHolder2(itemUserBinding2);

        }
        return null;


    }




    @Override
    public int getItemViewType(int position) {

        switch (position%2) {
            case 0:
                return BeerModelRoom.TYPE_ODD;
            case 1:
                return BeerModelRoom.TYPE_EVEN;
            default:
                return -1;
        }
    }

    public void getAllData( ArrayList<BeerModelRoom> beers)
    {
        this.beers=beers;
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
        final BeerModelRoom beer = beers.get(position);

        if (beer != null) {
            switch (position % 2) {
                case BeerModelRoom.TYPE_ODD:
                    // ((TextTypeViewHolder) holder).txtType.setText(object.text);
                    ((BeerRoomListAdapter.BeerViewHolder1) holder).itemBeerBinding.setBeerModelRoom(beer);
                    ((BeerRoomListAdapter.BeerViewHolder1) holder).itemBeerBinding.setImageUrl(beer.getImageUrl());
                    break;
                case BeerModelRoom.TYPE_EVEN:
                    ((BeerRoomListAdapter.BeerViewHolder2) holder).itemBeerBinding2.setBeerModelRoom(beer);
                    ((BeerRoomListAdapter.BeerViewHolder2) holder).itemBeerBinding2.setImageUrl(beer.getImageUrl());
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

    public List<BeerModelRoom> getBeers() {
        return beers;
    }

    public void setBeerList(ArrayList<BeerModelRoom> beers) {
        this.beers = beers;
//     notifyDataSetChanged();
//        notifyAll();
    }
    public void remove(){
        beers.clear();
    }

    public BeerModelRoom getCurrentItemAt(int position) {
        return beers.get(position);
    }
}
