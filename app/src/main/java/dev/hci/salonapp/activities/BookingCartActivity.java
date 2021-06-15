package dev.hci.salonapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

import java.util.Calendar;
import java.util.Date;

import dev.hci.salonapp.R;

public class BookingCartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_cart);

        CalendarView calendarView = findViewById(R.id.calViewPicker);

        Calendar calendar = Calendar.getInstance();

        calendarView.setMinDate((new Date().getTime()));
        calendarView.setSelectedWeekBackgroundColor(getResources().getColor(R.color.gold));

    }

    public void onBackBooking(View view) {
        finish();
    }
}