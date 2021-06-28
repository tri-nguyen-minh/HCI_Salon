package dev.hci.manager.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.example.hci_salon_manager.R;

import java.util.ArrayList;

import dev.hci.manager.dtos.Service;
import dev.hci.manager.dtos.ServiceDetail;

public class ServiceActivity extends AppCompatActivity {

    private ArrayList<ServiceDetail> serviceDetailsList;
    private Intent intent;
    private Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        intent = getIntent();
        service = (Service)intent.getSerializableExtra("SERVICE");

    }
}