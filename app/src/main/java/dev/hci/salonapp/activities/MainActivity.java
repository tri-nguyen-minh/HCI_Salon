package dev.hci.salonapp.activities;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import dev.hci.salonapp.R;
import dev.hci.salonapp.navigationadapter.NavigationMainAdapter;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabLayout.Tab tabCommon;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);


        tabCommon = tabLayout.newTab();
        tabCommon.setIcon(R.drawable.ic_nav_booking);
        tabCommon.setText("Booking");
        tabCommon.getIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
        tabLayout.addTab(tabCommon);

        tabCommon = tabLayout.newTab();
        tabCommon.setIcon(R.drawable.ic_nav_shopping);
        tabCommon.setText("Shopping");
        tabCommon.getIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
        tabLayout.addTab(tabCommon);

        tabCommon = tabLayout.newTab();
        tabCommon.setIcon(R.drawable.ic_nav_history);
        tabCommon.setText("History");
        tabCommon.getIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
        tabLayout.addTab(tabCommon);

        tabCommon = tabLayout.newTab();
        tabCommon.setIcon(R.drawable.ic_nav_profile);
        tabCommon.setText("Account");
        tabCommon.getIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
        tabLayout.addTab(tabCommon);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout.setTabTextColors(getResources().getColor(R.color.white), getResources().getColor(R.color.gold));
        NavigationMainAdapter adapter = new NavigationMainAdapter(getSupportFragmentManager(), MainActivity.this,
                MainActivity.this, tabLayout.getTabCount());
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

        intent = MainActivity.this.getIntent();
        TextView txtTag = findViewById(R.id.txtTag);
        TextView txtUser = findViewById(R.id.txtUsername);
        if (!intent.getBooleanExtra("logged", false)) {
            txtTag.setVisibility(View.GONE);
            txtUser.setText("You are not logged in!");
        } else {
            txtTag.setVisibility(View.VISIBLE);
            txtTag.setText("Welcome,");
            txtUser.setText("User Name");
        }
        if (intent.getBooleanExtra("forced_logged", false)) {
            tabLayout.selectTab(tabLayout.getTabAt(3));
        }
        this.getSupportActionBar().hide();
    }

    public void onUserClick(View view) {
        tabLayout.selectTab(tabLayout.getTabAt(3));
    }
}