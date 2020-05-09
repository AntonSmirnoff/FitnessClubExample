package com.nordsgn.fitnessclubexample.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ScheduleDAO {

    //Интерфейс для работы с базой данных
    //DAO - Data Access Object


    //получим все данные и отсортируем по дням недели
    @Query("SELECT * FROM schedules ORDER BY weekDay")
    LiveData<List<ScheduleEntry>> getAllSchedule();

    //Запрос для того чтобы запросить отдельную тренировку
    @Query("SELECT * FROM schedules WHERE id == :id")
    ScheduleEntry getTrainingById(int id);

    //Добавить 1 запись
    @Insert
    void insertSchedule(ScheduleEntry scheduleEntry);

    //удалить 1 запись
    @Delete
    void deleteSchedule(ScheduleEntry scheduleEntry);
    //удалить все записи из БД
    @Query("DELETE FROM schedules")
    void deleteAllSchedule();


}
