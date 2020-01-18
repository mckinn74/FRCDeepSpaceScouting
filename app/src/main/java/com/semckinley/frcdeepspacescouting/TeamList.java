package com.semckinley.frcdeepspacescouting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.semckinley.frcdeepspacescouting.sampledata.RobotContract;
import com.semckinley.frcdeepspacescouting.sampledata.RobotDbHelper;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class TeamList extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    SQLiteDatabase mDb;
    static Cursor mCursor;
    RobotDbHelper mRobotDbHelper;
    private RobotAdapter mAdapter;
    private RecyclerView mRobotList;
    public String mRobotResults;
    Context mContext;
    GridLayoutManager layoutManager;
    Button mDelete;

    SharedPreferences mPreference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_list);
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar !=null){
            actionBar.setDisplayUseLogoEnabled(true);
        }
        mContext = getBaseContext();
        mDelete = (Button) findViewById(R.id.bt_delete);
       // mErrorMessage = (TextView) findViewById(R.id.tv_error_message);


        mRobotList = (RecyclerView) findViewById(R.id.rv_robots);
        layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL,false );
        mRobotList.setLayoutManager(layoutManager);

        mRobotList.setHasFixedSize(false);
        mAdapter = new RobotAdapter(mCursor, mContext);

        mRobotList.setAdapter(mAdapter);
        mPreference = getDefaultSharedPreferences(this);
        mPreference.registerOnSharedPreferenceChangeListener(this);
        mRobotDbHelper = new RobotDbHelper(mContext);


         mDb = mRobotDbHelper.getReadableDatabase();
        mCursor = mDb.query(RobotContract.RobotEntry.TABLE_NAME, null, null, null, null, null,
                RobotContract.RobotEntry.COLUMN_TEAM_NUMBER);
        mAdapter = new RobotAdapter(mCursor,  mContext);
        mRobotList.setAdapter(mAdapter);

    }
    public void deleteDB(View view){
        mDb.delete(RobotContract.RobotEntry.TABLE_NAME, null, null);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        //mMovieList.removeAllViews();
        if(id==R.id.search_settings){
            Intent startSettingsActivity = new Intent(this, SettingsActivity.class);
            startActivity(startSettingsActivity);
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals(getString(R.string.search_option))){
            makeRobotQuery();
        }
    }

    public void makeRobotQuery(){
        int searchThing =Integer.parseInt(mPreference.getString("search_option", "3"));
        Resources res = getResources();
        String [] climbArray = res.getStringArray(R.array.Climb);
        String [] reloadArray = res.getStringArray(R.array.Reload);
        String [] startArray = res.getStringArray(R.array.Can_Start);
        String [] cargoArray = res.getStringArray(R.array.Cargo);
        String [] hatchArray = res.getStringArray(R.array.Hatches);
        String column = "*";
        String[] team= new String[1];
        switch (searchThing){
            case 1:
                mCursor = mDb.query(RobotContract.RobotEntry.TABLE_NAME, null, null, null, null, null,
                        null);
                mAdapter.setCursor(mCursor);
                mAdapter.notifyDataSetChanged();
                    return;

            case 2: column = RobotContract.RobotEntry.COLUMN_CLIMB;
                    team[0] = climbArray[2];
                    break;
            case 3: column = RobotContract.RobotEntry.COLUMN_CLIMB;
                    team[0] = climbArray[3];
                    break;
            case 4: column = RobotContract.RobotEntry.COLUMN_RELOAD;
                    team[0] = reloadArray[3];
                    break;
            case 5: column = RobotContract.RobotEntry.COLUMN_CAN_START;
                    team[0] = startArray[2];
                    break;
            case 6: column= RobotContract.RobotEntry.COLUMN_CARGO;
                    team[0] = cargoArray[4];
                    break;

            case 7: column= RobotContract.RobotEntry.COLUMN_HATCH;
                    team[0]=hatchArray[4];
                    break;
        }


        String whereClause = column + " = ?";
        mCursor = mDb.query(RobotContract.RobotEntry.TABLE_NAME, null, whereClause, team, null, null,
                null);
        mAdapter.setCursor(mCursor);
        mAdapter.notifyDataSetChanged();
    }
}
