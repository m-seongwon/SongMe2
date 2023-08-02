package com.example.songme2;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button btnSelectDate;
    private TextView tvSelectedDate;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSelectDate = findViewById(R.id.btnSelectDate);
        tvSelectedDate = findViewById(R.id.tvSelectedDate);

        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.set(year, month, dayOfMonth);
                        String formattedDate = dateFormat.format(selectedDate.getTime());
                        tvSelectedDate.setText("Selected Date: " + formattedDate);
                        showTimePickerDialog(selectedDate);
                    }
                },
                year,
                month,
                dayOfMonth
        );

        datePickerDialog.show();
    }

    private void showTimePickerDialog(final Calendar selectedDate) {
        Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        selectedDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        selectedDate.set(Calendar.MINUTE, minute);

                        String formattedDateTime = dateFormat.format(selectedDate.getTime());
                        formattedDateTime += " " + String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute);
                        showToast("Selected Date and Time: " + formattedDateTime);
                    }
                },
                hourOfDay,
                minute,
                true
        );

        timePickerDialog.show();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}