package com.example.basicappsample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.basicappsample.widget.SpringPositionImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Example #1 – Position");
        setContentView(R.layout.activity_spring_anim_position);
        final SpringPositionImageView imgDroid = findViewById(R.id.imgDroid);
        imgDroid.addEndListener(canceled -> Toast.makeText(MainActivity.this, "onAnimationEnd!", Toast.LENGTH_SHORT).show());
    }

    public void onClickAction(View view) {
        final Intent intent = new Intent(this, RotationSampleActivity.class);
        startActivity(intent);
    }
}
