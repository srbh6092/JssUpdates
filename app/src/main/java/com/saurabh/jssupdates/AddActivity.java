package com.saurabh.jssupdates;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
        String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference mCurrentUserDB= FirebaseDatabase.getInstance().getReference().child("Users").child(currentUser);
        mCurrentUserDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String name = null;
                    String designation = null;
                    String department = null;
                    if(snapshot.child("Name").getValue()!=null){
                        name = snapshot.child("Name").getValue().toString();
                        mName.setText(name);
                    }
                    if(snapshot.child("Designation").getValue()!=null){
                        designation = snapshot.child("Designation").getValue().toString();
                        mDesignation.setText(designation);
                    }
                    if(snapshot.child("Department").getValue()!=null){
                        department = snapshot.child("Department").getValue().toString();
                        mDepartment.setText(department);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        mUpload = findViewById(R.id.upload);
        mUpload.setOnClickListener(v -> {
            String key = FirebaseDatabase.getInstance().getReference().child("Notices").push().getKey();
            mCurrentUserDB.child("Uploads").child(key).setValue(true);
            DatabaseReference noticeUserDB =  FirebaseDatabase.getInstance().getReference().child("Notices").child(key);
            Map msgInfo = new HashMap();
            msgInfo.put("Sender ID",currentUser);
            msgInfo.put("Sender",mName.getText());
            msgInfo.put("Designation",mDesignation.getText()+" at "+mDepartment.getText());
            msgInfo.put("Message",mNotice.getText().toString());
            msgInfo.put("Date and Time",getDateTime());
            noticeUserDB.updateChildren(msgInfo);
            Toast.makeText(AddActivity.this,"Notice Uploaded",Toast.LENGTH_SHORT).show();
            finish();
        });

    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

}