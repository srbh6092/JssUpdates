package com.saurabh.jssupdates;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.CodeSigner;
import java.util.HashMap;
import java.util.Map;

public class RegisterFacultyActivity extends AppCompatActivity {

    private EditText mName, mId, mEmail, mPassword, mConfirmPassword;
    private RadioGroup mBranch, mDesignation;
    private Button mRegister;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_faculty);

        mAuth = FirebaseAuth.getInstance();
        firebaseAuthStateListener = firebaseAuth -> {

            final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if(user!=null)//if a user is already logged in
            {
                Intent intent = new Intent(RegisterFacultyActivity.this,MainActivity.class);//Going to main page
                startActivity(intent);
                finish();
                return;
            }
        };

        mName = (EditText) findViewById(R.id.name);//initializing name for input
        mId = (EditText) findViewById(R.id.id);//initializing id for input
        mEmail = (EditText) findViewById(R.id.email);//initializing email for input
        mPassword = (EditText) findViewById(R.id.password);//initializing password for input
        mConfirmPassword = (EditText) findViewById(R.id.confirmPassword);//initializing password one more time to confirm for input

        mBranch = (RadioGroup) findViewById(R.id.branch);//initializing branch for input
        mDesignation = (RadioGroup) findViewById(R.id.designation);//initializing designation for input

        mRegister = findViewById(R.id.register);
        mRegister.setOnClickListener(v -> {

            final RadioButton mRadioBranch = (RadioButton) findViewById(mBranch.getCheckedRadioButtonId());//initializing checked branch for input
            final RadioButton mRadioDesignation = (RadioButton) findViewById(mDesignation.getCheckedRadioButtonId());//initializing checked designation for input
            final String email = mEmail.getText().toString();//fetching email from input
            final String password = mPassword.getText().toString();//fetching password from input
            final String name = mName.getText().toString();//fetching name from input
            final String id = mId.getText().toString();//fetching name from input
            final String confirmPassword = mConfirmPassword.getText().toString();//fetching password one more time to confirm from input
            final String branch, designation;

            if(password.equals(confirmPassword)) {
                if (mRadioBranch.getText() == null||mRadioDesignation.getText() == null)//if radio groups are empty
                    return;
                else
                {
                    branch = mRadioBranch.getText().toString();
                    designation = mRadioDesignation.getText().toString();
                }

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterFacultyActivity.this, task -> {
                    if (!task.isSuccessful())//if an error occurs like.. user exists!
                        Toast.makeText(RegisterFacultyActivity.this, "Registration Error!", Toast.LENGTH_SHORT).show();
                    else {
                        final String userID = mAuth.getCurrentUser().getUid();
                        DatabaseReference currentUserDB = FirebaseDatabase.getInstance().getReference().child("Users").child("Faculty").child(branch).child(designation).child(userID);
                        Map userInfo = new HashMap<>();
                        userInfo.put("ID", id);
                        userInfo.put("Department", branch);
                        userInfo.put("Designation", designation);
                        userInfo.put("Name", name);
                        userInfo.put("Email", email);
                        currentUserDB.updateChildren(userInfo);//Uploading info to the database

                    }
                });
            }
            else if(!password.equals(confirmPassword))//if password doesn't match
                Toast.makeText(RegisterFacultyActivity.this, "Password don't match!", Toast.LENGTH_SHORT).show();
            else if(email==null||password==null||id==null||name==null||confirmPassword==null)//if any field is empty
                Toast.makeText(RegisterFacultyActivity.this, "Fill every detail!", Toast.LENGTH_SHORT).show();

        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthStateListener);//report the authentication state at start
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthStateListener);//report the authentication state at end
    }

}