package com.saurabh.jssupdates;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.saurabh.jssupdates.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoticeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView mSender;
    public TextView mDepartment;
    public TextView mDateAndTime;
    public TextView mMessage;


    public NoticeHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener((View.OnClickListener) this);

        mSender = itemView.findViewById(R.id.sender);
        mDepartment = itemView.findViewById(R.id.department);
        mDateAndTime = itemView.findViewById(R.id.dateAndTime);
        mMessage = itemView.findViewById(R.id.message);

    }

    @Override
    public void onClick(View v) {
    }
}
