package com.semckinley.frcdeepspacescouting;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.semckinley.frcdeepspacescouting.sampledata.RobotContract;
import com.semckinley.frcdeepspacescouting.sampledata.RobotDbHelper;

import java.io.File;
import java.net.URI;

import static android.support.v4.content.FileProvider.getUriForFile;
import static java.security.AccessController.getContext;

public class EntryActivity extends AppCompatActivity {
        Spinner mDriveSpinner;
        Spinner mHatchSpinner;
        Spinner mCanStartSpinner;
        Spinner mPrefStartSpinner;
        Spinner mRobPreLoadSpinner;
        EditText mEditNumber;
        EditText mEditName;
        Spinner mCargoSpinner;
        Spinner mReloadSpinner;
        Spinner mClimbSpinner;
        Spinner mSandSpinner;
        Spinner mPodPreSpinner;
        Spinner mRobotPreSpinner;
        Button mPhotoBtn;
        ImageView mRobotPhoto;
        String mTeamNumber;
        public static final int REQUEST_PHOTO = 20;
        public static final int requestCode = 20;
        Uri robotImageUri;
        SQLiteDatabase mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        mTeamNumber = "3457";

        mEditNumber = (EditText) findViewById(R.id.et_team_id);
        mEditName = (EditText) findViewById(R.id.et_team_name);

        mDriveSpinner = (Spinner) findViewById(R.id.sp_drive);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.DriveTrain, android.R.layout.simple_spinner_item);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mDriveSpinner.setAdapter(adapter1);

        mHatchSpinner = (Spinner) findViewById(R.id.sp_hatches);
        ArrayAdapter<CharSequence> adapterHatch = ArrayAdapter.createFromResource(this,
                R.array.Hatches, android.R.layout.simple_spinner_item);

        adapterHatch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mHatchSpinner.setAdapter(adapterHatch);

        mCanStartSpinner = (Spinner) findViewById(R.id.sp_can_start);
        ArrayAdapter<CharSequence> adapterCanStart = ArrayAdapter.createFromResource(this,
                R.array.Can_Start, android.R.layout.simple_spinner_item);

        adapterCanStart.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mCanStartSpinner.setAdapter(adapterCanStart);

        mPrefStartSpinner = (Spinner) findViewById(R.id.sp_prefs_start);
        ArrayAdapter<CharSequence> adapterPrefStart = ArrayAdapter.createFromResource(this,
                R.array.Pref_Start, android.R.layout.simple_spinner_item);

        adapterPrefStart.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mPrefStartSpinner.setAdapter(adapterPrefStart);

        mRobPreLoadSpinner = (Spinner) findViewById(R.id.sp_hatches);
        ArrayAdapter<CharSequence> adapterRobPreLoad = ArrayAdapter.createFromResource(this,
                R.array.Hatches, android.R.layout.simple_spinner_item);

        adapterRobPreLoad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mRobPreLoadSpinner.setAdapter(adapterRobPreLoad);

        mCargoSpinner = (Spinner) findViewById(R.id.sp_cargo);
        ArrayAdapter<CharSequence> adapterCargo = ArrayAdapter.createFromResource(this,
                R.array.Cargo, android.R.layout.simple_spinner_item);

        adapterCargo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mCargoSpinner.setAdapter(adapterCargo);
        //mCargoSpinner.setOnItemSelectedListener(this);


        mReloadSpinner = (Spinner) findViewById(R.id.sp_reload);
        ArrayAdapter<CharSequence> adapterReload = ArrayAdapter.createFromResource(this,
                R.array.Reload, android.R.layout.simple_spinner_item);

        adapterReload.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mReloadSpinner.setAdapter(adapterReload);

        mClimbSpinner = (Spinner) findViewById(R.id.sp_climb);
        ArrayAdapter<CharSequence> adapterClimb = ArrayAdapter.createFromResource(this,
                R.array.Climb, android.R.layout.simple_spinner_item);

        adapterClimb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mClimbSpinner.setAdapter(adapterClimb);

        mSandSpinner = (Spinner) findViewById(R.id.sp_sandstorm);
        ArrayAdapter<CharSequence> adapterSand = ArrayAdapter.createFromResource(this,
                R.array.SandStorm, android.R.layout.simple_spinner_item);

        adapterSand.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSandSpinner.setAdapter(adapterSand);

        mPodPreSpinner = (Spinner) findViewById(R.id.sp_pre_pod);
        ArrayAdapter<CharSequence> adapterPrePod = ArrayAdapter.createFromResource(this,
                R.array.pod_preload, android.R.layout.simple_spinner_item);

        adapterPrePod.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mPodPreSpinner.setAdapter(adapterPrePod);

        mRobotPreSpinner = (Spinner) findViewById(R.id.sp_pre_rbt);
        ArrayAdapter<CharSequence> adapterPreRob = ArrayAdapter.createFromResource(this,
                R.array.robot_preload, android.R.layout.simple_spinner_item);

        adapterPreRob.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mRobotPreSpinner.setAdapter(adapterPreRob);

        mPhotoBtn = (Button) findViewById(R.id.bt_robot_photo);
        mRobotPhoto = (ImageView) findViewById(R.id.iv_rbt_photo);
        RobotDbHelper robotDbHelper = new RobotDbHelper(this);
        mDb = robotDbHelper.getWritableDatabase();

    }

    public void takePhoto(View view){
         File mPhotoFile = robotFile();
        Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

         robotImageUri = getUriForFile(this,
                "com.semckinley.frcdeepspacescouting.fileprovider",
                mPhotoFile);

        photoIntent.putExtra(MediaStore.EXTRA_OUTPUT, robotImageUri);
        startActivityForResult(photoIntent, REQUEST_PHOTO);
}
    public File robotFile(){
        mTeamNumber = mEditNumber.getText().toString();
        File filesDir = this.getFilesDir();
        String robotPhotoFile = "RBT_" +  mTeamNumber + ".jpg";
        File photoFile = new File(filesDir, robotPhotoFile);

        return photoFile;

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(this.requestCode == requestCode && resultCode == RESULT_OK)
        {

               try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                           getContentResolver(), robotImageUri);
                   mRobotPhoto.setImageBitmap(bitmap);
               }
                   catch(Exception e){
                   e.printStackTrace();
                   }


        }
        //      String selected = mSpinner1.getItemAtPosition(i).toString();
    }

    public void submitAndNew(View view){
        String teamNumber = mEditNumber.getText().toString();
        if(robotImageUri !=null){
            ContentValues cv = new ContentValues();
            cv.put(RobotContract.RobotEntry.COLUMN_TEAM_NUMBER, mEditNumber.getText().toString());
            cv.put(RobotContract.RobotEntry.COLUMN_TEAM_NAME, mEditName.getText().toString());
            cv.put(RobotContract.RobotEntry.COLUMN_DRIVE, mDriveSpinner.getSelectedItem().toString());
            cv.put(RobotContract.RobotEntry.COLUMN_CARGO, mCargoSpinner.getSelectedItem().toString());
            cv.put(RobotContract.RobotEntry.COLUMN_HATCH, mHatchSpinner.getSelectedItem().toString());
            cv.put(RobotContract.RobotEntry.COLUMN_RELOAD, mReloadSpinner.getSelectedItem().toString());
            cv.put(RobotContract.RobotEntry.COLUMN_CAN_START, mCanStartSpinner.getSelectedItem().toString());
            cv.put(RobotContract.RobotEntry.COLUMN_CLIMB, mClimbSpinner.getSelectedItem().toString());
            cv.put(RobotContract.RobotEntry.COLUMN_PREF_START, mPrefStartSpinner.getSelectedItem().toString());
            cv.put(RobotContract.RobotEntry.COLUMN_SANDSTORM, mSandSpinner.getSelectedItem().toString());
            cv.put(RobotContract.RobotEntry.COLUMN_ROBOT_PRELOAD, mRobPreLoadSpinner.getSelectedItem().toString());
            cv.put(RobotContract.RobotEntry.COLUMN_POD_PRELOAD, mPodPreSpinner.getSelectedItem().toString());
            cv.put(RobotContract.RobotEntry.COLUMN_ROBOT_PHOTO_URI, robotImageUri.toString());
            mDb.insert(RobotContract.RobotEntry.TABLE_NAME, null, cv);
            String submit = "You Submitted Team " + mEditNumber.getText().toString();
            Toast.makeText(this, submit, Toast.LENGTH_LONG).show();
            ViewGroup viewGroup = (ViewGroup) findViewById(R.id.entry_activity);
            resetForm(viewGroup);

        }
    else {
            Toast.makeText(this, "YOU FORGOT TO TAKE THE PHOTO", Toast.LENGTH_LONG).show();
        }}

    public void submitAndView(View view){
        Intent listIntent= new Intent(this, TeamList.class);
        startActivity(listIntent);


    }

    public static void resetForm(ViewGroup group) {
        for (int i = 0, count = group.getChildCount(); i < count; ++i) {
            View view = group.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText) view).getText().clear();
            }
            if (view instanceof ImageView){
                ((ImageView) view).setImageDrawable(null);
            }

            if (view instanceof Spinner) {
                ((Spinner) view).setSelection(0);
            }

            if (view instanceof ViewGroup && (((ViewGroup) view).getChildCount() > 0))
                resetForm((ViewGroup) view);
        }

    }

}
