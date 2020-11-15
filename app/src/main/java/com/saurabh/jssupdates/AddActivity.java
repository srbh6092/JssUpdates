package com.saurabh.jssupdates;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
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
    CheckBox mPrincipal, mRegistrar, mHoD, mProfessor, mLabAssistant, mStudent;
    EditText mNotice;
    Button mUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        mName = findViewById(R.id.name);

        mPrincipal = (CheckBox)findViewById(R.id.principal);
        mRegistrar = (CheckBox)findViewById(R.id.registrar);
        mHoD = (CheckBox)findViewById(R.id.hod);
        mProfessor = (CheckBox)findViewById(R.id.professor);
        mLabAssistant = (CheckBox)findViewById(R.id.labAssistant);
        mStudent = (CheckBox)findViewById(R.id.student);

        mDesignation = findViewById(R.id.designation);
        mDepartment = findViewById(R.id.department);

        mNotice = findViewById(R.id.notice);
        String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();//getting User ID of current user
        DatabaseReference mCurrentUserDB= FirebaseDatabase.getInstance().getReference().child("Users").child(currentUser);//creating a reference to current user's databse
        mCurrentUserDB.addListenerForSingleValueEvent(new ValueEventListener() {//listening for data
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){//if data is found
                    String name = null;
                    String designation = null;
                    String department = null;
                    if(snapshot.child("Name").getValue()!=null){//if name has some value
                        name = snapshot.child("Name").getValue().toString();//store name
                        mName.setText(name);//set name on screen
                    }
                    if(snapshot.child("Designation").getValue()!=null){//if designation has some value
                        designation = snapshot.child("Designation").getValue().toString();//store designation
                        mDesignation.setText(designation);//set designation on screen
                    }
                    if(snapshot.child("Department").getValue()!=null){//if department has some value
                        department = snapshot.child("Department").getValue().toString();///store department
                        mDepartment.setText(department);//set department on screen
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        mUpload = findViewById(R.id.upload);
        mUpload.setOnClickListener(v -> {//if upload button is clicked
            String key = FirebaseDatabase.getInstance().getReference().child("Notices").push().getKey();//make a unique key for the notice in database
            DatabaseReference userDB = FirebaseDatabase.getInstance().getReference().child("Users");//creating a reference to users database
            if(mPrincipal.isChecked()){//if principal box is checked
                userDB.addChildEventListener(new ChildEventListener() {//search all child in user database
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        if(snapshot.exists())//if child is found
                        {
                            if(snapshot.child("Designation").getValue().equals("Principal")){//if child's designation is principal
                                DatabaseReference mReceiverDB= FirebaseDatabase.getInstance().getReference().child("Users").child(snapshot.getKey());//create reference to child's database
                                mReceiverDB.child("Tagged").child(key).setValue(true);//set tagged notice ID in child's database
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

            if(mRegistrar.isChecked()){//if registrar box is checked
                userDB.addChildEventListener(new ChildEventListener() {//search all child in user database
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        if(snapshot.exists())//if child is found
                        {
                            if(snapshot.child("Designation").getValue().equals("Registrar")){//if child's designation is registrar
                                DatabaseReference mReceiverDB= FirebaseDatabase.getInstance().getReference().child("Users").child(snapshot.getKey());//create reference to child's database
                                mReceiverDB.child("Tagged").child(key).setValue(true);//set tagged notice ID in child's database
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

            if(mHoD.isChecked()){//if HoD box is checked
                userDB.addChildEventListener(new ChildEventListener() {//search all child in user database
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        if(snapshot.exists())//if child is found
                        {
                            if(snapshot.child("Designation").getValue().equals("HoD")){//if child's designation is HoD
                                DatabaseReference mReceiverDB= FirebaseDatabase.getInstance().getReference().child("Users").child(snapshot.getKey());//create reference to child's database
                                mReceiverDB.child("Tagged").child(key).setValue(true);//set tagged notice ID in child's database
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

            if(mProfessor.isChecked()){//if professor box is checked
                userDB.addChildEventListener(new ChildEventListener() {//search all child in user database
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        if(snapshot.exists())//if child is found
                        {
                            if(snapshot.child("Designation").getValue().equals("Professor")){//if child's designation is professor
                                DatabaseReference mReceiverDB= FirebaseDatabase.getInstance().getReference().child("Users").child(snapshot.getKey());//create reference to child's database
                                mReceiverDB.child("Tagged").child(key).setValue(true);//set tagged notice ID in child's database
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

            if(mLabAssistant.isChecked()){//if lab assistant box is checked
                userDB.addChildEventListener(new ChildEventListener() {//search all child in user database
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        if(snapshot.exists())//if child is found
                        {
                            if(snapshot.child("Designation").getValue().equals("Lab Assistant")){//if child's designation is lab assistant
                                DatabaseReference mReceiverDB= FirebaseDatabase.getInstance().getReference().child("Users").child(snapshot.getKey());//create reference to child's database
                                mReceiverDB.child("Tagged").child(key).setValue(true);//set tagged notice ID in child's database
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

            if(mStudent.isChecked()){//if student box is checked
                userDB.addChildEventListener(new ChildEventListener() {//search all child in user database
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        if(snapshot.exists())//if child is found
                        {
                            if(snapshot.child("Designation").getValue().equals("Student")){//if child's designation is student
                                DatabaseReference mReceiverDB= FirebaseDatabase.getInstance().getReference().child("Users").child(snapshot.getKey());//create reference to child's database
                                mReceiverDB.child("Tagged").child(key).setValue(true);//set tagged notice ID in child's database
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

            mCurrentUserDB.child("Uploads").child(key).setValue(true);//set notice ID in current user's upload database
            DatabaseReference noticeUserDB =  FirebaseDatabase.getInstance().getReference().child("Notices").child(key);//create reference to notices in database
            Map msgInfo = new HashMap();
            msgInfo.put("Sender ID",currentUser);//put sender id in map
            msgInfo.put("Sender",mName.getText());//put sender's name in map
            msgInfo.put("Designation",mDesignation.getText()+" at "+mDepartment.getText());//put senders department in map
            msgInfo.put("Message",mNotice.getText().toString());//put message in map
            msgInfo.put("Date and Time",getDateTime());//put date and time in map
            noticeUserDB.updateChildren(msgInfo);//upload the info in the map to the above database
            Toast.makeText(AddActivity.this,"Notice Uploaded",Toast.LENGTH_SHORT).show();//send a msg that notice was uploaded
            finish();//go back to me page
        });

    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//set a date and time format
        Date date = new Date();//get current date and time
        return dateFormat.format(date);//return the date and time
    }

}