package dev.hci.manager.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.hci_salon_manager.R;

import java.util.ArrayList;

import dev.hci.manager.dtos.Service;
import dev.hci.manager.dtos.ServiceDetail;
import dev.hci.manager.recycleviewadapter.RecViewServiceAdapter;

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
        serviceDetailsList = service.getServiceDetailsList();
        RecyclerView recViewService = findViewById(R.id.recViewService);

        RecViewServiceAdapter adapter = new RecViewServiceAdapter(ServiceActivity.this, ServiceActivity.this);
        adapter.setServiceDetailsList(serviceDetailsList);
        recViewService.setAdapter(adapter);
        recViewService.setLayoutManager(new LinearLayoutManager(ServiceActivity.this, RecyclerView.VERTICAL,false));

        this.getSupportActionBar().hide();

    }

    public void onBackService(View view) {
        startActivity(new Intent(ServiceActivity.this, ServiceTypeActivity.class));
    }
}