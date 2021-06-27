package dev.hci.manager.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hci_salon_manager.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import dev.hci.manager.navigations.NavigationScheduleAdapter;

public class ScheduleActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabLayout.Tab tabCommon;
    private Intent intent;
    private LinearLayout linearLayoutCommon;
    private ConstraintLayout constraintLayoutCommon;
    private TextView txtCommon;
    private ImageView imgCommon;
    private int fromHour, fromMinute, toHour, toMinute;
    private int selectedDate;
    private int[] weekDayList = {R.id.btnMonday, R.id.btnTuesday,
                                 R.id.btnWednesday, R.id.btnThursday,
                                 R.id.btnFriday, R.id.btnSaturday,
                                 R.id.btnSunday, R.id.btnWeek};
    private int[] weekDayTimeIdList = {R.id.txtTimeMonday, R.id.txtTimeTuesday,
                                       R.id.txtTimeWednesday, R.id.txtTimeThursday,
                                       R.id.txtTimeFriday, R.id.txtTimeSaturday,
                                       R.id.txtTimeSunday, R.id.txtTimeWeek};
    private int[] timeslotIdList = {R.id.btn00General, R.id.btn01General,
                                    R.id.btn02General, R.id.btn03General,
                                    R.id.btn04General, R.id.btn05General,
                                    R.id.btn06General, R.id.btn07General,
                                    R.id.btn08General, R.id.btn09General,
                                    R.id.btn10General, R.id.btn11General,
                                    R.id.btn12General, R.id.btn13General,
                                    R.id.btn14General, R.id.btn15General,
                                    R.id.btn16General, R.id.btn17General,
                                    R.id.btn18General, R.id.btn19General,
                                    R.id.btn20General, R.id.btn21General,
                                    R.id.btn22General, R.id.btn23General};
    private int[] timeslotCheckIdList = {R.id.imgTime00General, R.id.imgTime01General,
                                         R.id.imgTime02General, R.id.imgTime03General,
                                         R.id.imgTime04General, R.id.imgTime05General,
                                         R.id.imgTime06General, R.id.imgTime07General,
                                         R.id.imgTime08General, R.id.imgTime09General,
                                         R.id.imgTime10General, R.id.imgTime11General,
                                         R.id.imgTime12General, R.id.imgTime13General,
                                         R.id.imgTime14General, R.id.imgTime15General,
                                         R.id.imgTime16General, R.id.imgTime17General,
                                         R.id.imgTime18General, R.id.imgTime19General,
                                         R.id.imgTime20General, R.id.imgTime21General,
                                         R.id.imgTime22General, R.id.imgTime23General};
    private ArrayList<Boolean> timeslotCheckList;

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

        txtCommon = findViewById(weekDayTimeIdList[selectedDate]);

        linearLayoutCommon = findViewById(R.id.layoutWorkHourGeneral);
        linearLayoutCommon.setVisibility(View.VISIBLE);

        String workHour = txtCommon.getText().toString();
        EditText txtTimeFromHour = findViewById(R.id.txtTimeFromHour);
        EditText txtTimeFromMinute = findViewById(R.id.txtTimeFromMinute);
        EditText txtTimeToHour = findViewById(R.id.txtTimeToHour);
        EditText txtTimeToMinute = findViewById(R.id.txtTimeToMinute);
        if (!workHour.equals("Custom")) {
            timeslotCheckList = new ArrayList<>();
            String fromTime = workHour.substring(0, workHour.indexOf(" - "));
            String toTime = workHour.substring(workHour.indexOf(" - ") + 3);
            fromHour = Integer.parseInt(fromTime.substring(0, fromTime.indexOf(":")));
            fromMinute = Integer.parseInt(fromTime.substring(fromTime.indexOf(":") + 1));
            toHour = Integer.parseInt(toTime.substring(0, toTime.indexOf(":")));
            toMinute = Integer.parseInt(toTime.substring(toTime.indexOf(":") + 1));
            txtTimeFromHour.setText(fromHour + "");
            txtTimeFromMinute.setText(fromMinute + "");
            txtTimeToHour.setText(toHour + "");
            txtTimeToMinute.setText(toMinute + "");

            for (int i = 0; i < timeslotCheckIdList.length; i++) {
                imgCommon = findViewById(timeslotCheckIdList[i]);
                if (fromMinute == 0) {
                    if (((i + 7) >= fromHour) && ((toHour - 3) > i)) {
                        imgCommon.setImageResource(R.drawable.ic_check_checked);
                        timeslotCheckList.add(true);
                        constraintLayoutCommon = findViewById(timeslotIdList[i]);
                        constraintLayoutCommon.setBackground(getResources().getDrawable(R.drawable.background_date_selected));
                    } else {
                        imgCommon.setImageResource(R.drawable.ic_check_unchecked);
                        timeslotCheckList.add(false);
                        constraintLayoutCommon = findViewById(timeslotIdList[i]);
                        constraintLayoutCommon.setBackground(getResources().getDrawable(R.drawable.background_date_default));
                    }
                } else if (((i + 8) >= fromHour) && ((toHour - 3) > i)) {
                    imgCommon.setImageResource(R.drawable.ic_check_checked);
                    timeslotCheckList.add(true);
                    constraintLayoutCommon = findViewById(timeslotIdList[i]);
                    constraintLayoutCommon.setBackground(getResources().getDrawable(R.drawable.background_date_selected));
                } else {
                    imgCommon.setImageResource(R.drawable.ic_check_unchecked);
                    timeslotCheckList.add(false);
                    constraintLayoutCommon = findViewById(timeslotIdList[i]);
                    constraintLayoutCommon.setBackground(getResources().getDrawable(R.drawable.background_date_default));
                }
            }
        } else {
            txtTimeFromHour.setText("");
            txtTimeFromMinute.setText("");
            txtTimeToHour.setText("");
            txtTimeToMinute.setText("");
            for (int i = 0; i < timeslotCheckIdList.length; i++) {
                timeslotCheckList.add(false);
                imgCommon = findViewById(timeslotCheckIdList[i]);
                imgCommon.setImageResource(R.drawable.ic_check_unchecked);
                constraintLayoutCommon = findViewById(timeslotIdList[i]);
                constraintLayoutCommon.setBackground(getResources().getDrawable(R.drawable.background_date_default));
            }
        }
    }

    public void onTimeslotGeneralClick(View view) {
        constraintLayoutCommon = findViewById(view.getId());
        for (int i = 0; i < timeslotIdList.length; i++) {
            if (timeslotIdList[i] == view.getId()) {
                imgCommon = findViewById(timeslotCheckIdList[i]);
                if (timeslotCheckList.get(i)) {
                    imgCommon.setImageResource(R.drawable.ic_check_unchecked);
                    timeslotCheckList.add(false);
                    constraintLayoutCommon.setBackground(getResources().getDrawable(R.drawable.background_date_default));
                } else {
                    imgCommon.setImageResource(R.drawable.ic_check_checked);
                    timeslotCheckList.add(true);
                    constraintLayoutCommon.setBackground(getResources().getDrawable(R.drawable.background_date_selected));
                }
            }
        }
        txtCommon = findViewById(R.id.txtSaveGeneral);
        txtCommon.setVisibility(View.VISIBLE);
    }
}