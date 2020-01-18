package com.semckinley.frcdeepspacescouting.sampledata;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class RobotDbHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "robotinfo.db";
    public static final int DATABASE_VERSION = 2;
    public RobotDbHelper (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_ROBOT_TABLE =
                "CREATE TABLE " + RobotContract.RobotEntry.TABLE_NAME + " (" +
                        RobotContract.RobotEntry._ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                        RobotContract.RobotEntry.COLUMN_TEAM_NUMBER + " INTEGER NOT NULL," +
                        RobotContract.RobotEntry.COLUMN_TEAM_NAME + " TEXT NOT NULL, " +
                        RobotContract.RobotEntry.COLUMN_DRIVE + " TEXT NOT NULL, " +
                        RobotContract.RobotEntry.COLUMN_CARGO + " TEXT NOT NULL, " +
                        RobotContract.RobotEntry.COLUMN_HATCH + " TEXT NOT NULL, " +
                        RobotContract.RobotEntry.COLUMN_RELOAD + " TEXT NOT NULL, " +
                        RobotContract.RobotEntry.COLUMN_CAN_START + " TEXT NOT NULL, " +
                        RobotContract.RobotEntry.COLUMN_PREF_START + " TEXT NOT NULL, " +
                        RobotContract.RobotEntry.COLUMN_CLIMB + " TEXT NOT NULL, " +
                        RobotContract.RobotEntry.COLUMN_SANDSTORM + " TEXT NOT NULL, " +
                        RobotContract.RobotEntry.COLUMN_ROBOT_PRELOAD + " TEXT NOT NULL, " +
                        RobotContract.RobotEntry.COLUMN_POD_PRELOAD + " TEXT NOT NULL, " +
                        RobotContract.RobotEntry.COLUMN_ROBOT_PHOTO_URI +" TEXT NOT NULL" +
                        "); ";
                    db.execSQL(SQL_CREATE_ROBOT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + RobotContract.RobotEntry.TABLE_NAME);//TODO Study SQLiteDatabases
        onCreate(db);
    }
}
