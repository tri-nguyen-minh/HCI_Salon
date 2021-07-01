package dev.hci.manager.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import dev.hci.manager.R;
import dev.hci.manager.dtos.Booking;
import dev.hci.manager.recycleviewadapter.RecViewNotificationAdapter;

public class NotificationActivity extends AppCompatActivity {

    private Spinner spinnerCommon;
    private ArrayList<Booking> notificationsList;
    private RecyclerView recViewCommon;
    private RecViewNotificationAdapter adapter;
    private ArrayList<Booking> tempNotificationsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        spinnerCommon = findViewById(R.id.spinnerNotification);
        ArrayList<String> dataSpinnerService = new ArrayList<>();
        dataSpinnerService.add("Latest");
        dataSpinnerService.add("Booking");
        dataSpinnerService.add("Reschedule");
        dataSpinnerService.add("Cancelled");
        dataSpinnerService.add("Review");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(NotificationActivity.this, android.R.layout.simple_spinner_item, dataSpinnerService);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCommon.setAdapter(dataAdapter);
        spinnerCommon.setSelection(0);

        recViewCommon = findViewById(R.id.recViewNotification);
        adapter = new RecViewNotificationAdapter(NotificationActivity.this, NotificationActivity.this);

        notificationsList = new ArrayList<>();
        notificationsList.add(new Booking("Trong Khanh",213,"11/07/2021","10:00",
                "14:27 02/07/2021", 0, false));
        notificationsList.add(new Booking("Trong Hoang",209,"12/07/2021","10:00",
                "13:56 02/07/2021", 1, false));
        notificationsList.add(new Booking("Duy Tam",189,"28/06/2021", "18:00",
                "13:21 02/07/2021", 3, false));
        notificationsList.add(new Booking("Quoc Trung",211,"10/07/2021", "10:00",
                "12:49 02/07/2021", 2, true));
        notificationsList.add(new Booking("Van Kien",212,"09/07/2021","17:00",
                "12:02 02/07/2021", 0, true));
        notificationsList.add(new Booking("Le Xuan",174,"01/07/2021", "09:00",
                "10:27 02/07/2021", 3, false));
        notificationsList.add(new Booking("Quoc Trung",211,"08/07/2021", "11:00",
                "09:58 02/07/2021", 2, true));
        notificationsList.add(new Booking("Minh Thong",210,"09/07/2021", "08:00",
                "09:13 02/07/2021", 0, true));
        notificationsList.add(new Booking("Trong Hoang",209,"12/07/2021", "11:00",
                "08:26 02/07/2021", 1, true));
        notificationsList.add(new Booking("Trong Hoang",209,"12/07/2021", "12:00",
                "01:26 02/07/2021", 0, true));
        notificationsList.add(new Booking("Minh Tam",183,"27/06/2021","09:00",
                "23:47 01/07/2021", 3, false));
        notificationsList.add(new Booking("Thanh Long",164,"26/06/2021","14:00",
                "23:23 02/07/2021", 3, true));

        setupRecycleView(notificationsList);

        spinnerCommon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tempNotificationsList = new ArrayList<>();
                if (position == 0) {
                    setupRecycleView(notificationsList);
                } else {
                    int type = position - 1;
                    for (Booking notification : notificationsList) {
                        if (notification.getContentTypeCode() == type) {
                            tempNotificationsList.add(notification);
                        }
                    }
                    setupRecycleView(tempNotificationsList);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        this.getSupportActionBar().hide();
    }

    private void setupRecycleView(ArrayList<Booking> list) {
        recViewCommon = findViewById(R.id.recViewNotification);
        adapter = new RecViewNotificationAdapter(NotificationActivity.this, NotificationActivity.this);
        adapter.setNotificationsList(list);
        recViewCommon.setAdapter(adapter);
        recViewCommon.setLayoutManager(new LinearLayoutManager(NotificationActivity.this, RecyclerView.VERTICAL,false));
    }

    public void onBackNotification(View view) {
        finish();
    }

    public void onNotificationSort(View view) {
        spinnerCommon.performClick();
    }
}