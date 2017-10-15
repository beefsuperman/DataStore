package com.kunyang.android.datastore;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SizeF;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.UUID;

/**
 * Created by 坤阳 on 2017/10/15.
 */

public class StudentFragment extends Fragment {

    private static final String ARG_STUDENT_ID="student_id";

    private Student mStudent;
    private EditText mNameField;
    private EditText mInfoField;

    public static StudentFragment newInstance(UUID studentId){
        Bundle args=new Bundle();
        args.putSerializable(ARG_STUDENT_ID,studentId);

        StudentFragment fragment=new StudentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        UUID studentId=(UUID)getArguments().getSerializable(ARG_STUDENT_ID);
        mStudent=StudentLab.get(getActivity()).getStudent(studentId);
    }

    @Override
    public void onPause(){
        super.onPause();;

        StudentLab.get(getActivity()).updateStudent(mStudent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View v=inflater.inflate(R.layout.fragment_student,container,false);

        mNameField=(EditText)v.findViewById(R.id.student_name);
        mNameField.setText(mStudent.getName());
        mNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mStudent.setName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mInfoField=(EditText)v.findViewById(R.id.student_info);
        mInfoField.setText(mStudent.getInfo());
        mInfoField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mStudent.setInfo(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.fragment_student,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_item_delete_student:
                StudentLab.get(getActivity()).deleteStudent(mStudent);
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
