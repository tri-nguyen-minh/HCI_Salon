package dev.hci.manager.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import dev.hci.manager.R;

public class NotificationActivity extends AppCompatActivity {

    private Spinner spinnerCommon;

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

        this.getSupportActionBar().hide();
    }

    public void onBackNotification(View view) {
        finish();
    }

    public void onNotificationSort(View view) {
        spinnerCommon.performClick();
    }
}