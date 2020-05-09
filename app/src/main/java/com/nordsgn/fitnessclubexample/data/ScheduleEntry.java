package com.nordsgn.fitnessclubexample.data;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity (tableName = "schedules")
public class ScheduleEntry {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String startTime;
    private String endTime;
    private String teacherName;
    private String place;
    private String description;
    private int weekDay;

    public ScheduleEntry(int id, String title, String startTime, String endTime, String teacherName, String place, String description, int weekDay) {
        this.id = id;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.teacherName = teacherName;
        this.place = place;
        this.description = description;
        this.weekDay = weekDay;
    }
    @Ignore
    public ScheduleEntry(String title, String startTime, String endTime, String teacherName, String place, String description, int weekDay) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.teacherName = teacherName;
        this.place = place;
        this.description = description;
        this.weekDay = weekDay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(int weekDay) {
        this.weekDay = weekDay;
    }
}
