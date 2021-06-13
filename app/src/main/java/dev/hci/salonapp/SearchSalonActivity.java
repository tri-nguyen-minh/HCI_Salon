package dev.hci.salonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchSalonActivity extends AppCompatActivity {

    private Intent intent;
    private Spinner spinnerService;
    private ArrayList<String> dataSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_salon);

        intent = getIntent();

        EditText editSearch = findViewById(R.id.editSearchSalon);
        RatingBar rating = findViewById(R.id.searchRatingSalon);
        Switch discountSwitch = findViewById(R.id.searchDiscountSwitch);

        editSearch.setText(intent.getStringExtra("nameSearch"));
        rating.setRating(intent.getFloatExtra("ratingSearch", 0));
        discountSwitch.setChecked(intent.getBooleanExtra("discountSearch", false));

        spinnerService = findViewById(R.id.spinnerService);
        dataSpinner = new ArrayList<>();
        dataSpinner.add("Choose Services");
        dataSpinner.add("Discount");
        dataSpinner.add("Haircut");
        dataSpinner.add("Hair Wash");
        dataSpinner.add("Hair Restore");
        dataSpinner.add("Coloring");
        dataSpinner.add("Curling");
        dataSpinner.add("Straightening");
        dataSpinner.add("Hair Styling");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(SearchSalonActivity.this, android.R.layout.simple_spinner_item, dataSpinner);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerService.setAdapter(dataAdapter);

        spinnerService.setSelection(dataSpinner.indexOf(intent.getStringExtra("serviceSearch")));
    }

    public void onBackSearch(View view) {
        finish();
    }
}