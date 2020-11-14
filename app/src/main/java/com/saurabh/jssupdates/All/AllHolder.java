package com.saurabh.jssupdates.All;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.saurabh.jssupdates.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AllHolder extends RecyclerView.ViewHolder {
    public LinearLayout mContainer;
    public TextView mSender;
    public TextView mDepartment;
    public TextView mDateAndTime;
    public TextView mMessage;


    public AllHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener((View.OnClickListener) this);

        mContainer = itemView.findViewById(R.id.container);
        mSender = itemView.findViewById(R.id.sender);
        mDepartment = itemView.findViewById(R.id.department);
        mDateAndTime = itemView.findViewById(R.id.dateAndTime);
        mMessage = itemView.findViewById(R.id.message);

    }
}
