package com.semckinley.frcdeepspacescouting;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView mTVLogo;
    Button mBTViewTeams;
    Button mBTViewSingle;
    EditText mETTeamNumber;
    Button mBTAddTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTVLogo = (TextView) findViewById(R.id.tv_logo);
        mBTAddTeam = (Button) findViewById(R.id.bt_new_team);

        mBTViewTeams = (Button) findViewById(R.id.bt_view_all);
        mBTViewSingle = (Button) findViewById(R.id.bt_view_one);
        mETTeamNumber = (EditText) findViewById(R.id.et_team_nmbr);

    }
    public void addNewTeam(View view){
        Intent intent = new Intent(MainActivity.this, EntryActivity.class);
        startActivity(intent);
    }

    public void viewAll(View view){
        Intent intent = new Intent (MainActivity.this, TeamList.class);
        startActivity(intent);
    }

    public void viewOne(View view){
        String number = mETTeamNumber.getText().toString();
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("team_number", number);
        startActivity(intent);
    }

}
