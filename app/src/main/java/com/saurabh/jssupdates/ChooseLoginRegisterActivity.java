package com.saurabh.jssupdates;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseLoginRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_login_register);


        Button mLogin = (Button)findViewById(R.id.login);
        Button mRegisterStudent = (Button)findViewById(R.id.registerStudent);
        Button mRegisterFaculty = (Button)findViewById(R.id.registerFaculty);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseLoginRegisterActivity.this,LoginActivity.class);//Going to login page
                startActivity(intent);
                return;
            }
        });
        mRegisterStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseLoginRegisterActivity.this, RegisterStudentActivity.class);//Going to register page
                startActivity(intent);
                return;
            }
        });
        mRegisterFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseLoginRegisterActivity.this, RegisterFacultyActivity.class);//Going to register page
                startActivity(intent);
                return;
            }
        });
    }
}