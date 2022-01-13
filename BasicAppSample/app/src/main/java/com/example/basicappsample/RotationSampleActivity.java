package com.example.basicappsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.basicappsample.widget.SpringRotationView;

public class RotationSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotation_sample);
        final SpringRotationView imgDroid = findViewById(R.id.imgDroid);
        imgDroid.addEndListener(canceled -> Toast.makeText(RotationSampleActivity.this, "onAnimationEnd!", Toast.LENGTH_SHORT).show());
    }
}