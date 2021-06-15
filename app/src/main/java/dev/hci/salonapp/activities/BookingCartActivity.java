package dev.hci.salonapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import dev.hci.salonapp.R;

public class BookingCartActivity extends AppCompatActivity {

    private Integer[] timeslotIdList = {R.id.timeslot08, R.id.timeslot09,
                                        R.id.timeslot10, R.id.timeslot11,
                                        R.id.timeslot12, R.id.timeslot13,
                                        R.id.timeslot14, R.id.timeslot15,
                                        R.id.timeslot16, R.id.timeslot17,
                                        R.id.timeslot18, R.id.timeslot19,
                                        R.id.timeslot20};
    private TextView textViewCommon;

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

    public void onTimeslotClick(View view) {
        resetTimeslot();
        textViewCommon = findViewById(view.getId());
        textViewCommon.setBackground(getResources().getDrawable(R.drawable.background_timeslot_selected));
    }

    private void resetTimeslot() {
        for (int slot : timeslotIdList) {
            textViewCommon = findViewById(slot);
            textViewCommon.setBackground(getResources().getDrawable(R.drawable.background_like));
        }
    }
}