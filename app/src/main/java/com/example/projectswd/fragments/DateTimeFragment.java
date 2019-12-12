package com.example.projectswd.fragments;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.projectswd.R;
import com.example.projectswd.activities.MenuActivity;


import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class DateTimeFragment extends DialogFragment {



View view;
    public DateTimeFragment(View view) {
        this.view = view;
    }


    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR) - 18;
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), R.style.Theme_AppCompat_DayNight_Dialog,
                (DatePickerDialog.OnDateSetListener) view, year, month, day);
    }

}
