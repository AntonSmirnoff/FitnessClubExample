package com.nordsgn.fitnessclubexample.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ScheduleEntry.class}, version = 1, exportSchema = false)
public abstract class ScheduleDatabase extends RoomDatabase {

    //Чтобы знать, что элемент единственный используется патерн Singelton

    private static ScheduleDatabase database;
    private static final String DB_NAME = "schedule.db";
    private static final Object LOCK = new Object();

    public static ScheduleDatabase getInstance(Context context) {
        synchronized (LOCK) {
            //использум синхронизацию, чтобы 2 разных метда одновременно не делали запрос
            if (database == null) {
                //если база данных отсутсвутет то ее нужно содать
                database = Room.databaseBuilder(context, ScheduleDatabase.class, DB_NAME).build();
            }
        }
        return database;
    }

    public abstract ScheduleDAO scheduleDAO ();

}
