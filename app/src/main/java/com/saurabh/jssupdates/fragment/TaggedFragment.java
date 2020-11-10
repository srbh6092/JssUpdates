package com.saurabh.jssupdates.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saurabh.jssupdates.R;
import androidx.fragment.app.Fragment;

public class TaggedFragment extends Fragment {
    public static TaggedFragment newInstance(){
        TaggedFragment fragment = new TaggedFragment();
        return fragment;
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tagged,container,false);
        return view;
    }
}
