package dev.hci.manager.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import dev.hci.manager.R;
import dev.hci.manager.dtos.Booking;
import dev.hci.manager.dtos.Service;
import dev.hci.manager.dtos.ServiceDetail;
import dev.hci.manager.recycleviewadapter.RecViewBookingAdapter;

public class EditServiceActivity extends AppCompatActivity {

    private Intent intent;
    private Service service;
    private TextView txtCommon;
    private EditText editCommon;
    private Spinner spinnerCommon;
    private ArrayAdapter<String> dataAdapter;
    private ArrayList<String> dataSpinnerService;
    private LinearLayout layoutWithDiscount;
    private int position;
    private DecimalFormat decimalFormat = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_service);
        decimalFormat.setGroupingUsed(true);
        decimalFormat.setGroupingSize(3);

        layoutWithDiscount = findViewById(R.id.layoutWithDiscount);
        intent = getIntent();
        service = (Service)intent.getSerializableExtra("SERVICE");

        spinnerCommon = findViewById(R.id.spinnerDiscount);
        dataSpinnerService = new ArrayList<>();
        dataSpinnerService.add("No Discount");
        dataSpinnerService.add("40% - Summer Discount");
        dataSpinnerService.add("30% - General Discount");
        dataAdapter = new ArrayAdapter<>(EditServiceActivity.this, android.R.layout.simple_spinner_item, dataSpinnerService);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCommon.setAdapter(dataAdapter);

        ImageView imgServiceType = findViewById(R.id.imgServiceType);
        imgServiceType.setImageResource(service.getImageId());
        txtCommon = findViewById(R.id.txtServiceType);
        txtCommon.setText(service.getServiceName());

        position = intent.getIntExtra("POSITION", -1);

        layoutWithDiscount.setVisibility(View.GONE);

        spinnerCommon.setSelection(0);

        if (position >= 0) {
            ServiceDetail serviceDetail = service.getServiceDetailsList().get(position);
            editCommon = findViewById(R.id.editServiceName);
            editCommon.setText(serviceDetail.getName());
            String priceString = serviceDetail.getOrgPrice();
            int price = 0;
            if (priceString.equals("0")) {
                priceString = serviceDetail.getPrice();
                priceString = checkPriceString(priceString);
                price = Integer.parseInt(priceString) * 1000;
                editCommon = findViewById(R.id.editPrice);
                editCommon.setText(price + "");
            } else {
                priceString = checkPriceString(priceString);
                price = Integer.parseInt(priceString) * 1000;
                editCommon = findViewById(R.id.editPrice);
                editCommon.setText(price + "");
                priceString = serviceDetail.getPrice();
                priceString = checkPriceString(priceString);
                price = Integer.parseInt(priceString) * 1000;
                txtCommon = findViewById(R.id.txtDiscountPrice);
                txtCommon.setText(price + "");
            }

            if (serviceDetail.getDiscount() > 0) {
                for (int i = 1; i < dataSpinnerService.size(); i++) {
                    String discountString = dataSpinnerService.get(i);
                    discountString = discountString.substring(0,discountString.indexOf("%"));
                    if (serviceDetail.getDiscount() == Integer.parseInt(discountString)) {
                        spinnerCommon.setSelection(i);
                        layoutWithDiscount.setVisibility(View.VISIBLE);
                    }
                }
            }
            String durationString = serviceDetail.getDuration();
            int indexCheck = durationString.indexOf("-");
            int fromDuration;
            editCommon = findViewById(R.id.editFromDuration);
            if (indexCheck > 0) {
                fromDuration = Integer.parseInt(durationString.substring(0, (indexCheck - 1)));
                editCommon.setText(fromDuration + "");
                int toDuration = Integer.parseInt(durationString.substring((indexCheck + 2), durationString.indexOf("m") - 1));
                editCommon = findViewById(R.id.editToDuration);
                editCommon.setText(toDuration + "");
            } else {
                fromDuration = Integer.parseInt(durationString.substring(0, durationString.indexOf(" ")));
                editCommon.setText(fromDuration + "");
            }
        }

        spinnerCommon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    layoutWithDiscount.setVisibility(View.GONE);
                } else {
                    editCommon = findViewById(R.id.editPrice);
                    String priceString = editCommon.getText().toString();
                    if (!priceString.isEmpty()) {
                        layoutWithDiscount.setVisibility(View.VISIBLE);
                        String discountString = dataSpinnerService.get(position);
                        int price = Integer.parseInt(priceString);
                        discountString = discountString.substring(0,dataSpinnerService.get(position).indexOf("0"));
                        int discount = Integer.parseInt(discountString);
                        price = (price / 10) * (10 - discount);
                        txtCommon = findViewById(R.id.txtDiscountPrice);
                        txtCommon.setText(price + "");
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        this.getSupportActionBar().hide();
    }

    private String checkPriceString(String priceString) {
        if (priceString.indexOf(",") >= 0) {
            String start = priceString.substring(0, priceString.indexOf(","));
            String end = priceString.substring(priceString.indexOf(",") + 1);
            priceString =  start + end;
        }
        return priceString;
    }

    public void onBackService(View view) {
        finish();
    }

    public void onRegisterClick(View view) {

        ServiceDetail serviceDetail = new ServiceDetail();
        editCommon = findViewById(R.id.editServiceName);
        serviceDetail.setName(editCommon.getText().toString());
        editCommon = findViewById(R.id.editPrice);
        int price = Integer.parseInt(editCommon.getText().toString()) / 1000;
        if (spinnerCommon.getSelectedItemPosition() == 0) {
            serviceDetail.setDiscount(0);
            serviceDetail.setPrice(decimalFormat.format(price));
        } else {
            serviceDetail.setOrgPrice(decimalFormat.format(price));
            String discountString = dataSpinnerService.get(spinnerCommon.getSelectedItemPosition());
            txtCommon = findViewById(R.id.txtDiscountPrice);
            price = Integer.parseInt(txtCommon.getText().toString()) / 1000;
            serviceDetail.setPrice(decimalFormat.format(price));
            discountString = discountString.substring(0,discountString.indexOf("%"));
            serviceDetail.setDiscount(Integer.parseInt(discountString));
        }
        editCommon = findViewById(R.id.editFromDuration);
        String fromDuration = editCommon.getText().toString();
        editCommon = findViewById(R.id.editToDuration);
        String toDuration = editCommon.getText().toString();
        String duration = "";
        if (toDuration.isEmpty()) {
            duration = fromDuration + " minutes";
        } else {
            duration = fromDuration + " - " + toDuration + " minutes";
        }
        serviceDetail.setDuration(duration);
        serviceDetail.setBookCount(0);

        AlertDialog.Builder builder = new AlertDialog.Builder(EditServiceActivity.this);
        builder.setCancelable(true);
        builder.setTitle("Confirm Service Details");
        builder.setMessage("Do you want to add this Service?");
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (position >= 0) {
                            service.getServiceDetailsList().set(position, serviceDetail);
                        } else {
                            service.getServiceDetailsList().add(serviceDetail);
                        }
                        intent = new Intent(getApplicationContext(), ServiceActivity.class);
                        intent.putExtra("SERVICE", service);
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
}