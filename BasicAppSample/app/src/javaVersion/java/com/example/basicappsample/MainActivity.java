package com.example.basicappsample;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.basicappsample.helpers.AnimationHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textHello = findViewById(R.id.text_hello);
        textHello.setText("Hello, Android Java");
        AnimationHelper.startAlphaAnimation(textHello);
    }
}
