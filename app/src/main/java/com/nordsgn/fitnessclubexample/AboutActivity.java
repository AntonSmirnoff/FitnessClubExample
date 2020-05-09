package com.nordsgn.fitnessclubexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class AboutActivity extends AppCompatActivity {

    //переопределяем метод для создания меню ctrl+o
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    //для реагирование на нажатие на пункты меню переопределяем метод
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // получаем ID
        int id = item.getItemId();
        // и исходя из полученного  id делаем действие
        switch (id) {
            case R.id.itemSchedule:
                Intent intentSchedule = new Intent(this, ScheduleActivity.class);
                startActivity(intentSchedule);
                break;
            case R.id.itemAboutProgramm:
                Intent intentAboutProgramm = new Intent(this, AboutActivity.class);
                startActivity(intentAboutProgramm);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
}
