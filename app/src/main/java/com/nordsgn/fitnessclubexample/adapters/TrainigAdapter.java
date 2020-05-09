package com.nordsgn.fitnessclubexample.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nordsgn.fitnessclubexample.R;
import com.nordsgn.fitnessclubexample.data.ScheduleEntry;

import java.util.ArrayList;
import java.util.List;

public class TrainigAdapter extends RecyclerView.Adapter<TrainigAdapter.ScheduleViewHolder> {
    // после наследования от RecyclerView.Adapter<TrainigAdapter.ScheduleViewHolder> и передачи
    // параметров, переопределяем необходимые методы нажав комбинацию ctrl+i

    //создадим массив
    private List<ScheduleEntry> scheduleEntries;

    //создадим пустой конструктор
    public TrainigAdapter() {
        scheduleEntries = new ArrayList<>();
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.training, parent, false);
        return new ScheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        //здесь установим значения наших textView
        // для начала получаем наши textView
        ScheduleEntry scheduleEntry = scheduleEntries.get(position);

        TextView textViewName = holder.textViewName;
        textViewName.setText(scheduleEntry.getTitle());

        TextView textViewDescription = holder.textViewDescription;
        textViewDescription.setText(scheduleEntry.getDescription());

        TextView textViewTeacher = holder.textViewTeacher;
        textViewTeacher.setText(scheduleEntry.getTeacherName());

        TextView textViewTime = holder.textViewTime;
        String time = String.format("c %s по %s", scheduleEntry.getStartTime(), scheduleEntry.getEndTime());
        textViewTime.setText(time);

        TextView textViewPlace = holder.textViewPlace;
        textViewPlace.setText(scheduleEntry.getPlace());

        TextView textViewDayOfWeek = holder.textViewDayOfWeek;
        int dayofWeekId = scheduleEntry.getWeekDay();
        String dayOfWeek = null;
        switch (dayofWeekId) {
            case 1:
                dayOfWeek = "Понедельник";
                break;
            case 2:
                dayOfWeek = "Вторник";
                break;
            case 3:
                dayOfWeek = "Среда";
                break;
            case 4:
                dayOfWeek = "Четверг";
                break;
            case 5:
                dayOfWeek = "Пятница";
                break;
            case 6:
                dayOfWeek = "Суббота";
                break;
            case 7:
                dayOfWeek = "Воскресенье";
                break;
            default:
                dayOfWeek = "неизвестно";
                break;
        }
        textViewDayOfWeek.setText(dayOfWeek);

    }

    @Override
    public int getItemCount() {
        //возвратим размер массива
        return scheduleEntries.size();
    }

    class ScheduleViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewName;
        private TextView textViewDescription;
        private TextView textViewTeacher;
        private TextView textViewTime;
        private TextView textViewPlace;
        private TextView textViewDayOfWeek;

        //нажатием ctrl+o переопределяем метод
        public ScheduleViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewTeacher = itemView.findViewById(R.id.textViewTeacher);
            textViewTime = itemView.findViewById(R.id.textViewTime);
            textViewPlace = itemView.findViewById(R.id.textViewPlace);
            textViewDayOfWeek = itemView.findViewById(R.id.textViewDayOfWeek);

            //после создания viewHolder наследуем наш класс от RecyclerView.Adapter

        }
    }

    //добавим setter и геттер для массива alt+insert

    public void setScheduleEntries(List<ScheduleEntry> scheduleEntries) {
        this.scheduleEntries = scheduleEntries;
        notifyDataSetChanged();
    }

    public List<ScheduleEntry> getScheduleEntries() {
        return scheduleEntries;
    }
}
