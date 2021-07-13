package dev.hci.manager.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

import dev.hci.manager.R;
import dev.hci.manager.dtos.ServiceDetail;
import dev.hci.manager.recycleviewadapter.RecViewServiceAdapter;

public class AddDiscountActivity extends AppCompatActivity {

    private ArrayList<ServiceDetail> serviceDetailsList;
    private Intent intent;
    private ServiceDetail discount;
    private EditText editTitle, editValue;
    private RecyclerView recViewDiscountService;
    private RecViewServiceAdapter adapter;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_discount);

        intent = getIntent();
        position = intent.getIntExtra("POSITION", -1);
        setUpList();

        System.out.println(discount.getDuration());
        for (int i = 0; i < discount.getList().size(); i ++) {
            System.out.println(discount.getList().get(i).getName());
            System.out.println(discount.getList().get(i).getDiscount());
        }
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Male Haircut", "30 - 40 minutes", "50", "0",35,0));
        serviceDetailsList.add(new ServiceDetail("Female Haircut", "30 - 45 minutes", "100", "0",61,0));
        serviceDetailsList.add(new ServiceDetail("Hair Styling", "30 minutes", "70", "0",87,0));
        serviceDetailsList.add(new ServiceDetail("Hair Loss Treatment", "60 - 120 minutes", "840", "1,200",6,30));
        serviceDetailsList.add(new ServiceDetail("Hair Collagen Treatment", "30 - 60 minutes", "270", "450",32,40));
        serviceDetailsList.add(new ServiceDetail("Hair Keratin Treatment", "30 - 60 minutes", "350", "500",29,30));
        serviceDetailsList.add(new ServiceDetail("Hair Wash", "15 minutes", "30", "0",91,0));
        serviceDetailsList.add(new ServiceDetail("Wash + Massage", "60 minutes", "42", "70",75,40));
        serviceDetailsList.add(new ServiceDetail("Coloring (Short)", "60 - 90 minutes", "550", "0",24,0));
        serviceDetailsList.add(new ServiceDetail("Coloring (Long)", "60 - 90 minutes", "490", "700",45,30));
        serviceDetailsList.add(new ServiceDetail("Hair Curling", "30 - 60 minutes", "400", "0",21,0));
        serviceDetailsList.add(new ServiceDetail("Hair Straightening", "60 - 120 minutes", "450", "0",23,0));

        int count = 0;
        for (int i = 0; i < discount.getList().size(); i++) {
            for (int j = 0; j < serviceDetailsList.size(); j++) {
                if (discount.getList().get(i).getName().equals(serviceDetailsList.get(j).getName())) {
                    serviceDetailsList.get(j).setDiscounted(true);
                }
            }
        }

        setupRecycleView(serviceDetailsList);


        this.getSupportActionBar().hide();
    }

    private void setupRecycleView(ArrayList<ServiceDetail> list) {
        recViewDiscountService = findViewById(R.id.recViewAddDiscountService);
        adapter = new RecViewServiceAdapter(AddDiscountActivity.this, AddDiscountActivity.this, 4);
        adapter.setServiceDetailsList(list);
        recViewDiscountService.setAdapter(adapter);
        recViewDiscountService.setLayoutManager(new LinearLayoutManager(AddDiscountActivity.this, RecyclerView.VERTICAL,false));
    }

    private void setUpList() {
        discount = (ServiceDetail) intent.getSerializableExtra("DISCOUNT");
    }

    public void onBackDiscount(View view) {
        setUpList();
        intent = new Intent(getApplicationContext(), EditDiscountActivity.class);
        intent.putExtra("DISCOUNT", discount);
        intent.putExtra("POSITION", position);
        startActivity(intent);
    }

}