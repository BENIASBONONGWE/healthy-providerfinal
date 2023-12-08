package com.example.myprojectfinal.DoctorDetails;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myprojectfinal.Databases.BookingDatabaseMalaria;
import com.example.myprojectfinal.R;

import java.util.Calendar;

public class PhysicianActivity extends AppCompatActivity {

    private TextView datePicker;
    private TextView timePicker;

    private TextView datePicker1;
    private TextView timePicker1;

    private BookingDatabaseMalaria databaseHelper;

    private boolean isBookingInProgress = false;
    private Handler bookingResetHandler = new Handler(Looper.getMainLooper());
    private static final long BOOKING_EXPIRATION_TIME = 60 * 60 * 1000; // 1 hour in milliseconds

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_malaria);

        // Initialize the database helper
        databaseHelper = new BookingDatabaseMalaria(this);

        // Initialize views for Container 1
        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timePicker);

        // Set click listener for the "Select Date and Time" button in Container 1
        TextView selectDateTimeButton = findViewById(R.id.selectDateTimeButton);
        selectDateTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show date-time picker dialog for Container 1
                showDateTimePicker();
            }
        });

        // Set click listener for the book doctor button in Container 1
        TextView bookDoctorButton = findViewById(R.id.bookDoctorButton);
        bookDoctorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedDate = datePicker.getText().toString();
                String selectedTime = timePicker.getText().toString();
                long id = saveBooking(selectedDate, selectedTime);

                if (id != -1) {
                    showToast("Book Doctor Clicked in Container 1\nDate: " + selectedDate + "\nTime: " + selectedTime
                            + "\nBooking ID: " + id);

                    // Redirect to AnotherActivity
                    Intent intent = new Intent(PhysicianActivity.this, AnotherActivity.class);
                    intent.putExtra("selectedDate", selectedDate);
                    intent.putExtra("selectedTime", selectedTime);
                    startActivity(intent);
                } else {
                    showToast("Booking failed. Please try again.");
                }
            }
        });

        // Initialize views for Container 2
        datePicker1 = findViewById(R.id.datePicker1);
        timePicker1 = findViewById(R.id.timePicker1);

        // Set click listener for the "Select Date and Time" button in Container 2
        TextView selectDateTimeButton1 = findViewById(R.id.selectDateTimeButton11);
        selectDateTimeButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show date-time picker dialog for Container 2
                showDateTimePicker1();
            }
        });

        // Set click listener for the book doctor button in Container 2
        TextView bookDoctorButton1 = findViewById(R.id.bookDoctorButton1);
        bookDoctorButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedDate1 = datePicker1.getText().toString();
                String selectedTime1 = timePicker1.getText().toString();
                long id = saveBooking(selectedDate1, selectedTime1);

                if (id != -1) {
                    showToast("Book Doctor Clicked in Container 2\nDate: " + selectedDate1 + "\nTime: " + selectedTime1
                            + "\nBooking ID: " + id);

                    // Redirect to AnotherActivity
                    Intent intent = new Intent(PhysicianActivity.this, AnotherActivity.class);
                    intent.putExtra("selectedDate", selectedDate1);
                    intent.putExtra("selectedTime", selectedTime1);
                    startActivity(intent);
                } else {
                    showToast("Booking failed. Please try again.");
                }
            }
        });
    }

    private void showDateTimePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year1, monthOfYear, dayOfMonth1) -> {
                    showTimePicker(year1, monthOfYear, dayOfMonth1);
                }, year, month, dayOfMonth);
        datePickerDialog.show();
    }

    private void showTimePicker(final int year, final int month, final int dayOfMonth) {
        final Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                (view, hourOfDay1, minute1) -> {
                    String selectedDateTime = dayOfMonth + "/" + (month + 1) + "/" + year + " " +
                            String.format("%02d:%02d", hourOfDay1, minute1);
                    datePicker.setText(selectedDateTime);
                    timePicker.setText(String.format("%02d:%02d", hourOfDay1, minute1));
                }, hourOfDay, minute, true);
        timePickerDialog.show();
    }

    private void showDateTimePicker1() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        DatePickerDialog datePickerDialog1 = new DatePickerDialog(this,
                (view, year1, monthOfYear, dayOfMonth1) -> {
                    showTimePicker1(year1, monthOfYear, dayOfMonth1);
                }, year, month, dayOfMonth);
        datePickerDialog1.show();
    }

    private void showTimePicker1(final int year, final int month, final int dayOfMonth) {
        final Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog1 = new TimePickerDialog(this,
                (view, hourOfDay1, minute1) -> {
                    String selectedDateTime1 = dayOfMonth + "/" + (month + 1) + "/" + year + " " +
                            String.format("%02d:%02d", hourOfDay1, minute1);
                    datePicker1.setText(selectedDateTime1);
                    timePicker1.setText(String.format("%02d:%02d", hourOfDay1, minute1));
                }, hourOfDay, minute, true);
        timePickerDialog1.show();
    }

    private long saveBooking(String date, String time) {
        if (isBookingInProgress) {
            showToast("Booking already in progress. Please wait.");
            return -1;
        }

        isBookingInProgress = true;

        String dateTime = date + " " + time;

        if (databaseHelper.hasRecentBooking(dateTime, BOOKING_EXPIRATION_TIME)) {
            showToast("You have already booked within the last " + BOOKING_EXPIRATION_TIME / (60 * 1000) + " minutes.");
            isBookingInProgress = false;
            return -1;
        }

        long id = databaseHelper.insertBooking(dateTime);

        bookingResetHandler.postDelayed(() -> isBookingInProgress = false, BOOKING_EXPIRATION_TIME);

        return id;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    // Method for Container 1's "Book Doctor" button
    public void onBookDoctorClick(View view) {
        // Implement logic for Container 1's "Book Doctor" button click if needed
    }

    // Method for Container 2's "Book Doctor" button
    public void onBookDoctor1Click(View view) {
        // Implement logic for Container 2's "Book Doctor" button click if needed
    }
}
