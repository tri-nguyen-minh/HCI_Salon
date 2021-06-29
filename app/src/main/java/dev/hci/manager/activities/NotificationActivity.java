package dev.hci.manager.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import dev.hci.manager.R;
import dev.hci.manager.dtos.Notification;
import dev.hci.manager.recycleviewadapter.RecViewNotificationAdapter;

public class NotificationActivity extends AppCompatActivity {

    private Spinner spinnerCommon;
    private ArrayList<Notification> notificationsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        spinnerCommon = findViewById(R.id.spinnerNotification);
        ArrayList<String> dataSpinnerService = new ArrayList<>();
        dataSpinnerService.add("Latest");
        dataSpinnerService.add("Booking");
        dataSpinnerService.add("Reschedule");
        dataSpinnerService.add("Review");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(NotificationActivity.this, android.R.layout.simple_spinner_item, dataSpinnerService);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCommon.setAdapter(dataAdapter);

        RecyclerView recViewNotification = findViewById(R.id.recViewNotification);
        notificationsList = new ArrayList<>();
        notificationsList.add(new Notification("Trong Khanh",193,"11/07/2021 at 10:00",
                "14:27 02/07/2021", 0, false));
        notificationsList.add(new Notification("Trong Hoang",185,"12/07/2021 at 10:00",
                "13:56 02/07/2021", 1, false));
        notificationsList.add(new Notification("Duy Tam",169,"28/07/2021 at 18:00",
                "13:21 02/07/2021", 2, false));
        notificationsList.add(new Notification("Quoc Trung",191,"10/07/2021 at 10:00",
                "12:49 02/07/2021", 1, true));
        notificationsList.add(new Notification("Van Kien",192,"09/07/2021 at 18:00",
                "12:02 02/07/2021", 0, true));
        notificationsList.add(new Notification("Le Xuan",173,"01/07/2021 at 09:00",
                "10:27 02/07/2021", 2, false));
        notificationsList.add(new Notification("Quoc Trung",191,"08/07/2021 at 10:00",
                "09:58 02/07/2021", 0, true));
        notificationsList.add(new Notification("Minh Thong",190,"09/07/2021 at 08:00",
                "09:13 02/07/2021", 0, true));
        notificationsList.add(new Notification("Trong Hoang",185,"12/07/2021 at 11:00",
                "08:26 02/07/2021", 1, true));
        notificationsList.add(new Notification("Trong Hoang",185,"12/07/2021 at 12:00",
                "01:26 02/07/2021", 0, true));
        notificationsList.add(new Notification("Minh Tam",163,"27/06/2021 at 09:00",
                "23:47 01/07/2021", 2, false));
        notificationsList.add(new Notification("Thanh Long",158,"26/06/2021 at 14:00",
                "23:23 02/07/2021", 2, true));

        RecViewNotificationAdapter adapter = new RecViewNotificationAdapter(NotificationActivity.this, NotificationActivity.this);
        adapter.setNotificationsList(notificationsList);
        recViewNotification.setAdapter(adapter);
        recViewNotification.setLayoutManager(new LinearLayoutManager(NotificationActivity.this, RecyclerView.VERTICAL,false));


        this.getSupportActionBar().hide();
    }

    public void onBackNotification(View view) {
        finish();
    }

    public void onNotificationSort(View view) {
        spinnerCommon.performClick();
    }
}