package dev.hci.manager.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import dev.hci.manager.R;
import dev.hci.manager.dtos.ServiceDetail;
import dev.hci.manager.recycleviewadapter.RecViewServiceAdapter;

public class EditDiscountActivity extends AppCompatActivity {

    private Intent intent;
    private ServiceDetail discount;
    private EditText editTitle, editValue;
    private RecyclerView recViewDiscountService;
    private RecViewServiceAdapter adapter;
    private int position;
    private DecimalFormat decimalFormat = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_discount);
        decimalFormat.setGroupingUsed(true);
        decimalFormat.setGroupingSize(3);

        intent = getIntent();
        position = intent.getIntExtra("POSITION", -1);
        setUpList();

        editTitle = findViewById(R.id.editDiscountName);
        editValue = findViewById(R.id.editDiscountValue);

        if (position >= 0) {
            editTitle.setText(discount.getName());
            editValue.setText(discount.getDiscount() + "");
            setupRecycleView(discount.getList());
        }

        editValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int discountValue = 0, price = 0;
                discount.setDiscount(discountValue);
                for (ServiceDetail serviceDetail: discount.getList()) {
                    discountValue = Integer.parseInt(editValue.getText().toString()) / 10;
                    if (serviceDetail.getDiscount() > 0) {
                        price = Integer.parseInt(checkPriceString(serviceDetail.getOrgPrice()));
                    } else {
                        price = Integer.parseInt(checkPriceString(serviceDetail.getPrice()));
                    }
                    price = (price / 10) * (10 - discountValue);
                    serviceDetail.setPrice(price + "");
                    serviceDetail.setDiscount(discountValue);
                }
                setupRecycleView(discount.getList());
            }
        });

        this.getSupportActionBar().hide();
    }

    private void setUpList() {
        discount = (ServiceDetail) intent.getSerializableExtra("DISCOUNT");
    }

    private void setupRecycleView(ArrayList<ServiceDetail> list) {
        recViewDiscountService = findViewById(R.id.recViewDiscountService);
        adapter = new RecViewServiceAdapter(EditDiscountActivity.this, EditDiscountActivity.this, 3);
        adapter.setServiceDetailsList(list);
        recViewDiscountService.setAdapter(adapter);
        recViewDiscountService.setLayoutManager(new LinearLayoutManager(EditDiscountActivity.this, RecyclerView.VERTICAL,false));
    }

    public void onBackDiscount(View view) {
        startActivity(new Intent(getApplicationContext(), DiscountActivity.class));
    }

    public void onRegisterClick(View view) {
        discount.setName(editTitle.getText().toString());
        int discountValue = 0, price = 0;
        discount.setDiscount(discountValue);
        for (ServiceDetail serviceDetail: discount.getList()) {
            discountValue = Integer.parseInt(editValue.getText().toString()) / 10;
            if (serviceDetail.getDiscount() > 0) {
                price = Integer.parseInt(checkPriceString(serviceDetail.getOrgPrice()));
            } else {
                price = Integer.parseInt(checkPriceString(serviceDetail.getPrice()));
            }
            price = (price / 10) * (10 - discountValue);
            serviceDetail.setPrice(price + "");
            serviceDetail.setDiscount(discountValue);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(EditDiscountActivity.this);
        builder.setCancelable(true);
        builder.setTitle("Confirm Discount Details");
        builder.setMessage("Do you want to add this Discount?");
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        intent = new Intent(getApplicationContext(), DiscountActivity.class);
                        intent.putExtra("DISCOUNT", discount);
                        intent.putExtra("POSITION", position);
                        startActivity(intent);
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onAddDiscountClick(View view) {

        if (position >= 0) {
            discount.setName(editTitle.getText().toString());
            int discountValue = 0, price = 0;
            discount.setDiscount(discountValue);
            for (ServiceDetail serviceDetail: discount.getList()) {
                discountValue = Integer.parseInt(editValue.getText().toString()) / 10;
                if (serviceDetail.getDiscount() > 0) {
                    price = Integer.parseInt(checkPriceString(serviceDetail.getOrgPrice()));
                } else {
                    price = Integer.parseInt(checkPriceString(serviceDetail.getPrice()));
                }
                price = (price / 10) * (10 - discountValue);
                serviceDetail.setPrice(price + "");
                serviceDetail.setDiscount(discountValue);
            }
        }else {
            discount = new ServiceDetail();
            discount.setName(editTitle.getText().toString());
            int price = editValue.getText().toString().isEmpty() ? 0 : Integer.parseInt(editValue.getText().toString());
            discount.setDiscount(price);
            discount.setList(new ArrayList<>());
        }
        intent = new Intent(getApplicationContext(), AddDiscountActivity.class);
        intent.putExtra("DISCOUNT", discount);
        intent.putExtra("POSITION", position);
        startActivity(intent);
    }


    private String checkPriceString(String string) {
        if (string.indexOf(",") >= 0) {
            String start = string.substring(0, string.indexOf(","));
            String end = string.substring(string.indexOf(",") + 1);
            string =  start + end;
        }
        return string;
    }
}