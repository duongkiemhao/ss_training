package com.example.hao_activity_submission;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.hao_activity_submission.Model.StudentModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.MyViewHolder>{


    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;


    private  ProgressBar progressBar;
    private Context context;
    private List<StudentModel> studentList;
    private ItemClickListener clickListener;

    public StudentListAdapter(Context context, List<StudentModel> studentList, ItemClickListener clickListener) {
        this.context = context;
        this.studentList = studentList;
        this.clickListener = clickListener;
    }

    public void setStudentList(List<StudentModel> studentList) {
        this.studentList = studentList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StudentListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycle_student_row, parent, false);
        return new MyViewHolder(view);

//        if (viewType == VIEW_TYPE_ITEM) {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_tab1, parent, false);
//            return new ItemViewHolder(view);
//        } else {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_tab_loading, parent, false);
//            return new LoadingViewHolder(view);
//        }
    }


//    @Override
//    public void onBindViewHolder(@NonNull StudentListAdapter.MyViewHolder holder, int position) {
//
//        if (holder instanceof ItemViewHolder) {
//            StudentModel student = studentList.get(holder.getAdapterPosition());
//            holder.StudentId.setText(this.studentList.get(position).getStudentId().toString());
//            holder.StudentName.setText(this.studentList.get(position).getStudentName().toString());
//        //    populateItemRows((ItemViewHolder) viewHolder, position);
//        } else if (holder instanceof LoadingViewHolder) {
//            showLoadingView((LoadingViewHolder) holder, position);
//        }
//
//    }



    @Nullable
    @Override
    public void onBindViewHolder(@NonNull StudentListAdapter.MyViewHolder holder, int position) {
        StudentModel student = studentList.get(holder.getAdapterPosition());
        holder.StudentId.setText(this.studentList.get(position).getStudentId().toString());
        holder.StudentName.setText(this.studentList.get(position).getStudentName().toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onStudentClick(studentList.get(holder.getAdapterPosition()));
            }
        });
//        Glide.with(context)
//                .load(this.studentList.get(position).getStudentName())
//                .apply(RequestOptions.centerCropTransform())
//                .into(holder.);


    }

    @Override
    public int getItemCount() {
        if(this.studentList != null){
            return this.studentList.size();
        }
        return 0;
    }




    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvStudentId)
        TextView StudentId;
        @BindView(R.id.tvStudentName)
        TextView StudentName;


        public MyViewHolder(View itemView) {
            super(itemView);
            StudentId = (TextView)itemView.findViewById(R.id.tvStudentId);
            StudentName = (TextView)itemView.findViewById(R.id.tvStudentName);
            progressBar = (ProgressBar) itemView.findViewById(R.id.theProgressBar);
//            Butter knife method
            ButterKnife.bind(this, itemView);
        }
    }

//    @Override
//    public void onDataChanged(){
//        if(progressBar != null){
//            progressBar.setVisibility(View.GONE);
//        }
//    }

    //Version 2
    private class ItemViewHolder extends StudentListAdapter.MyViewHolder {

        TextView StudentId;
        TextView StudentName;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            StudentId = (TextView)itemView.findViewById(R.id.tvStudentId);
            StudentName = (TextView)itemView.findViewById(R.id.tvStudentName);
            ButterKnife.bind(this, itemView);
        }
    }

    private class LoadingViewHolder extends StudentListAdapter.MyViewHolder {

        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.theProgressBar);
        }
    }

    private void showLoadingView(LoadingViewHolder viewHolder, int position) {
        //ProgressBar would be displayed

    }



//    private void populateItemRows(ItemViewHolder viewHolder, int position) {
//
//        String item = mItemList.get(position);
//        viewHolder.tvItem.setText(item);
//
//    }

    public interface ItemClickListener{
        public void onStudentClick(StudentModel student);
    }
}
