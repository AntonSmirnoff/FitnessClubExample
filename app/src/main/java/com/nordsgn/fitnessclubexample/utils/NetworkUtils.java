package com.nordsgn.fitnessclubexample.utils;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class NetworkUtils {

    private final static String BASE_URL = "https://sample.fitnesskit-admin.ru/schedule/get_group_lessons_v2/1/";
    private final static int REQUEST_TIMEOUT = 3000;

    public static JSONArray GetJSONArray() {
        JSONArray jsonArray = null;

        try {
            URL url = new URL(BASE_URL);
            jsonArray = new JSONArrayLoaderFromWeb().execute(url).get();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return jsonArray;
    }

    private static class JSONArrayLoaderFromWeb extends AsyncTask<URL, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(URL... urls) {
            JSONArray jsonArray = null;
            if (urls == null || urls.length == 0)
                return jsonArray;
            HttpURLConnection urlConnection = null;
            try {
                urlConnection = (HttpURLConnection) urls[0].openConnection();
                //получаем поток
                InputStream inputStream = urlConnection.getInputStream();
                //читаем поток
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                //Читаем по строкам
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                //трингБилдер для сохранения по строчно
                StringBuilder builder = new StringBuilder();
                //читаем первую строку
                String line = bufferedReader.readLine();
                // далее в цикле по строчно сохраняем поток
                while (line != null) {
                    builder.append(line);
                    line = bufferedReader.readLine();
                }
                jsonArray = new JSONArray(builder.toString());

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
            return jsonArray;
        }
    }

}
