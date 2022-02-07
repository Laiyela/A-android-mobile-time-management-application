package com.example.selfdisciplinemaster.LoginAndRegister;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.selfdisciplinemaster.R;

public class RegisteredActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText et_rgsName, et_rgsEmail, et_rgsPsw;
    private Button btn_cancel, btn_register;
    private DBOpenHelper mDBOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        setContentView(R.layout.activity_registered);
        setTitle("User Register");

        initView();
        mDBOpenHelper = new DBOpenHelper(this);
    }

    private void initView() {
        et_rgsName = findViewById(R.id.et_name);
        et_rgsEmail = findViewById(R.id.et_email);
        et_rgsPsw = findViewById(R.id.et_Psw);

        btn_register = findViewById(R.id.btn_register);
        btn_cancel = findViewById(R.id.btn_cancel);

        btn_cancel.setOnClickListener(this);
        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel:
                Intent intent = new Intent(RegisteredActivity.this, LoginActivity.class);
                startActivity(intent);

                break;
            case R.id.btn_register:
                String username = et_rgsName.getText().toString().trim();
                String email = et_rgsEmail.getText().toString().trim();
                String password = et_rgsPsw.getText().toString().trim();
                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                        mDBOpenHelper.add(username, email, password);
                        Intent intent1 = new Intent(RegisteredActivity.this, LoginActivity.class);
                        startActivity(intent1);

                        Toast.makeText(this, "You registere successfully!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(this, "Incomplete information. Registration failed!", Toast.LENGTH_SHORT).show();
                }


                break;
        }
    }

}