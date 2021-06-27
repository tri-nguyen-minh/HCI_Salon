package dev.hci.manager.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hci_salon_manager.R;
import com.google.android.material.tabs.TabLayout;

import dev.hci.manager.navigations.NavigationScheduleAdapter;

public class ScheduleActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabLayout.Tab tabCommon;
    private Intent intent;
    private LinearLayout linearLayoutCommon;
    private TextView txtCommon;
    private int selectedDate;
    private int[] weekDayList = {R.id.btnMonday, R.id.btnTuesday,
                                 R.id.btnWednesday, R.id.btnThursday,
                                 R.id.btnFriday, R.id.btnSaturday,
                                 R.id.btnSunday, R.id.btnWeek};
    private int[] weekDayTimeList = {R.id.txtTimeMonday, R.id.txtTimeTuesday,
                                     R.id.txtTimeWednesday, R.id.txtTimeThursday,
                                     R.id.txtTimeFriday, R.id.txtTimeSaturday,
                                     R.id.txtTimeSunday, R.id.txtTimeWeek};
    private int[] timeslotList = {R.id.btn07General, R.id.btn08General,
                                  R.id.btn09General, R.id.btn10General,
                                  R.id.btn11General, R.id.btn12General,
                                  R.id.btn13General, R.id.btn14General,
                                  R.id.btn15General, R.id.btn16General,
                                  R.id.btn17General, R.id.btn18General,
                                  R.id.btn19General, R.id.btn20General};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        tabLayout = findViewById(R.id.tabSchedule);
        viewPager = findViewById(R.id.viewPagerSchedule);

        tabCommon = tabLayout.newTab();
        tabCommon.setText("General\nSchedule");
        tabLayout.addTab(tabCommon);

        tabCommon = tabLayout.newTab();
        tabCommon.setText("Daily\nSchedule");
        tabLayout.addTab(tabCommon);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout.setTabTextColors(getResources().getColor(R.color.white), getResources().getColor(R.color.gold));
        NavigationScheduleAdapter adapter = new NavigationScheduleAdapter(getSupportFragmentManager(), ScheduleActivity.this,
                ScheduleActivity.this, tabLayout.getTabCount());
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

        this.getSupportActionBar().hide();
    }

    public void onBackSchedule(View view) {
        startActivity(new Intent(ScheduleActivity.this, HomeActivity.class));
    }

    public void onWeekdayClick(View view) {
        for (Integer id : weekDayList) {
            linearLayoutCommon = findViewById(id);
            linearLayoutCommon.setBackground(getResources().getDrawable(R.drawable.background_date_default));
        }
        linearLayoutCommon = findViewById(view.getId());
        linearLayoutCommon.setBackground(getResources().getDrawable(R.drawable.background_date_selected));
        for (int i = 0; i < weekDayList.length; i++) {
            if (weekDayList[i] == view.getId()) {
                selectedDate = i;
            }
        }

        txtCommon = findViewById(weekDayTimeList[selectedDate]);

        linearLayoutCommon = findViewById(R.id.layoutWorkHourGeneral);
        linearLayoutCommon.setVisibility(View.VISIBLE);

        String workHour = txtCommon.getText().toString();
        EditText txtTimeFromHour = findViewById(R.id.txtTimeFromHour);
        EditText txtTimeFromMinute = findViewById(R.id.txtTimeFromMinute);
        EditText txtTimeToHour = findViewById(R.id.txtTimeToHour);
        EditText txtTimeToMinute = findViewById(R.id.txtTimeToMinute);
        if (!workHour.equals("Custom")) {
            String fromTime = workHour.substring(0, workHour.indexOf(" - "));
            String toTime = workHour.substring(workHour.indexOf(" - ") + 3);
            txtTimeFromHour.setText(fromTime.substring(0, fromTime.indexOf(":")));
            txtTimeFromMinute.setText(fromTime.substring(fromTime.indexOf(":") + 1));
            txtTimeToHour.setText(toTime.substring(0, toTime.indexOf(":")));
            txtTimeToMinute.setText(toTime.substring(toTime.indexOf(":") + 1));
        } else {
            txtTimeFromHour.setText("");
            txtTimeFromMinute.setText("");
            txtTimeToHour.setText("");
            txtTimeToMinute.setText("");
        }
    }
}