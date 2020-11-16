package com.saurabh.jssupdates;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView mSender;
    public TextView mDepartment;
    public TextView mDateAndTime;
    public TextView mMessage;


    public MeHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener((View.OnClickListener) this);

        mSender = itemView.findViewById(R.id.sender);//initializing the sender in item
        mDepartment = itemView.findViewById(R.id.department);//initializing the department in item
        mDateAndTime = itemView.findViewById(R.id.dateAndTime);//initializing the dateAndTime in item
        mMessage = itemView.findViewById(R.id.message);//initializing the message in item

    }

    @Override
    public void onClick(View v) {
    }
}
