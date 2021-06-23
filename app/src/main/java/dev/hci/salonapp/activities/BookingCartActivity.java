package dev.hci.salonapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import dev.hci.salonapp.R;
import dev.hci.salonapp.dtos.Salon;
import dev.hci.salonapp.dtos.ServiceDetail;
import dev.hci.salonapp.recycleviewadapter.RecViewServiceBookedAdapter;

public class BookingCartActivity extends AppCompatActivity {

    private Integer[] timeslotIdList = {R.id.timeslot08, R.id.timeslot09,
                                        R.id.timeslot10, R.id.timeslot11,
                                        R.id.timeslot12, R.id.timeslot13,
                                        R.id.timeslot14, R.id.timeslot15,
                                        R.id.timeslot16, R.id.timeslot17,
                                        R.id.timeslot18, R.id.timeslot19};
    private TextView textViewCommon;
    private LinearLayout linearLayoutCommon;
    private Intent intent;
    private int timeslotSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_cart);

        intent = BookingCartActivity.this.getIntent();

        Salon salon = (Salon)intent.getSerializableExtra("salon");

        CalendarView calendarView = findViewById(R.id.calViewPicker);

        Calendar calendar = Calendar.getInstance();

        calendarView.setMinDate((new Date().getTime()));
        calendarView.setSelectedWeekBackgroundColor(getResources().getColor(R.color.gold));

        RecyclerView recViewService = findViewById(R.id.recViewServiceBooked);
        ArrayList<ServiceDetail> serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Male Haircut", "30 - 40 minutes", "30", "60", 60));
        serviceDetailsList.add(new ServiceDetail("Hair Keratin Treatment", "30 - 60 minutes", "450", "600", 25));
        serviceDetailsList.add(new ServiceDetail("Hair Styling", "30 minutes", "70", "0", 0));
        RecViewServiceBookedAdapter adapter = new RecViewServiceBookedAdapter(BookingCartActivity.this, BookingCartActivity.this);
        adapter.setServiceDetailsList(serviceDetailsList);
        recViewService.setAdapter(adapter);
        recViewService.setLayoutManager(new LinearLayoutManager(BookingCartActivity.this, RecyclerView.VERTICAL,false));

        TextView btnKeepShopping = findViewById(R.id.btnKeepShopping);
        btnKeepShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        textViewCommon = findViewById(R.id.txtServicePriceTotal);
        int total = 0;
        for (ServiceDetail serviceDetail : serviceDetailsList) {
            total += Integer.parseInt(serviceDetail.getPrice());
        }
        textViewCommon.setText(total + ".000d");
        timeslotSelected = R.id.timeslot08;
        this.getSupportActionBar().hide();
    }

    public void onBackBooking(View view) {
        finish();
    }

    public void onTimeslotClick(View view) {
        resetTimeslot();
        textViewCommon = findViewById(view.getId());
        textViewCommon.setBackground(getResources().getDrawable(R.drawable.background_timeslot_selected));
        for (Integer timeslotId : timeslotIdList) {
            if (view.getId() == timeslotId) {
                timeslotSelected = timeslotId;
            }
        }
    }

    private void resetTimeslot() {
        for (int slot : timeslotIdList) {
            textViewCommon = findViewById(slot);

            textViewCommon.setBackground(getResources().getDrawable(R.drawable.background_like));
        }
    }

    public void OnConfirmClick(View view) {
        TextView btnConfirmServiceBooking = findViewById(R.id.btnConfirmServiceBooking);
        btnConfirmServiceBooking.setVisibility(View.GONE);
        CalendarView datePicker = findViewById(R.id.calViewPicker);
        datePicker.setVisibility(View.GONE);
        HorizontalScrollView layoutTimeslot = findViewById(R.id.layoutTimeslot);
        layoutTimeslot.setVisibility(View.GONE);

        Date date = new Date(datePicker.getDate());
        DateFormat format = new SimpleDateFormat("EEE, MMMM dd yyyy");

        textViewCommon = findViewById(R.id.txtSelectedDate);
        textViewCommon.setText(format.format(date));

        textViewCommon = findViewById(R.id.txtSelectedTimeslot);
        textViewCommon.setText(((TextView)findViewById(timeslotSelected)).getText().toString());

        linearLayoutCommon = findViewById(R.id.layoutBookTime);
        linearLayoutCommon.setVisibility(View.VISIBLE);

        linearLayoutCommon = findViewById(R.id.layoutFinalBookingConfirm);
        linearLayoutCommon.setVisibility(View.VISIBLE);

    }
}