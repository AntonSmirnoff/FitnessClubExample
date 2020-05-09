package com.nordsgn.fitnessclubexample.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private static ScheduleDatabase database;

    public LiveData<List<ScheduleEntry>> getTrainings() {
        return trainings;
    }

    private LiveData<List<ScheduleEntry>> trainings;


    public MainViewModel(@NonNull Application application) {
        super(application);
        database = ScheduleDatabase.getInstance(getApplication());
        trainings = database.scheduleDAO().getAllSchedule();
    }

    public void deleteAllSchedule() {
        new deleteAllScheduleTask().execute();
    }

    public void insertSchedule(ScheduleEntry scheduleEntry) {
        new insertScheduleTask().execute(scheduleEntry);
    }

    private static class insertScheduleTask extends AsyncTask<ScheduleEntry, Void, Void> {
        @Override
        protected Void doInBackground(ScheduleEntry... scheduleEntries) {
            if (scheduleEntries != null && scheduleEntries.length > 0) {
                database.scheduleDAO().insertSchedule(scheduleEntries[0]);
            }
            return null;
        }
    }


    private static class deleteAllScheduleTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... integers) {
            database.scheduleDAO().deleteAllSchedule();
            return null;
        }
    }


}
