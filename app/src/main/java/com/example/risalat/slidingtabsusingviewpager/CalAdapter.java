package com.example.risalat.slidingtabsusingviewpager;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class CalAdapter extends RecyclerView.Adapter<CalAdapter.CalViewHolder> {

    private Context mContext;
    private Cursor mCursor;
    public CalAdapter(Context context, Cursor cursor){

        mContext=context;
        mCursor= cursor;
    }
    public class CalViewHolder extends RecyclerView.ViewHolder{

        public TextView nameText;
        public CalViewHolder(View itemView) {
            super(itemView);

            nameText=itemView.findViewById(R.id.textview_name_item);
        }
    }

    @Override
    public CalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(mContext);
        View view=inflater.inflate(R.layout.cal_item,parent,false);
        return new CalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CalViewHolder holder, int position) {

        if(!mCursor.moveToPosition(position)){
            return;
        }

        String name=mCursor.getString(mCursor.getColumnIndex(CalContract.CalEntry.COLUMN_NAME));

        holder.nameText.setText(name);

    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor){
        if(mCursor != null){
            mCursor.close();
        }

        mCursor=newCursor;
        if (newCursor !=null){
            notifyDataSetChanged();
        }
    }
}
