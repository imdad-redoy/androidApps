package com.example.risalat.slidingtabsusingviewpager;

import android.provider.BaseColumns;


public class CalContract {

    private CalContract(){

    }
    public static final class CalEntry implements BaseColumns {
        public static final String TABLE_NAME="caladd";
        public static final String COLUMN_NAME="name";
        public static final String COLUMN_TIMESTAMP="timestamp";

        public static final String TABLE_NAME_S="calsub";
        public static final String COLUMN_NAME_S="name";
        public static final String COLUMN_TIMESTAMP_S="timestamp";

        public static final String TABLE_NAME_M="calmul";
        public static final String COLUMN_NAME_M="name";
        public static final String COLUMN_TIMESTAMP_M="timestamp";

    }
}
