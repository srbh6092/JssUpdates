package com.saurabh.jssupdates.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.saurabh.jssupdates.All.AllAdapter;
import com.saurabh.jssupdates.NoticeObject;
import com.saurabh.jssupdates.R;

import java.util.ArrayList;
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

        mRecyclerView = view.findViewById(R.id.recyclerView);//initializing recyclerView
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        ((LinearLayoutManager) mLayoutManager).setReverseLayout(true);
        ((LinearLayoutManager) mLayoutManager).setStackFromEnd(true);
        mRecyclerView.setLayoutManager(mLayoutManager);//setting linear layout to recyclerView
        mAllAdapter = new AllAdapter(getDataSet(),getContext());//Creating an adapter
        mRecyclerView.setAdapter(mAllAdapter);//putting adapter in recyclerView
        
        return view;
    }

    private ArrayList<NoticeObject> resultsAll = new ArrayList<>();//Creating list for all notices
    private List<NoticeObject> getDataSet() {
        fetchData();//calling method to fetch all notices from  database
        return resultsAll;//returning the list of all notices
    }

    private void fetchData() {
        resultsAll.clear();//clearing the list
        DatabaseReference mNotice = FirebaseDatabase.getInstance().getReference().child("Notices");//Creating a reference in database from where the notices are to be extracted
        mNotice.addChildEventListener(new ChildEventListener() {//fetching child of the database reference
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.exists())//If there is one more data
                {
                    String sender = null;//initializing sender
                    String dateAndTime = null;//initializing dateAndTime
                    String department = null;//initializing department
                    String message = null;//initializing message
                    if(snapshot.child("Sender").getValue()!=null)//if sender has some value in database
                        sender = snapshot.child("Sender").getValue().toString();//storing sender id
                    if(snapshot.child("Designation").getValue()!=null)//if designation has some value in database
                        department = snapshot.child("Designation").getValue().toString();//storing designation of the sender
                    if(snapshot.child("Date and Time").getValue()!=null)//if date and time of upload has some value in database
                        dateAndTime = snapshot.child("Date and Time").getValue().toString();//storing data and time of upload
                    if(snapshot.child("Message").getValue()!=null)//if uploaded message has some value in database
                        message = snapshot.child("Message").getValue().toString();//storing message
                    if(sender  != null && department != null && dateAndTime  != null && message != null)//only if all above stored data has some value
                    {
                        NoticeObject newMessage = new NoticeObject(message, sender, department, dateAndTime);//create an object with the stored data
                        resultsAll.add(newMessage);//add the object as an item to the list
                        mAllAdapter.notifyDataSetChanged();//notify the adapter that the data in the list has changed
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
