package com.nordsgn.fitnessclubexample.utils;

import com.nordsgn.fitnessclubexample.data.ScheduleEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONUtils {

    private static String KEY_TITLE = "name";
    private static String KEY_START_TIME = "startTime";
    private static String KEY_END_TIME = "endTime";
    private static String KEY_TEACHER_NAME = "teacher";
    private static String KEY_PLACE = "place";
    private static String KEY_DESCRIPTION = "description";
    private static String KEY_DAY_OF_WEEK = "weekDay";

    public static ArrayList<ScheduleEntry> getScheduleFromJSON(JSONArray jsonArray) {
        //Создаем массив
        ArrayList<ScheduleEntry> result = new ArrayList<>();
        //делаем проверку, вдргу массив пустой
        if (jsonArray == null) {
            return result;
        }
        // так как ключом к каждому объекту в нашем массив является цифра, перебираем его циклом
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = NetworkUtils.GetJSONArray().getJSONObject(i);
                //получаем  значения из полученного JsonObject'a
                int id = i;
                String title = jsonObject.getString(KEY_TITLE);
                String startTime = jsonObject.getString(KEY_START_TIME);
                String endTime = jsonObject.getString(KEY_END_TIME);
                String teacher = jsonObject.getString(KEY_TEACHER_NAME);
                String place = jsonObject.getString(KEY_PLACE);
                String description = jsonObject.getString(KEY_DESCRIPTION);
                int weekDay = jsonObject.getInt(KEY_DAY_OF_WEEK);
                //создаем объект scheduleEntry и передаем в него полученные значения
                ScheduleEntry scheduleEntry = new ScheduleEntry(id, title, startTime, endTime, teacher, place, description, weekDay);
                //добавляем полученный объектв массив result
                result.add(scheduleEntry);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return result;

    }


}
