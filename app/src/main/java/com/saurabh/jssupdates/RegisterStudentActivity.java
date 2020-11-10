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

import java.util.HashMap;
import java.util.Map;

public class RegisterStudentActivity extends AppCompatActivity {

    private EditText mName, mAdm, mRoll, mEmail, mPassword, mConfirmPassword;
    private RadioGroup mBranch, mSection ,mSemester;
    private Button mRegister;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_student);

        mAuth = FirebaseAuth.getInstance();
        firebaseAuthStateListener = firebaseAuth -> {

            final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if(user!=null)//if a user is already logged in
            {
                Intent intent = new Intent(RegisterStudentActivity.this,MainActivity.class);//Going to main page
                startActivity(intent);
                finish();
                return;
            }
        };

        mName = (EditText) findViewById(R.id.name);//initializing name for input
        mEmail = (EditText) findViewById(R.id.email);//initializing email for input
        mAdm = (EditText) findViewById(R.id.admissionNo);//initializing admission no for input
        mRoll = (EditText) findViewById(R.id.rollNo);//initializing roll no for input
        mPassword = (EditText) findViewById(R.id.password);//initializing password for input
        mConfirmPassword = (EditText) findViewById(R.id.confirmPassword);//initializing password one more time to confirm for input

        mBranch = (RadioGroup) findViewById(R.id.branch);//initializing branch for input
        mSection = (RadioGroup) findViewById(R.id.section);//initializing section for input
        mSemester = (RadioGroup) findViewById(R.id.semester);//initializing semester for input

        mRegister = findViewById(R.id.register);
        mRegister.setOnClickListener(v -> {

            final RadioButton mRadioBranch = (RadioButton) findViewById(mBranch.getCheckedRadioButtonId());//initializing checked branch for input
            final RadioButton mRadioSection = (RadioButton) findViewById(mSection.getCheckedRadioButtonId());//initializing checked branch for input
            final RadioButton mRadioSemester = (RadioButton) findViewById(mSemester.getCheckedRadioButtonId());//initializing checked branch for input
            final String email = mEmail.getText().toString();//fetching email from input
            final String password = mPassword.getText().toString();//fetching password from input
            final String roll = mRoll.getText().toString();//fetching roll no from input
            final String adm = mAdm.getText().toString();//fetching admission no from input
            final String name = mName.getText().toString();//fetching name from input
            final String confirmPassword = mConfirmPassword.getText().toString();//fetching password one more time to confirm from input
            final String branch, semester;

            if(password.equals(confirmPassword)) {
                if (mRadioBranch.getText() == null||mRadioSemester.getText() == null||mRadioSection.getText() == null)//if radio groups are empty
                    return;

                else
                {
                    branch = mRadioBranch.getText().toString();
                    semester = mRadioSemester.getText().toString();
                }

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterStudentActivity.this, task -> {
                    if (!task.isSuccessful())//if an error occurs like.. user exists!
                        Toast.makeText(RegisterStudentActivity.this, "Registration Error!", Toast.LENGTH_SHORT).show();
                    else {
                        final String userID = mAuth.getCurrentUser().getUid();
                        DatabaseReference currentUserDB = FirebaseDatabase.getInstance().getReference().child("Users").child("Student").child(branch).child(semester).child(userID);
                        Map userInfo = new HashMap<>();
                        userInfo.put("Roll No.", roll);
                        userInfo.put("Department", branch);
                        userInfo.put("Designation","Student");
                        userInfo.put("Semester",semester);
                        userInfo.put("Name", name);
                        userInfo.put("Email", email);
                        userInfo.put("Adm No",adm);
                        currentUserDB.updateChildren(userInfo);//Uploading info to the database
                    }
                });
            }
            else if(!password.equals(confirmPassword))//if password doesn't match
                Toast.makeText(RegisterStudentActivity.this, "Password don't match!", Toast.LENGTH_SHORT).show();
            else if(email==null||password==null||roll==null||adm==null||name==null||confirmPassword==null)//if any field is empty
                Toast.makeText(RegisterStudentActivity.this, "Fill every detail!", Toast.LENGTH_SHORT).show();

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