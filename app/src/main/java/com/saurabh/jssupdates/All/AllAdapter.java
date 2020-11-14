package com.saurabh.jssupdates.All;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saurabh.jssupdates.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AllAdapter extends RecyclerView.Adapter<AllHolder> {

    private List<AllObject> allList;
    private Context context;

    public AllAdapter(List<AllObject> allList, Context context) {
        this.allList = allList;
        this.context = context;
    }

    @NonNull
    @Override
    public AllHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notice, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutView.setLayoutParams(lp);
        AllHolder rcv = new AllHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(@NonNull AllHolder holder, int position) {
        holder.mSender.setText(allList.get(position).getSender());
        holder.mDepartment.setText(allList.get(position).getDepartment());
        holder.mDateAndTime.setText(allList.get(position).getDateAndTime());
        holder.mMessage.setText(allList.get(position).getMessage());

    }

    @Override
    public int getItemCount() {
        Log.e("Item", String.valueOf(allList.size()));
        return allList.size();
    }
}
