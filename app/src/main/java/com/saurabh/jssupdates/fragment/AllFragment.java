package com.saurabh.jssupdates.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.saurabh.jssupdates.All.AllAdapter;
import com.saurabh.jssupdates.All.AllObject;
import com.saurabh.jssupdates.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AllFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAllAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static AllFragment newInstance(){
        AllFragment fragment = new AllFragment();
        return fragment;
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all, container,false);

        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAllAdapter = new AllAdapter(getDataSet(),getContext());
        mRecyclerView.setAdapter(mAllAdapter);
        
        return view;
    }

    private ArrayList<AllObject> resultsAll = new ArrayList<>();
    private List<AllObject> getDataSet() {
        fetchData();
        return resultsAll;
    }

    private void fetchData() {
        DatabaseReference mNotice = FirebaseDatabase.getInstance().getReference().child("Notices").child("All");
        mNotice.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.exists())
                {
                    String sender = null;
                    String dateAndTime = null;
                    String department = null;
                    String message = null;
                    if(snapshot.child("Sender").getValue()!=null)
                        sender = snapshot.child("Sender").getValue().toString();
                    if(snapshot.child("Designation").getValue()!=null)
                        department = snapshot.child("Designation").getValue().toString();
                    if(snapshot.child("Date and Time").getValue()!=null)
                        dateAndTime = snapshot.child("Date and Time").getValue().toString();
                    if(snapshot.child("Message").getValue()!=null)
                        message = snapshot.child("Message").getValue().toString();
                    Log.e("Sender", sender);
                    Log.e("Designation", department);
                    Log.e("Date and Time", dateAndTime);
                    Log.e("Message", message);
                    if(sender  != null && department != null && dateAndTime  != null && message != null)
                    {
                        AllObject newMessage = new AllObject(message, sender, department, dateAndTime);
                        resultsAll.add(newMessage);
                        mAllAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
