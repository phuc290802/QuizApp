package com.example.appquiz.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.appquiz.R;

public class MyProfineActivity extends AppCompatActivity {

    private EditText name,email,phone;
    private LinearLayout editB;
    private Button cancelB,saveB;
    private TextView profileText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profine);


        name=findViewById(R.id.mp_name);
        email=findViewById(R.id.mp_email);
        phone=findViewById(R.id.mp_phone);
        profileText=findViewById(R.id.profile_text);
        cancelB=findViewById(R.id.cancelB);
        saveB=findViewById(R.id.saveB);

        disableediiting();
    }
    private void disableediiting()
    {
        name.setEnabled(false);
        email.setEnabled(false);
        phone.setEnabled(false);

        //
    }
}