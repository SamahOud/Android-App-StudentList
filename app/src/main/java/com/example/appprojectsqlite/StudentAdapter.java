package com.example.appprojectsqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder>{
    private List<Student> studentList;
    private MyStudentListener listener;
    Context context;

    public StudentAdapter(Context context, List<Student> stList){
        this.context = context;
        this.studentList = stList;
    }

    interface MyStudentListener{
        void onStudentClicked(int position, View view);
        void onLongStudentClicked(int position, View view);
    }

    public void setListener(MyStudentListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_cell,parent,false);
        StudentViewHolder studentViewHolder = new StudentViewHolder(view);
        return studentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.firstNameTv.setText(student.getFirstName());
        holder.lastNameTv.setText(student.getLastName());
        holder.addressTv.setText(student.getAddress());
        holder.avgTv.setText(student.getAvg()+"");
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView firstNameTv, lastNameTv, addressTv, avgTv;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            firstNameTv = itemView.findViewById(R.id.tvFirstName);
            lastNameTv = itemView.findViewById(R.id.tvLastName);
            addressTv = itemView.findViewById(R.id.tvAddress);
            avgTv = itemView.findViewById(R.id.tvAvg);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (listener != null)
                        listener.onLongStudentClicked(getAdapterPosition(),v);
                    return false;
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.onStudentClicked(getAdapterPosition(),v);
                }
            });
        }
    }
}
