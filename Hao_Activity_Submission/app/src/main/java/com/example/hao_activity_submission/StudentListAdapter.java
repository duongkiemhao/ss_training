package com.example.hao_activity_submission;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.hao_activity_submission.Model.StudentModel;

import java.util.List;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.MyViewHolder>{



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
    }

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
        TextView StudentId;
        TextView StudentName;

        public MyViewHolder(View itemView) {
            super(itemView);
            StudentId = (TextView)itemView.findViewById(R.id.tvStudentId);
            StudentName = (TextView)itemView.findViewById(R.id.tvStudentName);

        }
    }


    public interface ItemClickListener{
        public void onStudentClick(StudentModel student);
    }
}
