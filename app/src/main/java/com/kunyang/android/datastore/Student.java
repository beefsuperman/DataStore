package com.kunyang.android.datastore;

import java.util.UUID;

/**
 * Created by 坤阳 on 2017/10/15.
 */

public class Student {

    private UUID mId;
    private String mName;
    private String mInfo;

    public Student(){
        this(UUID.randomUUID());
    }

    public Student(UUID id){
        mId=id;
    }

    public UUID getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getInfo() {
        return mInfo;
    }

    public void setInfo(String info) {
        mInfo = info;
    }
}
