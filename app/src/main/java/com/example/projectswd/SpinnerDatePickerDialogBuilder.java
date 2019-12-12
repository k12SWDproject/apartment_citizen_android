package com.example.projectswd;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class SpinnerDatePickerDialogBuilder {
    private Context context;
    private DatePickerDialog.OnDateSetListener callBack;
    private DatePickerDialog.OnCancelListener onCancel;
    private boolean isDayShown = true;
    private boolean isTitleShown = true;
    private String customTitle = "";
    private int theme = 0;                 //default theme
    private int spinnerTheme = 0;          //default theme
    private Calendar defaultDate = new GregorianCalendar(1980, 0, 1);
    private Calendar minDate = new GregorianCalendar(1900, 0, 1);
    private Calendar maxDate = new GregorianCalendar(2100, 0, 1);


    public SpinnerDatePickerDialogBuilder() {
    }

    public SpinnerDatePickerDialogBuilder context(Context context) {
        this.context = context;
        return this;
    }

    public SpinnerDatePickerDialogBuilder callback(DatePickerDialog.OnDateSetListener callBack) {
        this.callBack = callBack;
        return this;
    }

    public SpinnerDatePickerDialogBuilder onCancel(DatePickerDialog.OnCancelListener onCancel) {
        this.onCancel = onCancel;
        return this;
    }

    public SpinnerDatePickerDialogBuilder dialogTheme(int theme) {
        this.theme = theme;
        return this;
    }

    public SpinnerDatePickerDialogBuilder spinnerTheme(int spinnerTheme) {
        this.spinnerTheme = spinnerTheme;
        return this;
    }

    public SpinnerDatePickerDialogBuilder defaultDate(int year, int monthIndexedFromZero, int day) {
        this.defaultDate = new GregorianCalendar(year, monthIndexedFromZero, day);
        return this;
    }

    public SpinnerDatePickerDialogBuilder minDate(int year, int monthIndexedFromZero, int day) {
        this.minDate = new GregorianCalendar(year, monthIndexedFromZero, day);
        return this;
    }

    public SpinnerDatePickerDialogBuilder maxDate(int year, int monthIndexedFromZero, int day) {
        this.maxDate = new GregorianCalendar(year, monthIndexedFromZero, day);
        return this;
    }

    public SpinnerDatePickerDialogBuilder showDaySpinner(boolean showDaySpinner) {
        this.isDayShown = showDaySpinner;
        return this;
    }

    public SpinnerDatePickerDialogBuilder showTitle(boolean showTitle) {
        this.isTitleShown = showTitle;
        return this;
    }

    public SpinnerDatePickerDialogBuilder customTitle(String title) {
        this.customTitle = title;
        return this;
    }

    public SpinnerDatePickerDialogBuilder(Context context, DatePickerDialog.OnDateSetListener callBack, DatePickerDialog.OnCancelListener onCancel, boolean isDayShown, boolean isTitleShown, String customTitle, int theme, int spinnerTheme, Calendar defaultDate, Calendar minDate, Calendar maxDate) {
        this.context = context;
        this.callBack = callBack;
        this.onCancel = onCancel;
        this.isDayShown = isDayShown;
        this.isTitleShown = isTitleShown;
        this.customTitle = customTitle;
        this.theme = theme;
        this.spinnerTheme = spinnerTheme;
        this.defaultDate = defaultDate;
        this.minDate = minDate;
        this.maxDate = maxDate;
    }

    public DatePickerDialog build() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR) - 18;
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        if (context == null) throw new IllegalArgumentException("Context must not be null");
        if (maxDate.getTime().getTime() <= minDate.getTime().getTime()) throw new IllegalArgumentException("Max date is not after Min date");
        return new DatePickerDialog(context, R.style.Theme_AppCompat_DayNight_Dialog,
                (DatePickerDialog.OnDateSetListener) context, year,month,day);
//        return new DatePickerDialog(context, theme, spinnerTheme, defaultDate, minDate, maxDate, isDayShown, isTitleShown, customTitle);
    }
//  /
}
