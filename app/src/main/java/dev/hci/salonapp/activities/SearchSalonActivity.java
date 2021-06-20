package dev.hci.salonapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.ArrayList;

import dev.hci.salonapp.R;
import dev.hci.salonapp.dtos.Salon;
import dev.hci.salonapp.dtos.ServiceDetail;
import dev.hci.salonapp.recycleviewadapter.RecViewSalonAdapter;

public class SearchSalonActivity extends AppCompatActivity {

    private Intent intent;
    private Spinner spinnerCommon;
    private ArrayAdapter<String> dataAdapter;
    private ArrayList<ServiceDetail> serviceDetailsList;
    private Salon salon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_salon);

        intent = SearchSalonActivity.this.getIntent();

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
        dataSpinnerService.add("Hair Treatment");
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
        dataAdapter = new ArrayAdapter<>(SearchSalonActivity.this, android.R.layout.simple_spinner_item, dataSpinnerSort);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCommon.setAdapter(dataAdapter);


        RecyclerView recViewSalon = findViewById(R.id.recViewNearSalon);
        ArrayList<Salon> salonList = new ArrayList<>();

        salonList = new ArrayList<>();
        salon = new Salon("Tuan Tay Salon", "Số 578 Sư Vạn Hạnh, Phường 10, Quận 10, Thành phố Hồ Chí Minh",
                1.5, Float.parseFloat("3.5"), 56, R.drawable.salon_tuan_tay);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Male Haircut", "30 - 40 minutes", "30", "60", 50));
        serviceDetailsList.add(new ServiceDetail("Female Haircut", "30 - 45 minutes", "60", "120", 50));
        serviceDetailsList.add(new ServiceDetail("Hair Loss Treatment", "60 - 120 minutes", "900", "1.200", 25));
        salon.setServiceDetailsList(serviceDetailsList);
        salonList.add(salon);
        salon = new Salon("Phuong Tokyo Salon", "686 Cách Mạng Tháng 8, Phường 05, Quận Tân Bình, Thành phố Hồ Chí Minh",
                2.9, Float.parseFloat("4"), 78, R.drawable.salon_phuong_tokyo);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Male Haircut", "30 - 40 minutes", "30", "60", 50));
        serviceDetailsList.add(new ServiceDetail("Hair Collagen Treatment", "30 - 60 minutes", "300", "400", 25));
        serviceDetailsList.add(new ServiceDetail("Hair Keratin Treatment", "30 - 60 minutes", "450", "600", 25));
        salon.setServiceDetailsList(serviceDetailsList);
        salonList.add(salon);
        salon = new Salon("Mervyn Hair Salon", "Số 16, Cao Bá Nhạ, Phường Nguyễn Cư Trinh, Quận 1, Thành phố Hồ Chí Minh",
                3.3, Float.parseFloat("4"), 26, R.drawable.salon_mervyn_art);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Male Haircut", "30 - 40 minutes", "30", "60", 50));
        serviceDetailsList.add(new ServiceDetail("Female Haircut", "30 - 45 minutes", "60", "120", 50));
        serviceDetailsList.add(new ServiceDetail("Hair Loss Treatment", "60 - 120 minutes", "900", "1.200", 25));
        salon.setServiceDetailsList(serviceDetailsList);
        salonList.add(salon);
        salon = new Salon("Nguyen Duy Salon", "183E Trần Quốc Thảo, Phường 09, Quận 3, Thành phố Hồ Chí Minh",
                3.6, Float.parseFloat("3.5"), 21, R.drawable.salon_nguyen_duy);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Hair Loss Treatment", "60 - 120 minutes", "900", "1.200", 25));
        serviceDetailsList.add(new ServiceDetail("Hair Collagen Treatment", "30 - 60 minutes", "300", "400", 25));
        serviceDetailsList.add(new ServiceDetail("Hair Straightening", "60 - 120 minutes", "400", "0", 0));
        salon.setServiceDetailsList(serviceDetailsList);
        salonList.add(salon);
        salon = new Salon("Linh R Hair & Salon", "263 Dương Bá Trạc, Phường 01, Quận 8, Thành phố Hồ Chí Minh",
                3.7, Float.parseFloat("3.5"), 44, R.drawable.salon_linh_r_hair);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Female Haircut", "30 - 45 minutes", "60", "120", 50));
        serviceDetailsList.add(new ServiceDetail("Hair Collagen Treatment", "30 - 60 minutes", "300", "400", 25));
        serviceDetailsList.add(new ServiceDetail("Hair Keratin Treatment", "30 - 60 minutes", "450", "600", 25));
        salon.setServiceDetailsList(serviceDetailsList);
        salonList.add(salon);

        RecViewSalonAdapter salonAdapter = new RecViewSalonAdapter(SearchSalonActivity.this, SearchSalonActivity.this,
                R.layout.recycle_view_search_card);
        salonAdapter.setSalonList(salonList);
        recViewSalon.setAdapter(salonAdapter);
        recViewSalon.setLayoutManager(new LinearLayoutManager(SearchSalonActivity.this, RecyclerView.VERTICAL,false));

        ImageView imgSearchSlider = findViewById(R.id.imgSearchSlider);
        imgSearchSlider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RelativeLayout searchDrawerHandle = findViewById(R.id.searchDrawerHandle);
                searchDrawerHandle.performClick();
            }
        });
        this.getSupportActionBar().hide();
    }

    public void onBackSearch(View view) {
        finish();
    }
}