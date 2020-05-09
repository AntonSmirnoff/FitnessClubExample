package com.nordsgn.fitnessclubexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.nordsgn.fitnessclubexample.adapters.TrainigAdapter;
import com.nordsgn.fitnessclubexample.data.MainViewModel;
import com.nordsgn.fitnessclubexample.data.ScheduleDatabase;
import com.nordsgn.fitnessclubexample.data.ScheduleEntry;
import com.nordsgn.fitnessclubexample.utils.JSONUtils;
import com.nordsgn.fitnessclubexample.utils.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity {

    //создаем ссылку на наш recyclerView
    private RecyclerView recyclerViewSchedule;
    //Создаем адаптер
    private TrainigAdapter trainigAdapter;

    //
    private MainViewModel viewModel;
    //получим объект базы данных
    private ScheduleDatabase database;


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
        setContentView(R.layout.activity_schedule);
        //присваиваем значение recyclerView
        recyclerViewSchedule = findViewById(R.id.recyclerViewSchedule);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        //присваиваем значение нашей базе данных
       // database =

        //укажем как располагать элементы в recyclerView, если сеткой то нужно GridLayoutManager
        recyclerViewSchedule.setLayoutManager(new LinearLayoutManager(this));
        //присваиваем значение нашему адаптеру
        trainigAdapter = new TrainigAdapter();

        LiveData<List<ScheduleEntry>> trainingFromLiveDate = viewModel.getTrainings();
        trainingFromLiveDate.observe(this, new Observer<List<ScheduleEntry>>() {
            @Override
            public void onChanged(List<ScheduleEntry> scheduleEntries) {
                trainigAdapter.setScheduleEntries(scheduleEntries);
                recyclerViewSchedule.setAdapter(trainigAdapter);
            }
        });


        //загружаем данные
        downloadDate();

    }

    private void downloadDate(){
        //получаем наше расписание
        JSONArray jsonArray = NetworkUtils.GetJSONArray();
        //затем получаем расписание ввиде списка
        ArrayList<ScheduleEntry> scheduleEntries = JSONUtils.getScheduleFromJSON(jsonArray);
        if(scheduleEntries != null && !scheduleEntries.isEmpty())
        {
            viewModel.deleteAllSchedule();
            for (ScheduleEntry scheduleEntry : scheduleEntries){
                viewModel.insertSchedule(scheduleEntry);
            }
        }
    }
}
