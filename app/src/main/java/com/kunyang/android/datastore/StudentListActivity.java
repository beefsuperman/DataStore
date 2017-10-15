package com.kunyang.android.datastore;

import android.support.v4.app.Fragment;

/**
 * Created by 坤阳 on 2017/10/15.
 */

public class StudentListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new StudentListFragment();
    }
}
