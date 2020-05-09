package com.nordsgn.fitnessclubexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void OnClickOpenSchedule(View view) {
        //Open active with schedule
        Intent intent = new Intent(this, ScheduleActivity.class);
        startActivity(intent);
    }
}
