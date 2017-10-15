package com.kunyang.android.datastore;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;

import java.util.UUID;

public class StudentActivity extends SingleFragmentActivity {

    private static final String EXTRA_STUDENT_ID=
            "com.kunyang.android.datastore.student_id";

    public static Intent newIntent(Context packageContext, UUID studentId){
        Intent intent=new Intent(packageContext,StudentActivity.class);
        intent.putExtra(EXTRA_STUDENT_ID,studentId);
        return intent;
    }

    @Override
    protected Fragment createFragment(){
        UUID studentId=(UUID)getIntent().getSerializableExtra(EXTRA_STUDENT_ID);
        return StudentFragment.newInstance(studentId);
    }
}
