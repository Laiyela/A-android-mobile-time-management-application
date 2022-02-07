package com.example.selfdisciplinemaster.LoginAndRegister;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.selfdisciplinemaster.HomeActivity;
import com.example.selfdisciplinemaster.MainActivity;
import com.example.selfdisciplinemaster.R;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private DBOpenHelper mDBOpenHelper;
    private EditText et_email, et_Psw;
    private CheckBox cb_rmbPsw;
    private String userEmail;
    private Button btn_login;
    private TextView tv_register;
    private SharedPreferences.Editor editor;
    SharedPreferences sp2;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("CommitPrefEdits")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        setContentView(R.layout.activity_login);

        initView();
        mDBOpenHelper = new DBOpenHelper(this);



        SharedPreferences sp = getSharedPreferences("user_mes", MODE_PRIVATE);
        editor = sp.edit();
        if(sp.getBoolean("flag",false)){
            String email_read = sp.getString("user","");
            String psw_read = sp.getString("psw","");
            et_email.setText(email_read);
            et_Psw.setText(psw_read);
        }
       /* else {
            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(i);
        } */
    }




    private void initView() {
        et_email = findViewById(R.id.et_email);
        et_Psw = findViewById(R.id.et_Psw);
        cb_rmbPsw = findViewById(R.id.cb_rmbPsw);
        btn_login = findViewById(R.id.btn_Login);
        tv_register = findViewById(R.id.tv_Register);
        btn_login.setOnClickListener(this);
        tv_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_Register:
                Intent intent = new Intent(LoginActivity.this, RegisteredActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.btn_Login:
                String email = et_email.getText().toString().trim();
                String password = et_Psw.getText().toString().trim();
                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                    ArrayList<User> data = mDBOpenHelper.getAllData();
                    boolean match = false;
                    boolean match2 = false;
                    for (int i = 0; i < data.size(); i++) {
                        User user = data.get(i);
                        if ((email.equals(user.getEmail()) && password.equals(user.getPassword()))) {
                            email = user.getEmail();
                            match = true;
                            if(cb_rmbPsw.isChecked()){
                                editor.putBoolean("flag",true);
                                editor.putString("email",user.getEmail());
                                editor.putString("psw",user.getPassword());
                                editor.apply();
                                match2 = true;
                            }else {
                                editor.putString("email",user.getEmail());
                                editor.putString("psw","");
//                                editor.clear();
                                editor.apply();
                                match2 = false;
                            }
                            break;
                        } else {
                            match = false;
                        }
                    }
                    if (match) {
                        if(match2){
                            Toast.makeText(this, "Successfully remember password!", Toast.LENGTH_SHORT).show();
                            cb_rmbPsw.setChecked(true);
                        }
                        Toast.makeText(this, "Log in successfully!", Toast.LENGTH_SHORT).show();
                        Runnable target;

                        Thread thread = new Thread(){
                            @Override
                            public void run(){
                                try {
                                    sleep(2000);
                                    String uEmail = userEmail;
                                    Intent intent1 = new Intent(LoginActivity.this, HomeActivity.class);


                                    startActivity(intent1);
                                    finish();
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        };
                        thread.start();
                    } else {
                        Toast.makeText(this, "Your email or password is wrong, please try again!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Please enter your email and password", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

}