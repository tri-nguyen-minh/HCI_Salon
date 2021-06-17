package dev.hci.salonapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
                                        R.id.timeslot18, R.id.timeslot19,
                                        R.id.timeslot20};
    private TextView textViewCommon;
    private Intent intent;

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
        serviceDetailsList.add(new ServiceDetail("Male Haircut", "30 - 40 minutes", "60", "0"));
        serviceDetailsList.add(new ServiceDetail("Hair Keratin Treatment", "30 - 60 minutes", "450", "600"));
        serviceDetailsList.add(new ServiceDetail("Hair Styling", "30 minutes", "70", "0"));
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