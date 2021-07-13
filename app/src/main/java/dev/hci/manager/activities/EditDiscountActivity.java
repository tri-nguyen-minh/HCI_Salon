package dev.hci.manager.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import dev.hci.manager.R;
import dev.hci.manager.dtos.ServiceDetail;
import dev.hci.manager.recycleviewadapter.RecViewServiceAdapter;

public class EditDiscountActivity extends AppCompatActivity {

    private Intent intent;
    private CardView cardStartDate, cardEndDate;
    private CalendarView calendarEndDate, calendarStartDate;
    private Calendar calendar = Calendar.getInstance();
    private ServiceDetail discount;
    private EditText editTitle, editValue;
    private RecyclerView recViewDiscountService;
    private RecViewServiceAdapter adapter;
    private int position;
    private TextView txtCommon;
    private LinearLayout layoutParent;
    private RelativeLayout txtStartDateDiscount, txtEndDateDiscount;
    private DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    private DecimalFormat decimalFormat = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_discount);
        decimalFormat.setGroupingUsed(true);
        decimalFormat.setGroupingSize(3);

        cardStartDate = findViewById(R.id.cardStartDate);
        cardEndDate = findViewById(R.id.cardEndDate);

        cardStartDate.setVisibility(View.GONE);
        cardEndDate.setVisibility(View.GONE);

        txtCommon = findViewById(R.id.txtStartDateDiscount);
        txtCommon.setText(format.format((new Date()).getTime()));
        txtCommon = findViewById(R.id.txtEndDateDiscount);
        txtCommon.setText(format.format((new Date()).getTime()));

        calendarStartDate = findViewById(R.id.calendarStartDate);
        calendarEndDate = findViewById(R.id.calendarEndDate);
        calendarStartDate.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                txtCommon = findViewById(R.id.txtStartDateDiscount);
                txtCommon.setText(((dayOfMonth < 10) ? "0" + dayOfMonth : dayOfMonth) + "/" + (((month + 1) < 10) ? "0" + (month + 1) : (month + 1)) + "/" + year);
            }
        });
        calendarEndDate.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                txtCommon = findViewById(R.id.txtEndDateDiscount);
                txtCommon.setText(((dayOfMonth < 10) ? "0" + dayOfMonth : dayOfMonth) + "/" + (((month + 1) < 10) ? "0" + (month + 1) : (month + 1)) + "/" + year);
            }
        });

        calendarStartDate.setMinDate((new Date().getTime()));
        calendarEndDate.setMinDate((new Date().getTime()));

        intent = getIntent();
        System.out.println("Intent1: " + intent);
        position = intent.getIntExtra("POSITION", -1);
        setUpList();
        editTitle = findViewById(R.id.editDiscountName);
        editValue = findViewById(R.id.editDiscountValueEdit);

        if (discount != null) {
            editTitle.setText(discount.getName());
            editValue.setText(discount.getDiscount() + "");
            String duration = discount.getDuration();
            txtCommon = findViewById(R.id.txtStartDateDiscount);
            txtCommon.setText(duration.substring(0, duration.indexOf("-") - 1));
            txtCommon = findViewById(R.id.txtEndDateDiscount);
            txtCommon.setText(duration.substring(duration.indexOf("-") + 2));
//            setupDiscount();
            setupRecycleView(discount.getList());
        }

        editValue.addTextChangedListener(new TextWatcher() {
            String beforeString;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                beforeString = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (discount != null) {
                    String value = s.toString();
                    if (value.length() > 1 && value.indexOf("0") == 0) {
                        editValue.setText(value.substring(1));
                    }
                    int discountValue = checkDiscountValue();
                    if(discountValue > 100) {
                        discountValue = 100;
                        editValue.setText(discountValue + "");
                    }

                    setupDiscount();
                    setupRecycleView(discount.getList());
                }
            }
        });

        txtStartDateDiscount = findViewById(R.id.layoutStartDateDiscount);
        txtStartDateDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cardStartDate.getVisibility() == View.GONE) {
                    cardStartDate.setVisibility(View.VISIBLE);
                } else {
                    cardStartDate.setVisibility(View.GONE);
                }
                cardEndDate.setVisibility(View.GONE);
            }
        });
        txtEndDateDiscount = findViewById(R.id.layoutEndDateDiscount);
        txtEndDateDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cardEndDate.getVisibility() == View.GONE) {
                    cardEndDate.setVisibility(View.VISIBLE);
                } else {
                    cardEndDate.setVisibility(View.GONE);
                }
                cardStartDate.setVisibility(View.GONE);
            }
        });

        layoutParent = findViewById(R.id.layoutParent);
        layoutParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardStartDate.setVisibility(View.GONE);
                cardEndDate.setVisibility(View.GONE);
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
        if (discount == null) {
            discount = new ServiceDetail();
            discount.setList(new ArrayList<>());
        } else {
            intent = getIntent();
            setUpList();
        }

        for (int i = 0; i < discount.getList().size() - 1; i ++) {
            if (discount.getList().get(i).getDiscount() != discount.getList().get(i + 1).getDiscount()) {
                System.out.println(discount.getList().get(i).getDiscount() != discount.getList().get(i + 1).getDiscount());
                discount.setDiscount(0);
            }
        }
        discount.setName(editTitle.getText().toString());
//        setupDiscount();

        txtCommon = findViewById(R.id.txtStartDateDiscount);
        String duration = txtCommon.getText().toString();
        txtCommon = findViewById(R.id.txtEndDateDiscount);
        duration += " - " + txtCommon.getText().toString();
        discount.setDuration(duration);

        discount.setBookCount(1);

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

    private void setupDiscount() {
        int discountValue = 0, price = 0;
        discount.setDiscount(checkDiscountValue());
        for (ServiceDetail serviceDetail: discount.getList()) {
            discountValue = checkDiscountValue();
            if (serviceDetail.getDiscount() > 0) {
                price = Integer.parseInt(checkPriceString(serviceDetail.getOrgPrice())) * 10;
            } else {
                price = Integer.parseInt(checkPriceString(serviceDetail.getPrice())) * 10;
            }
            serviceDetail.setOrgPrice(decimalFormat.format(price  / 10));
            price = (price / 100) * (100 - discountValue);
            serviceDetail.setPrice((price  / 10) + "");
            serviceDetail.setDiscount(checkDiscountValue());
        }
    }

    public void onAddDiscountClick(View view) {

        if (position >= 0) {
            intent = getIntent();
            setUpList();
            discount.setName(editTitle.getText().toString());
            int discountValue = 0, price = 0;
            discount.setDiscount(checkDiscountValue());
        }else {
            discount = new ServiceDetail();
            discount.setName(editTitle.getText().toString());
            int price = checkDiscountValue();
            discount.setDiscount(price);
            discount.setList(new ArrayList<>());
        }
        txtCommon = findViewById(R.id.txtStartDateDiscount);
        String duration = txtCommon.getText().toString();
        txtCommon = findViewById(R.id.txtEndDateDiscount);
        duration += " - " + txtCommon.getText().toString();
        discount.setDuration(duration);

        intent = new Intent(getApplicationContext(), AddDiscountActivity.class);
        intent.putExtra("DISCOUNT", discount);
        intent.putExtra("POSITION", position);
        startActivity(intent);
    }

    private int checkDiscountValue() {
        return editValue.getText().toString().isEmpty() ? 0 : Integer.parseInt(editValue.getText().toString());
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