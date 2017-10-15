package com.kunyang.android.datastore;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

/**
 * Created by 坤阳 on 2017/10/15.
 */

public class StudentPagerActivity extends AppCompatActivity {

    private static final String EXTRA_STUDENT_ID=
            "com.kunyang.android.datastore.student_id";

    private ViewPager mViewPager;
    private List<Student>mStudents;

    public static Intent newIntent(Context packageContext, UUID studentId){
        Intent intent=new Intent(packageContext,StudentPagerActivity.class);
        intent.putExtra(EXTRA_STUDENT_ID,studentId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_pager);

        UUID studentId=(UUID)getIntent().getSerializableExtra(EXTRA_STUDENT_ID);

        mViewPager=(ViewPager)findViewById(R.id.activity_student_pager_view_pager);

        mStudents=StudentLab.get(this).getStudents();
        FragmentManager fragmentManager=getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Student student=mStudents.get(position);
                return StudentFragment.newInstance(student.getId());
            }

            @Override
            public int getCount() {
                return mStudents.size();
            }
        });

        for (int i=0;i<mStudents.size();i++){
            if (mStudents.get(i).getId().equals(studentId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
