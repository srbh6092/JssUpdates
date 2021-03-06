package com.saurabh.jssupdates.All;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saurabh.jssupdates.NoticeHolder;
import com.saurabh.jssupdates.NoticeObject;
import com.saurabh.jssupdates.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AllAdapter extends RecyclerView.Adapter<NoticeHolder> {

    private List<NoticeObject> allList;
    private Context context;

    public AllAdapter(List<NoticeObject> allList, Context context) {
        this.allList = allList;
        this.context = context;
    }

    @NonNull
    @Override
    public NoticeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notice, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutView.setLayoutParams(lp);
        NoticeHolder rcv = new NoticeHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeHolder holder, int position) {
        holder.mSender.setText(allList.get(position).getSender());//putting sender on the item holder
        holder.mDepartment.setText(allList.get(position).getDepartment());//putting department on the item holder
        holder.mDateAndTime.setText(allList.get(position).getDateAndTime());//putting dat and time of upload on the item holder
        holder.mMessage.setText(allList.get(position).getMessage());//putting the message on the item holder

    }

    @Override
    public int getItemCount() {
        Log.e("Item", String.valueOf(allList.size()));
        return allList.size();//returning the size of adapter
    }
}
