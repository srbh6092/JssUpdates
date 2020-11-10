package com.saurabh.jssupdates.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saurabh.jssupdates.R;

import androidx.fragment.app.Fragment;

public class AllFragment extends Fragment {

    public static AllFragment newInstance(){
        AllFragment fragment = new AllFragment();
        return fragment;
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all, container,false);
        return view;
    }
}
