package com.saurabh.jssupdates.Me;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.saurabh.jssupdates.MeHolder;
import com.saurabh.jssupdates.NoticeHolder;
import com.saurabh.jssupdates.NoticeObject;
import com.saurabh.jssupdates.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MeAdapter extends RecyclerView.Adapter<MeHolder>{

    private List<NoticeObject> meList;
    private Context context;

    public MeAdapter(List<NoticeObject> meList, Context context) {
        this.meList = meList;
        this.context = context;
    }

    @NonNull
    @Override
    public MeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notice, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutView.setLayoutParams(lp);
        MeHolder rcv = new MeHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(@NonNull MeHolder holder, int position) {
        holder.mSender.setText(meList.get(position).getSender());//putting sender on the item holder
        holder.mDepartment.setText(meList.get(position).getDepartment());//putting department on the item holder
        holder.mDateAndTime.setText(meList.get(position).getDateAndTime());//putting dat and time of upload on the item holder
        holder.mMessage.setText(meList.get(position).getMessage());//putting the message on the item holder
    }

    @Override
    public int getItemCount() {
        Log.e("Me Item", String.valueOf(meList.size()));
        return meList.size();//returning the size of adapter
    }
}
