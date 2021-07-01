package dev.hci.manager.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import dev.hci.manager.R;

import java.util.ArrayList;

import dev.hci.manager.dtos.Service;
import dev.hci.manager.dtos.ServiceDetail;
import dev.hci.manager.recycleviewadapter.RecViewServiceTypeAdapter;

public class ServiceTypeActivity extends AppCompatActivity {

    private Service service;
    private ArrayList<ServiceDetail> serviceDetailsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_type);

        RecyclerView recViewServiceType = findViewById(R.id.recViewServiceType);

        ArrayList<Service> servicesList  = new ArrayList<>();

        service = new Service("Haircut", R.drawable.ic_service_cut);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Male Haircut", "30 - 40 minutes", "50", "0",35,0));
        serviceDetailsList.add(new ServiceDetail("Female Haircut", "30 - 45 minutes", "100", "0",61,0));
        service.setServiceDetailsList(serviceDetailsList);
        servicesList.add(service);
        service = new Service("Hair Styling", R.drawable.ic_service_style);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Hair Styling", "30 minutes", "70", "0",87,0));
        service.setServiceDetailsList(serviceDetailsList);
        servicesList.add(service);
        service = new Service("Hair Treatment", R.drawable.ic_service_restore);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Hair Loss Treatment", "60 - 120 minutes", "800", "1.200",6,30));
        serviceDetailsList.add(new ServiceDetail("Hair Collagen Treatment", "30 - 60 minutes", "270", "450",32,40));
        serviceDetailsList.add(new ServiceDetail("Hair Keratin Treatment", "30 - 60 minutes", "350", "500",29,30));
        service.setServiceDetailsList(serviceDetailsList);
        servicesList.add(service);
        service = new Service("Hair Wash", R.drawable.ic_service_wash);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Hair Wash", "15 minutes", "30", "0",91,0));
        serviceDetailsList.add(new ServiceDetail("Wash + Massage", "60 minutes", "42", "70",75,40));
        service.setServiceDetailsList(serviceDetailsList);
        servicesList.add(service);
        service = new Service("Coloring", R.drawable.ic_service_color);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Coloring (Short)", "60 - 90 minutes", "550", "0",24,0));
        serviceDetailsList.add(new ServiceDetail("Coloring (Long)", "60 - 90 minutes", "700", "0",45,0));
        service.setServiceDetailsList(serviceDetailsList);
        servicesList.add(service);
        service = new Service("Curling", R.drawable.ic_service_curling);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Hair Curling", "30 - 60 minutes", "400", "0",21,0));
        service.setServiceDetailsList(serviceDetailsList);
        servicesList.add(service);
        service = new Service("Straightening", R.drawable.ic_service_straight);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Hair Straightening", "60 - 120 minutes", "450", "0",23,0));
        service.setServiceDetailsList(serviceDetailsList);
        servicesList.add(service);

        RecViewServiceTypeAdapter adapter = new RecViewServiceTypeAdapter(ServiceTypeActivity.this, ServiceTypeActivity.this);
        adapter.setServicesList(servicesList);
        recViewServiceType.setAdapter(adapter);
        recViewServiceType.setLayoutManager(new LinearLayoutManager(ServiceTypeActivity.this, RecyclerView.VERTICAL,false));

        this.getSupportActionBar().hide();
    }

    public void onBackService(View view) {
        Intent newIntent = new Intent(ServiceTypeActivity.this, HomeActivity.class);
        newIntent.putExtra("BACK_HOME", 3);
        startActivity(newIntent);
    }
}