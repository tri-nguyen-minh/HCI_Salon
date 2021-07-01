package dev.hci.manager.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import dev.hci.manager.R;

import java.util.ArrayList;

import dev.hci.manager.dtos.Service;
import dev.hci.manager.dtos.ServiceDetail;
import dev.hci.manager.recycleviewadapter.RecViewServiceAdapter;

public class ServiceActivity extends AppCompatActivity {

    private ArrayList<ServiceDetail> serviceDetailsList;
    private Intent intent;
    private Service service;
    private TextView txtCommon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        intent = getIntent();
        service = (Service)intent.getSerializableExtra("SERVICE");
        serviceDetailsList = service.getServiceDetailsList();

        ImageView imgServiceType = findViewById(R.id.imgServiceType);
        imgServiceType.setImageResource(service.getImageId());
        txtCommon = findViewById(R.id.txtServiceType);
        txtCommon.setText(service.getServiceName());

        txtCommon = findViewById(R.id.txtServiceLabel);
        RecyclerView recViewService = findViewById(R.id.recViewService);
        setupServiceCount(service.getServiceCount(), recViewService);

        RecViewServiceAdapter adapter = new RecViewServiceAdapter(ServiceActivity.this, ServiceActivity.this,0);
        adapter.setServiceDetailsList(serviceDetailsList);
        recViewService.setAdapter(adapter);
        recViewService.setLayoutManager(new LinearLayoutManager(ServiceActivity.this, RecyclerView.VERTICAL,false));

        this.getSupportActionBar().hide();

    }

    private void setupServiceCount(int serviceCount, RecyclerView recViewService) {
        if (serviceCount == 0) {
            txtCommon.setText("No available service");
            recViewService.setVisibility(View.GONE);
        } else if (serviceCount == 1) {
            txtCommon.setText("1 available service");
            recViewService.setVisibility(View.VISIBLE);
        } else {
            txtCommon.setText(serviceCount + " available services");
            recViewService.setVisibility(View.VISIBLE);
        }
    }

    public void onBackService(View view) {
        startActivity(new Intent(ServiceActivity.this, ServiceTypeActivity.class));
    }
}