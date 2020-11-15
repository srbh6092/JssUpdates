package com.saurabh.jssupdates.Tagged;

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

public class TaggedAdapter extends RecyclerView.Adapter<NoticeHolder> {

    private List<NoticeObject> taggedList;
    private Context context;

    public TaggedAdapter(List<NoticeObject> taggedList, Context context) {
        this.taggedList = taggedList;
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
        holder.mSender.setText(taggedList.get(position).getSender());//putting sender on the item holder
        holder.mDepartment.setText(taggedList.get(position).getDepartment());//putting department on the item holder
        holder.mDateAndTime.setText(taggedList.get(position).getDateAndTime());//putting dat and time of upload on the item holder
        holder.mMessage.setText(taggedList.get(position).getMessage());//putting the message on the item holder

    }

    @Override
    public int getItemCount() {
        Log.e("tagged", String.valueOf(taggedList.size()));
        return taggedList.size();//returning the size of adapter
    }
}
