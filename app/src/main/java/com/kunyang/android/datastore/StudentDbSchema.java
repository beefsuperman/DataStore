package com.kunyang.android.datastore;

/**
 * Created by 坤阳 on 2017/10/15.
 */

public class StudentDbSchema {

    public static final class StudentTable{
        public static final String NAME="students";

        public static final class Cols{
            public static final String UUID="uuid";
            public static final String NAME="name";
            public static final String INFO="info";
        }
    }
}
