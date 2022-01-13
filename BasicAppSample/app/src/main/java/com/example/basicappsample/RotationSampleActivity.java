package com.example.basicappsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class RotationSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotation_sample);
    }

    public void onClickAction(View view) {
        Snackbar.make(view, "does not implement it yet.", Snackbar.LENGTH_SHORT).show();
    }
}