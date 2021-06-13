package dev.hci.salonapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.ArrayList;

import dev.hci.salonapp.dtos.Salon;
import dev.hci.salonapp.recycleviewadapter.RecViewSalonAdapter;

public class SearchSalonActivity extends AppCompatActivity {

    private Intent intent;
    private Spinner spinnerCommon;
    private ArrayAdapter<String> dataAdapter;

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

        spinnerCommon = findViewById(R.id.spinnerService);
        ArrayList<String> dataSpinnerService = new ArrayList<>();
        dataSpinnerService.add("Choose Services");
        dataSpinnerService.add("Discount");
        dataSpinnerService.add("Haircut");
        dataSpinnerService.add("Hair Wash");
        dataSpinnerService.add("Hair Restore");
        dataSpinnerService.add("Coloring");
        dataSpinnerService.add("Curling");
        dataSpinnerService.add("Straightening");
        dataSpinnerService.add("Hair Styling");
        dataAdapter = new ArrayAdapter<>(SearchSalonActivity.this, android.R.layout.simple_spinner_item, dataSpinnerService);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCommon.setAdapter(dataAdapter);

        spinnerCommon.setSelection(dataSpinnerService.indexOf(intent.getStringExtra("serviceSearch")));

        spinnerCommon = findViewById(R.id.spinnerSort);
        ArrayList<String> dataSpinnerSort = new ArrayList<>();
        dataSpinnerSort.add("All");
        dataSpinnerSort.add("Closest");
        dataSpinnerSort.add("Most Booked");
        dataSpinnerSort.add("Most Reviews");
        dataAdapter = new ArrayAdapter<>(SearchSalonActivity.this, android.R.layout.simple_spinner_item, dataSpinnerService);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCommon.setAdapter(dataAdapter);


        RecyclerView recViewSalon = findViewById(R.id.recViewNearSalon);
        ArrayList<Salon> salonList = new ArrayList<>();

        salonList.add(new Salon("Tuan Tay Salon", "Số 578 Sư Vạn Hạnh, Phường 10, Quận 10, Thành phố Hồ Chí Minh",
                1.5, Float.parseFloat("3.5"), 56, 75, R.drawable.salon_tuan_tay));
        salonList.add(new Salon("Phuong Tokyo Salon", "686 Cách Mạng Tháng 8, Phường 05, Quận Tân Bình, Thành phố Hồ Chí Minh",
                2.9, Float.parseFloat("4"), 78, 0, R.drawable.salon_phuong_tokyo));
        salonList.add(new Salon("Mervyn Hair Salon", "Số 16, Cao Bá Nhạ, Phường Nguyễn Cư Trinh, Quận 1, Thành phố Hồ Chí Minh",
                3.3, Float.parseFloat("4"), 126, 0, R.drawable.salon_mervyn_art));
        salonList.add(new Salon("Nguyen Duy Salon", "183E Trần Quốc Thảo, Phường 09, Quận 3, Thành phố Hồ Chí Minh",
                3.6, Float.parseFloat("3.5"), 21, 20, R.drawable.salon_nguyen_duy));
        salonList.add(new Salon("Linh R Hair & Salon", "263 Dương Bá Trạc, Phường 01, Quận 8, Thành phố Hồ Chí Minh",
                3.7, Float.parseFloat("3.5"), 44, 20, R.drawable.salon_linh_r_hair));

        RecViewSalonAdapter salonAdapter = new RecViewSalonAdapter(SearchSalonActivity.this, SearchSalonActivity.this,
                R.layout.recycle_view_salon_search);
        salonAdapter.setSalonList(salonList);
        recViewSalon.setAdapter(salonAdapter);
        recViewSalon.setLayoutManager(new LinearLayoutManager(SearchSalonActivity.this, RecyclerView.VERTICAL,false));

    }

    public void onBackSearch(View view) {
        finish();
    }
}