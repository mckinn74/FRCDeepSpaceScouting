package com.semckinley.frcdeepspacescouting;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.semckinley.frcdeepspacescouting.sampledata.RobotContract;
import com.semckinley.frcdeepspacescouting.sampledata.RobotDbHelper;

public class DetailActivity extends AppCompatActivity {
    TextView mTextView;
    SQLiteDatabase mDb;
    RobotDbHelper mRobotDbHelper;
    Context mContext;
    String mNumber;
    Cursor mCursor;
    int mCount;
    ImageView mPhoto;
    TextView mTeamNumber;
    TextView mName;
    TextView mDrive;
    String mBob = ": ";
    TextView mHatch;
    TextView mCargo;
    TextView mReload;
    TextView mCanStart;
    TextView mPrefStart;
    TextView mClimb;
    TextView mSand;
    TextView mRobPre;
    TextView mPodPre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mPhoto = (ImageView) findViewById(R.id.iv_detailPhoto);
        mContext = getBaseContext();
        mTextView = (TextView) findViewById(R.id.tv_detailNumber);
        mTeamNumber = (TextView) findViewById(R.id.tv_dispNumber);
        mName = (TextView) findViewById(R.id.tv_dispName);
        mDrive = (TextView) findViewById(R.id.tv_dispDrive);
        mHatch = (TextView) findViewById(R.id.tv_dispHatch);
        mCargo = (TextView) findViewById(R.id.tv_dispCargo);
        mReload = (TextView) findViewById(R.id.tv_dispReload);
        mCanStart = (TextView) findViewById(R.id.tv_dispCanStart);
        mPrefStart = (TextView) findViewById(R.id.tv_dispPrefStart);
        mClimb = (TextView) findViewById(R.id.tv_dispClimb);
        mSand = (TextView) findViewById(R.id.tv_dispSand);
        mRobPre = (TextView) findViewById(R.id.tv_dispRobPre);
        mPodPre = (TextView) findViewById(R.id.tv_dispPodPre);
        Intent intent = getIntent();
        if(intent.hasExtra("team_number")) {
            mNumber = intent.getStringExtra("team_number");
            queryTask();

        }

    }

    public void queryTask(){
        mRobotDbHelper = new RobotDbHelper(this);
        mDb = mRobotDbHelper.getReadableDatabase();
        String column = RobotContract.RobotEntry.COLUMN_TEAM_NUMBER;
        String [] team = {mNumber};
        String whereClause = column + " = ?";
        mCursor = mDb.query(RobotContract.RobotEntry.TABLE_NAME, null, whereClause, team, null, null,
                null);
        if(mCursor != null) {
            mCount = mCursor.getCount();
            mCursor.moveToPosition(0);
            String name = mCursor.getString(mCursor.getColumnIndex(RobotContract.RobotEntry.COLUMN_TEAM_NAME));
            String number = mCursor.getString(mCursor.getColumnIndex(RobotContract.RobotEntry.COLUMN_TEAM_NUMBER));
            String drive = mCursor.getString(mCursor.getColumnIndex(RobotContract.RobotEntry.COLUMN_DRIVE));
            String hatch = mCursor.getString(mCursor.getColumnIndex(RobotContract.RobotEntry.COLUMN_HATCH));
            String cargo = mCursor.getString(mCursor.getColumnIndex(RobotContract.RobotEntry.COLUMN_CARGO));
            String reload = mCursor.getString(mCursor.getColumnIndex(RobotContract.RobotEntry.COLUMN_RELOAD));
            String can_start = mCursor.getString(mCursor.getColumnIndex(RobotContract.RobotEntry.COLUMN_CAN_START));
            String pref_start = mCursor.getString(mCursor.getColumnIndex(RobotContract.RobotEntry.COLUMN_PREF_START));
            String climb = mCursor.getString(mCursor.getColumnIndex(RobotContract.RobotEntry.COLUMN_CLIMB));
            String sand = mCursor.getString(mCursor.getColumnIndex(RobotContract.RobotEntry.COLUMN_SANDSTORM));
            String rob_pload = mCursor.getString(mCursor.getColumnIndex(RobotContract.RobotEntry.COLUMN_ROBOT_PRELOAD));
            String pod_pload = mCursor.getString(mCursor.getColumnIndex(RobotContract.RobotEntry.COLUMN_POD_PRELOAD));


            String photo = mCursor.getString(mCursor.getColumnIndex(RobotContract.RobotEntry.COLUMN_ROBOT_PHOTO_URI));

            Uri uri = Uri.parse(photo);
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                        getContentResolver(), uri);
                mPhoto.setImageBitmap(bitmap);
            }
            catch(Exception e){
                e.printStackTrace();
            }
            mTeamNumber.setText(number);
            mName.setText(" " + name);
           mDrive.setText(mBob + drive);
           mHatch.setText(mBob + hatch);
           mCargo.setText(mBob + cargo);
           mReload.setText(mBob + reload);
           mCanStart.setText(mBob + can_start);
           mPrefStart.setText(mBob + pref_start);
           mClimb.setText(mBob + climb);
           mSand.setText(mBob + sand);
           mRobPre.setText(mBob + rob_pload);
           mPodPre.setText(mBob + pod_pload);

        }
        else{mCount = 0;
        String printable = "Your search for " + mNumber + " returned " + mCount + " entries";
        mTextView.setText(printable);}


    }
}
