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
        serviceDetailsList.add(new ServiceDetail("Male Haircut", "30 - 40 minutes", "30", "60", 50));
        serviceDetailsList.add(new ServiceDetail("Female Haircut", "30 - 45 minutes", "60", "120", 50));
        service.setServiceDetailsList(serviceDetailsList);
        servicesList.add(service);
        service = new Service("Hair Styling", R.drawable.ic_service_style);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Hair Styling", "30 minutes", "70", "0", 0));
        service.setServiceDetailsList(serviceDetailsList);
        servicesList.add(service);
        service = new Service("Hair Treatment", R.drawable.ic_service_restore);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Hair Loss Treatment", "60 - 120 minutes", "900", "1.200", 25));
        serviceDetailsList.add(new ServiceDetail("Hair Collagen Treatment", "30 - 60 minutes", "300", "400", 25));
        serviceDetailsList.add(new ServiceDetail("Hair Keratin Treatment", "30 - 60 minutes", "450", "600", 25));
        service.setServiceDetailsList(serviceDetailsList);
        servicesList.add(service);
        service = new Service("Hair Wash", R.drawable.ic_service_wash);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Wash + Massage", "60 minutes", "70", "0", 0));
        service.setServiceDetailsList(serviceDetailsList);
        servicesList.add(service);
        service = new Service("Coloring", R.drawable.ic_service_color);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Coloring (Short)", "60 - 90 minutes", "600", "0", 0));
        serviceDetailsList.add(new ServiceDetail("Coloring (Long)", "60 - 90 minutes", "900", "0", 0));
        service.setServiceDetailsList(serviceDetailsList);
        servicesList.add(service);
        service = new Service("Curling", R.drawable.ic_service_curling);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Hair Curling", "30 - 60 minutes", "350", "0", 0));
        service.setServiceDetailsList(serviceDetailsList);
        servicesList.add(service);
        service = new Service("Straightening", R.drawable.ic_service_straight);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Hair Straightening", "60 - 120 minutes", "400", "0", 0));
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
        newIntent.putExtra("BACK_HOME", 2);
        startActivity(newIntent);
    }
}