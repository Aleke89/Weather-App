package com.example.instructionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.instructionapp.R;

public class DefaultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);
    }
}