package dev.hci.manager.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import dev.hci.manager.R;
import dev.hci.manager.dtos.Service;
import dev.hci.manager.dtos.ServiceDetail;
import dev.hci.manager.recycleviewadapter.RecViewServiceAdapter;

public class DiscountActivity extends AppCompatActivity {

    private ArrayList<ServiceDetail> serviceDetailsList;
    private Intent intent;
    private TextView txtCommon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount);

        RecyclerView recViewService = findViewById(R.id.recViewDiscount);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Hair Loss Treatment", "60 - 120 minutes", "800", "1.200",6,30));
        serviceDetailsList.add(new ServiceDetail("Hair Collagen Treatment", "30 - 60 minutes", "270", "450",32,40));
        serviceDetailsList.add(new ServiceDetail("Hair Keratin Treatment", "30 - 60 minutes", "350", "500",29,30));
        serviceDetailsList.add(new ServiceDetail("Wash + Massage", "60 minutes", "42", "70",75,40));
        txtCommon = findViewById(R.id.txtDiscountLabel);
        setupServiceCount(serviceDetailsList.size(), recViewService);

        RecViewServiceAdapter adapter = new RecViewServiceAdapter(DiscountActivity.this, DiscountActivity.this,0);
        adapter.setServiceDetailsList(serviceDetailsList);
        recViewService.setAdapter(adapter);
        recViewService.setLayoutManager(new LinearLayoutManager(DiscountActivity.this, RecyclerView.VERTICAL,false));

        this.getSupportActionBar().hide();
    }

    private void setupServiceCount(int serviceCount, RecyclerView recViewService) {
        if (serviceCount == 0) {
            txtCommon.setText("You have not added any discount");
            recViewService.setVisibility(View.GONE);
        } else if (serviceCount == 1) {
            txtCommon.setText("1 service with Discount");
            recViewService.setVisibility(View.VISIBLE);
        } else {
            txtCommon.setText(serviceCount + " services with Discounts");
            recViewService.setVisibility(View.VISIBLE);
        }
    }

    public void onBackDiscount(View view) {
        Intent newIntent = new Intent(DiscountActivity.this, HomeActivity.class);
        newIntent.putExtra("BACK_HOME", 3);
        startActivity(newIntent);
    }
}