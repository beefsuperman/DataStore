package com.kunyang.android.datastore;

import android.content.Intent;
import android.database.Observable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.TimeUnit;

/**
 * Created by 坤阳 on 2017/10/15.
 */

public class StudentListFragment extends Fragment{

    private PullLoadMoreRecyclerView refreshRecyclerView;
    private RecyclerView mStudentRecyclerView;
    private StudentAdapter mAdapter;
    private List<Student> list = new ArrayList<>();
    private int i=0;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_crime_list,container,false);

        refreshRecyclerView = (PullLoadMoreRecyclerView) view.findViewById(R.id.pullLoadMoreRecyclerView);
        refreshRecyclerView.setLinearLayout();

        refreshRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshRecyclerView.setPullLoadMoreCompleted();
                    }
                }, 1500);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        StudentLab studentLab=StudentLab.get(getActivity());
                        List<Student> students=studentLab.getStudents();

                        Student s=new Student();
                        s.setName("Student #"+i);
                        i++;
                        StudentLab.get(getActivity()).addStudent(s);
                        updateUI();
                        mAdapter.notifyItemInserted(students.size()-1);
                        refreshRecyclerView.setPullLoadMoreCompleted();
//                        refreshRecyclerView.scrollToTop();//滑动顶部
                    }
                }, 1000);
            }
        });

        updateUI();

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.fragment_student_list,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_item_new_student:
                Student student=new Student();
                StudentLab.get(getActivity()).addStudent(student);
                Intent intent=StudentActivity.newIntent(getActivity(),student.getId());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateUI(){
        StudentLab studentLab=StudentLab.get(getActivity());
        List<Student> students=studentLab.getStudents();

        if (mAdapter==null) {
            mAdapter = new StudentAdapter(students);
            refreshRecyclerView.setAdapter(mAdapter);
        }else {
            mAdapter.setStudents(students);
            mAdapter.notifyDataSetChanged();
        }
    }

    private class StudentHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Student mStudent;

        private TextView mNameTextView;
        private TextView mInfoTextView;

        public StudentHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);

            mNameTextView=(TextView)itemView.findViewById(R.id.list_item_student_name_text_view);
            mInfoTextView=(TextView)itemView.findViewById(R.id.list_item_student_info_text_view);
        }

        public void bindStudent(Student student){
            mStudent=student;
            mNameTextView.setText(mStudent.getName());
            mInfoTextView.setText(mStudent.getInfo());
        }

        @Override
        public void onClick(View view) {

            Intent intent=StudentPagerActivity.newIntent(getActivity(),mStudent.getId());
            startActivity(intent);
        }
    }

    private class StudentAdapter extends RecyclerView.Adapter<StudentHolder>{

        private List<Student>mStudents;

        public StudentAdapter(List<Student> students){
            mStudents=students;
        }

        @Override
        public StudentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater=LayoutInflater.from(getActivity());
            View view=layoutInflater.inflate(R.layout.list_item_student,parent,false);
            return new StudentHolder(view);
        }

        @Override
        public void onBindViewHolder(StudentHolder holder, int position) {
            Student student=mStudents.get(position);
            holder.bindStudent(student);
        }

        @Override
        public int getItemCount() {
            return mStudents.size();
        }

        public void setStudents(List<Student> students){
            mStudents=students;
        }
    }

}
