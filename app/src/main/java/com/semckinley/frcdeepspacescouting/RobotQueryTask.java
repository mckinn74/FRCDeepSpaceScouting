package com.semckinley.frcdeepspacescouting;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.semckinley.frcdeepspacescouting.sampledata.RobotContract;

public class RobotQueryTask extends CursorLoader {
    Context sContext;
    public RobotQueryTask(@NonNull Context context) {
        super(context);
        sContext = context;
    }

    @Nullable
    @Override
    public Cursor loadInBackground() {
        try {

            return sContext.getContentResolver().query(RobotContract.RobotEntry.CONTENT_URI,
                    null,
                    null,
                    null,
                    RobotContract.RobotEntry.COLUMN_TEAM_NAME);

        } catch (Exception e) {
            //Log.e(TAG, "Failed to asynchronously load data.");
            e.printStackTrace();
            return null;
        }

    }
}
