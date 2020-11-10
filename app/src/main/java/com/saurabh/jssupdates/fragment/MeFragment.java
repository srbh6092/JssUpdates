package com.saurabh.jssupdates.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.saurabh.jssupdates.AddActivity;
import com.saurabh.jssupdates.R;

import androidx.fragment.app.Fragment;

public class MeFragment extends Fragment {


    public static MeFragment newInstance(){
        MeFragment fragment = new MeFragment();
        return fragment;
    }

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me,container,false);
        FloatingActionButton mAdd = (FloatingActionButton) view.findViewById(R.id.add);
        mAdd.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddActivity.class);
            startActivity(intent);
        });
        return view;
    }
}
