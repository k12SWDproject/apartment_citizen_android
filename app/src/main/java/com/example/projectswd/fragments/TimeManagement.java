package com.example.projectswd.fragments;

import android.app.DatePickerDialog;

public interface TimeManagement {
    TimeManagement dialogDatePicker(DatePickerDialog.OnDateSetListener onDateSetListener);

    void showDatePickerDialog();


}
