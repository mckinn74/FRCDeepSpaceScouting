package com.semckinley.frcdeepspacescouting;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.semckinley.frcdeepspacescouting.sampledata.RobotContract;

public class RobotAdapter extends RecyclerView.Adapter<RobotAdapter.RobotViewHolder> {

    Cursor mCursor;
    static Context mContext;
    //final private RobotTeamClickListener mOnClickListener;

    public RobotAdapter(Cursor cursor, Context context){
        mCursor = cursor;
        mContext = context;
        //mOnClickListener = listener;
    }

    public interface RobotTeamClickListener  {
        void onRobotClick(int clickedRobotIndex);
    }
    @Override
    public RobotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        int layoutIdForStudentItem = R.layout.robot_list;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForStudentItem, parent, shouldAttachToParentImmediately);
        RobotViewHolder viewHolder = new RobotViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RobotViewHolder holder, int position) {
        if(!mCursor.moveToPosition(position))
            return;

        String name = mCursor.getString(mCursor.getColumnIndex(RobotContract.RobotEntry.COLUMN_TEAM_NAME));
        String number = mCursor.getString(mCursor.getColumnIndex(RobotContract.RobotEntry.COLUMN_TEAM_NUMBER));
        String drive = mCursor.getString(mCursor.getColumnIndex(RobotContract.RobotEntry.COLUMN_DRIVE));
        String hatch = mCursor.getString(mCursor.getColumnIndex(RobotContract.RobotEntry.COLUMN_HATCH));
        String cargo = mCursor.getString(mCursor.getColumnIndex(RobotContract.RobotEntry.COLUMN_CARGO));
        String reload = mCursor.getString(mCursor.getColumnIndex(RobotContract.RobotEntry.COLUMN_RELOAD));


        holder.listRobotView.setText(number +"\n"
                 + name + "\n" + "DRIVE: " + drive + "\n" + "HATCH: " + hatch  +
        "\n" + "CARGO: " + cargo + "\n" + "RELOAD: " + reload);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public class RobotViewHolder extends RecyclerView.ViewHolder {

        TextView listRobotView;
        public RobotViewHolder(View itemView) {
            super(itemView);
            listRobotView = itemView.findViewById(R.id.tv_item_robot);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int adapterPosition = getAdapterPosition();
                    Context context = v.getContext();
                    mCursor.moveToPosition(adapterPosition);
                    String number = mCursor.getString(mCursor.getColumnIndex(RobotContract.RobotEntry.COLUMN_TEAM_NUMBER));

                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("team_number", number);
                    context.startActivity(intent);
                }
            });
        }

       /* @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();


            mOnClickListener.onRobotClick(adapterPosition);

        }*/
    }




    public void setCursor (Cursor mCursor){this.mCursor = mCursor;}
}
