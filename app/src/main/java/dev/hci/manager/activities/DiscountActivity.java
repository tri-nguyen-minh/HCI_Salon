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

    private ServiceDetail serviceDetail;
    private ArrayList<ServiceDetail> serviceDetailsList;
    private ArrayList<ServiceDetail> discountList;
    private Intent intent;
    private TextView txtCommon;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount);

        RecyclerView recViewService = findViewById(R.id.recViewDiscount);

        discountList = new ArrayList<>();
        serviceDetail = new ServiceDetail("Summer Discount","01/06/2021 - 31/08/2021","0","0", 1 ,20);
        serviceDetail.setDiscounted(true);


        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail(   "Hair Collagen Treatment", "30 - 60 minutes", "360", "450",32,20));
        serviceDetailsList.add(new ServiceDetail("Wash + Massage", "60 minutes", "56", "70",75,20));
        serviceDetail.setList(serviceDetailsList);
        discountList.add(serviceDetail);

        serviceDetail = new ServiceDetail("Spring Discount","01/01/2021 - 28/02/2021","0","0", 0 ,0);

        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Hair Loss Treatment", "60 - 120 minutes", "1,080", "1,200",6,10));
        serviceDetailsList.add(new ServiceDetail("Hair Keratin Treatment", "30 - 60 minutes", "425", "500",29,15));
        serviceDetailsList.add(new ServiceDetail("Coloring (Long)", "60 - 90 minutes", "630", "700",45,10));
        serviceDetail.setList(serviceDetailsList);
        discountList.add(serviceDetail);

        txtCommon = findViewById(R.id.txtDiscountLabel);

        intent = getIntent();
        position = intent.getIntExtra("POSITION", -2);

        if (position != -2) {
            serviceDetail = (ServiceDetail)intent.getSerializableExtra("DISCOUNT");
            if (position == -1) {
                discountList.add(serviceDetail);
            } else {
                if (position > (discountList.size() - 1)) {
                    discountList.add(serviceDetail);
                } else {
                    discountList.set(position, serviceDetail);
                }
            }
        }
        setupServiceCount(discountList.size(), recViewService);

        RecViewServiceAdapter adapter = new RecViewServiceAdapter(DiscountActivity.this, DiscountActivity.this,2);
        adapter.setServiceDetailsList(discountList);
        recViewService.setAdapter(adapter);
        recViewService.setLayoutManager(new LinearLayoutManager(DiscountActivity.this, RecyclerView.VERTICAL,false));

        this.getSupportActionBar().hide();
    }

    private void setupServiceCount(int serviceCount, RecyclerView recViewService) {
        if (serviceCount == 0) {
            txtCommon.setText("You have not added any discount");
            recViewService.setVisibility(View.GONE);
        } else if (serviceCount == 1) {
            txtCommon.setText("1 Discount");
            recViewService.setVisibility(View.VISIBLE);
        } else {
            txtCommon.setText(serviceCount + " Discounts");
            recViewService.setVisibility(View.VISIBLE);
        }
    }

    public void onBackDiscount(View view) {
        Intent newIntent = new Intent(getApplicationContext(), HomeActivity.class);
        newIntent.putExtra("BACK_HOME", 3);
        startActivity(newIntent);
    }

    public void onAddDiscountClick(View view) {
        intent = new Intent(getApplicationContext(), EditDiscountActivity.class);
        startActivity(intent);
    }
}