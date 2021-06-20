package dev.hci.salonapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import dev.hci.salonapp.R;
import dev.hci.salonapp.dtos.Salon;
import dev.hci.salonapp.navigationadapter.NavigationSalonAdapter;

public class SalonActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabLayout.Tab tabCommon;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon);

        intent = SalonActivity.this.getIntent();

        Salon salon = (Salon)intent.getSerializableExtra("salon");
        TextView txtTitle = findViewById(R.id.salonTitle);
        txtTitle.setText(salon.getName());


        tabLayout = findViewById(R.id.tabLayoutSalon);
        viewPager = findViewById(R.id.viewPagerSalon);


        tabCommon = tabLayout.newTab();
        tabCommon.setIcon(R.drawable.ic_info);
        tabCommon.setText("About Us");
        tabCommon.getIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
        tabLayout.addTab(tabCommon);

        tabCommon = tabLayout.newTab();
        tabCommon.setIcon(R.drawable.ic_service);
        tabCommon.setText("Services");
        tabCommon.getIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
        tabLayout.addTab(tabCommon);

        tabCommon = tabLayout.newTab();
        tabCommon.setIcon(R.drawable.ic_message);
        tabCommon.setText("Reviews");
        tabCommon.getIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
        tabLayout.addTab(tabCommon);

        tabLayout.setTabTextColors(getResources().getColor(R.color.white), getResources().getColor(R.color.gold));
        NavigationSalonAdapter adapter = new NavigationSalonAdapter(getSupportFragmentManager(), SalonActivity.this,
                SalonActivity.this, tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getIcon() != null)
                    tab.getIcon().setColorFilter(getResources().getColor(R.color.gold), PorterDuff.Mode.SRC_IN);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab.getIcon() != null)
                    tab.getIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        if (intent.getBooleanExtra("service", false)) {
            tabLayout.selectTab(tabLayout.getTabAt(1));
        }
        tabLayout.getTabAt(tabLayout.getSelectedTabPosition()).getIcon().setColorFilter(getResources().getColor(R.color.gold), PorterDuff.Mode.SRC_IN);

        this.getSupportActionBar().hide();
    }

    public void onBackSalon(View view) {
        finish();
    }

    public void onHome(View view) {
        Intent newIntent = new Intent(SalonActivity.this, MainActivity.class);
        newIntent.putExtra("logged", intent.getBooleanExtra("logged", false));
        startActivity(newIntent);
    }
}