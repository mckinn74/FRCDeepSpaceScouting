package com.semckinley.frcdeepspacescouting.sampledata;

import android.net.Uri;
import android.provider.BaseColumns;

public class RobotContract {
    public static final String AUTHORITY = "com.semckinley.frcdeepspacescouting";
    public static final String ROBOT_INFORMATION = "robotinformation";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final class RobotEntry implements BaseColumns{

        public static final String TABLE_NAME = "robotinformation";
        public static final String COLUMN_TEAM_NUMBER = "teamNumber";
        public static final String COLUMN_TEAM_NAME = "teamName";
        public static final String COLUMN_DRIVE = "driveTrain";
        public static final String COLUMN_CARGO = "cargo";
        public static final String COLUMN_HATCH = "hatchPanels";
        public static final String COLUMN_RELOAD = "reload";
        public static final String COLUMN_CAN_START = "canStart";
        public static final String COLUMN_CLIMB = "climb";
        public static final String COLUMN_PREF_START = "prefStart";
        public static final String COLUMN_SANDSTORM = "sandstorm";
        public static final String COLUMN_ROBOT_PRELOAD = "robotPreLoad";
        public static final String COLUMN_POD_PRELOAD = "podPreLoad";
        public static final String COLUMN_ROBOT_PHOTO_URI = "photoUri";

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

    }
}
