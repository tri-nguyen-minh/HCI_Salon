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
import dev.hci.manager.recycleviewadapter.RecViewServiceTypeAdapter;

public class ServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        RecyclerView recViewServiceType = findViewById(R.id.recViewServiceType);

        ArrayList<Service> servicesList  = new ArrayList<>();
        servicesList.add(new Service("Haircut", R.drawable.ic_service_cut, 2));
        servicesList.add(new Service("Hair Wash", R.drawable.ic_service_wash, 1));
        servicesList.add(new Service("Hair Treatment", R.drawable.ic_service_restore, 3));
        servicesList.add(new Service("Coloring", R.drawable.ic_service_color, 2));
        servicesList.add(new Service("Curling", R.drawable.ic_service_curling, 1));
        servicesList.add(new Service("Straightening", R.drawable.ic_service_straight, 1));
        servicesList.add(new Service("Hair Styling", R.drawable.ic_service_style, 1));

        RecViewServiceTypeAdapter adapter = new RecViewServiceTypeAdapter(ServiceActivity.this, ServiceActivity.this);
        adapter.setServicesList(servicesList);
        recViewServiceType.setAdapter(adapter);
        recViewServiceType.setLayoutManager(new LinearLayoutManager(ServiceActivity.this, RecyclerView.VERTICAL,false));

        this.getSupportActionBar().hide();
    }

    public void onBackService(View view) {
        Intent newIntent = new Intent(ServiceActivity.this, HomeActivity.class);
        newIntent.putExtra("BACK_HOME", 2);
        startActivity(newIntent);
    }
}