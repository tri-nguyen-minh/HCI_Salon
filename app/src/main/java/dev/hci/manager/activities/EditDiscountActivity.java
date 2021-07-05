package dev.hci.manager.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import dev.hci.manager.R;
import dev.hci.manager.dtos.Service;
import dev.hci.manager.dtos.ServiceDetail;
import dev.hci.manager.recycleviewadapter.RecViewServiceAdapter;

public class EditDiscountActivity extends AppCompatActivity {

    private Intent intent;
    private ServiceDetail discount;
    private TextView txtCommon;
    private EditText editTitle, editValue;
    private Spinner spinnerCommon;
    private RecyclerView recViewDiscountService;
    private ArrayAdapter<String> dataAdapter;
    private ArrayList<String> dataSpinnerService;
    private LinearLayout layoutWithDiscount;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_discount);

        intent = getIntent();
        discount = (ServiceDetail) intent.getSerializableExtra("DISCOUNT");

        editTitle = findViewById(R.id.editDiscountName);
        editValue = findViewById(R.id.editDiscountValue);
        position = intent.getIntExtra("POSITION", -1);

        if (position >= 0) {
            editTitle.setText(discount.getName());
            editValue.setText(discount.getDiscount() + "");
        }
        recViewDiscountService = findViewById(R.id.recViewDiscountService);
        RecViewServiceAdapter adapter = new RecViewServiceAdapter(EditDiscountActivity.this, EditDiscountActivity.this, 3);
        adapter.setServiceDetailsList(discount.getList());
        recViewDiscountService.setAdapter(adapter);
        recViewDiscountService.setLayoutManager(new LinearLayoutManager(EditDiscountActivity.this, RecyclerView.VERTICAL,false));

        this.getSupportActionBar().hide();
    }

    public void onBackDiscount(View view) {
        startActivity(new Intent(getApplicationContext(), DiscountActivity.class));
    }

    public void onRegisterClick(View view) {
        discount.setName(editTitle.getText().toString());
        discount.setDiscount(Integer.parseInt(editValue.getText().toString()));

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
        intent = new Intent(getApplicationContext(), DiscountActivity.class);
        intent.putExtra("DISCOUNT", discount);
        intent.putExtra("POSITION", position);
        startActivity(intent);
    }
}