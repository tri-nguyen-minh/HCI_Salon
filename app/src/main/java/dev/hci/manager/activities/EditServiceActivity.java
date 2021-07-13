package dev.hci.manager.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

import dev.hci.manager.R;
import dev.hci.manager.dtos.Service;
import dev.hci.manager.dtos.ServiceDetail;

public class EditServiceActivity extends AppCompatActivity {

    private Intent intent;
    private Service service;
    private ServiceDetail serviceDetail;
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

        intent = getIntent();
        service = (Service)intent.getSerializableExtra("SERVICE");

        ImageView imgServiceType = findViewById(R.id.imgServiceType);
        imgServiceType.setImageResource(service.getImageId());
        txtCommon = findViewById(R.id.txtServiceType);
        txtCommon.setText(service.getServiceName());

        position = intent.getIntExtra("POSITION", -1);


        if (position >= 0) {
            serviceDetail = service.getServiceDetailsList().get(position);
            editCommon = findViewById(R.id.editServiceName);
            editCommon.setText(serviceDetail.getName());
            String priceString = serviceDetail.getOrgPrice();
            int price = 0;
            if (priceString.equals("0")) {
                priceString = serviceDetail.getPrice();
            }
            priceString = checkPriceString(priceString);
            price = Integer.parseInt(priceString) * 1000;
            editCommon = findViewById(R.id.editPrice);
            editCommon.setText(price + "");

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
        } else {
            serviceDetail = null;
        }

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

        ServiceDetail newService = new ServiceDetail();
        editCommon = findViewById(R.id.editServiceName);
        newService.setName(editCommon.getText().toString());
        editCommon = findViewById(R.id.editPrice);
        int price = Integer.parseInt(editCommon.getText().toString()) / 1000;
        if (serviceDetail == null) {
            newService.setDiscount(0);
            newService.setPrice(decimalFormat.format(price));
        } else {
            if (serviceDetail.getDiscount() == 0) {
                newService.setDiscount(0);
                newService.setPrice(decimalFormat.format(price));
            } else {
                newService.setOrgPrice(decimalFormat.format(price));
                int discountValue = serviceDetail.getDiscount();
                price = (price / 100) * (100 - discountValue);
                newService.setPrice(decimalFormat.format(price));
                newService.setDiscount(serviceDetail.getDiscount());
            }
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
        newService.setDuration(duration);
        newService.setBookCount(0);

        AlertDialog.Builder builder = new AlertDialog.Builder(EditServiceActivity.this);
        builder.setCancelable(true);
        builder.setTitle("Confirm Service Details");
        builder.setMessage("Do you want to add this Service?");
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (position >= 0) {
                            service.getServiceDetailsList().set(position, newService);
                        } else {
                            service.getServiceDetailsList().add(newService);
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