package com.saurabh.jssupdates;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.saurabh.jssupdates.R;

public class AddActivity extends AppCompatActivity {

    TextView mName, mDesignation, mDepartment;
    EditText mNotice;
    Button mUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        mName = findViewById(R.id.name);
        mDesignation = findViewById(R.id.designation);
        mDepartment = findViewById(R.id.department);

        mNotice = findViewById(R.id.notice);

        mUpload = findViewById(R.id.upload);

    }
}