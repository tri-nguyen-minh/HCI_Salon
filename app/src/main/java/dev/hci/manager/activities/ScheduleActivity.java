package dev.hci.manager.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hci_salon_manager.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Date;

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
    private int[] timeslotDailyIdList = {R.id.btn00Daily, R.id.btn01Daily,
                                    R.id.btn02Daily, R.id.btn03Daily,
                                    R.id.btn04Daily, R.id.btn05Daily,
                                    R.id.btn06Daily, R.id.btn07Daily,
                                    R.id.btn08Daily, R.id.btn09Daily,
                                    R.id.btn10Daily, R.id.btn11Daily,
                                    R.id.btn12Daily, R.id.btn13Daily,
                                    R.id.btn14Daily, R.id.btn15Daily,
                                    R.id.btn16Daily, R.id.btn17Daily,
                                    R.id.btn18Daily, R.id.btn19Daily,
                                    R.id.btn20Daily, R.id.btn21Daily,
                                    R.id.btn22Daily, R.id.btn23Daily};
    private int[] timeslotDailyCheckIdList = {R.id.imgTime00Daily, R.id.imgTime01Daily,
                                        R.id.imgTime02Daily, R.id.imgTime03Daily,
                                        R.id.imgTime04Daily, R.id.imgTime05Daily,
                                        R.id.imgTime06Daily, R.id.imgTime07Daily,
                                        R.id.imgTime08Daily, R.id.imgTime09Daily,
                                        R.id.imgTime10Daily, R.id.imgTime11Daily,
                                        R.id.imgTime12Daily, R.id.imgTime13Daily,
                                        R.id.imgTime14Daily, R.id.imgTime15Daily,
                                        R.id.imgTime16Daily, R.id.imgTime17Daily,
                                        R.id.imgTime18Daily, R.id.imgTime19Daily,
                                        R.id.imgTime20Daily, R.id.imgTime21Daily,
                                        R.id.imgTime22Daily, R.id.imgTime23Daily};
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
                if (tabLayout.getSelectedTabPosition() == 1) {
                    timeslotCheckList = new ArrayList<>();
                    CalendarView calendarView = findViewById(R.id.calViewPicker);
                    calendarView.setMinDate((new Date().getTime()));
                    calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                        @Override
                        public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                            linearLayoutCommon = findViewById(R.id.layoutHolderDaily);
                            linearLayoutCommon.setVisibility(View.GONE);
                            linearLayoutCommon = findViewById(R.id.layoutWorkHourDaily);
                            linearLayoutCommon.setVisibility(View.VISIBLE);
                            fromHour = 8;
                            fromMinute = 0;
                            toHour = 20;
                            toMinute = 0;
                            EditText txtTimeFromHour = findViewById(R.id.txtTimeFromHourDaily);
                            EditText txtTimeFromMinute = findViewById(R.id.txtTimeFromMinuteDaily);
                            EditText txtTimeToHour = findViewById(R.id.txtTimeToHourDaily);
                            EditText txtTimeToMinute = findViewById(R.id.txtTimeToMinuteDaily);
                            txtTimeFromHour.setText("08");
                            txtTimeFromMinute.setText("00");
                            txtTimeToHour.setText("19");
                            txtTimeToMinute.setText("00");

                            for (int i = 0; i < timeslotDailyCheckIdList.length; i++) {
                                imgCommon = findViewById(timeslotDailyCheckIdList[i]);
                                if (fromMinute == 0) {
                                    if ((i >= fromHour) && (toHour > i)) {
                                        imgCommon.setImageResource(R.drawable.ic_check_checked);
                                        timeslotCheckList.add(true);
                                        constraintLayoutCommon = findViewById(timeslotDailyIdList[i]);
                                        constraintLayoutCommon.setBackground(getResources().getDrawable(R.drawable.background_date_selected));
                                    } else {
                                        imgCommon.setImageResource(R.drawable.ic_check_unchecked);
                                        timeslotCheckList.add(false);
                                        constraintLayoutCommon = findViewById(timeslotDailyIdList[i]);
                                        constraintLayoutCommon.setBackground(getResources().getDrawable(R.drawable.background_date_default));
                                    }
                                } else if (((i + 1) >= fromHour) && (toHour > i)) {
                                    imgCommon.setImageResource(R.drawable.ic_check_checked);
                                    timeslotCheckList.add(true);
                                    constraintLayoutCommon = findViewById(timeslotDailyIdList[i]);
                                    constraintLayoutCommon.setBackground(getResources().getDrawable(R.drawable.background_date_selected));
                                } else {
                                    imgCommon.setImageResource(R.drawable.ic_check_unchecked);
                                    timeslotCheckList.add(false);
                                    constraintLayoutCommon = findViewById(timeslotDailyIdList[i]);
                                    constraintLayoutCommon.setBackground(getResources().getDrawable(R.drawable.background_date_default));
                                }
                            }
                        }
                    });
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


        this.getSupportActionBar().hide();
    }

    public void onBackSchedule(View view) {
        Intent newIntent = new Intent(ScheduleActivity.this, HomeActivity.class);
        newIntent.putExtra("BACK_HOME", 2);
        startActivity(newIntent);
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

        linearLayoutCommon = findViewById(R.id.layoutHolderGeneral);
        linearLayoutCommon.setVisibility(View.GONE);
        linearLayoutCommon = findViewById(R.id.layoutWorkHourGeneral);
        linearLayoutCommon.setVisibility(View.VISIBLE);

        txtCommon = findViewById(weekDayTimeIdList[selectedDate]);

        String workHour = txtCommon.getText().toString();
        EditText txtTimeFromHour = findViewById(R.id.txtTimeFromHour);
        EditText txtTimeFromMinute = findViewById(R.id.txtTimeFromMinute);
        EditText txtTimeToHour = findViewById(R.id.txtTimeToHour);
        EditText txtTimeToMinute = findViewById(R.id.txtTimeToMinute);
        timeslotCheckList = new ArrayList<>();
        if (!workHour.equals("Custom")) {
            String fromTime = workHour.substring(0, workHour.indexOf(" - "));
            String toTime = workHour.substring(workHour.indexOf(" - ") + 3);
            fromHour = Integer.parseInt(fromTime.substring(0, fromTime.indexOf(":")));
            fromMinute = Integer.parseInt(fromTime.substring(fromTime.indexOf(":") + 1));
            toHour = Integer.parseInt(toTime.substring(0, toTime.indexOf(":")));
            toMinute = Integer.parseInt(toTime.substring(toTime.indexOf(":") + 1));
            txtTimeFromHour.setText((fromHour < 10) ? ("0" + fromHour + "") : (fromHour + ""));
            txtTimeFromMinute.setText((fromMinute < 10) ? ("0" + fromMinute + "") : (fromMinute + ""));
            txtTimeToHour.setText((toHour < 10) ? ("0" + toHour + "") : (toHour + ""));
            txtTimeToMinute.setText((toMinute < 10) ? ("0" + toMinute + "") : (toMinute + ""));

            for (int i = 0; i < timeslotCheckIdList.length; i++) {
                imgCommon = findViewById(timeslotCheckIdList[i]);
                if (fromMinute == 0) {
                    if ((i >= fromHour) && (toHour > i)) {
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
                } else if (((i + 1) >= fromHour) && (toHour > i)) {
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
                    timeslotCheckList.set(i, false);
                    constraintLayoutCommon.setBackground(getResources().getDrawable(R.drawable.background_date_default));
                } else {
                    imgCommon.setImageResource(R.drawable.ic_check_checked);
                    timeslotCheckList.set(i, true);
                    constraintLayoutCommon.setBackground(getResources().getDrawable(R.drawable.background_date_selected));
                }
            }
        }
        txtCommon = findViewById(R.id.txtSaveGeneral);
        txtCommon.setVisibility(View.VISIBLE);
    }

    public void onTimeslotDailyClick(View view) {
        constraintLayoutCommon = findViewById(view.getId());
        for (int i = 0; i < timeslotDailyIdList.length; i++) {
            if (timeslotDailyIdList[i] == view.getId()) {
                imgCommon = findViewById(timeslotDailyCheckIdList[i]);
                if (timeslotCheckList.get(i)) {
                    imgCommon.setImageResource(R.drawable.ic_check_unchecked);
                    timeslotCheckList.set(i, false);
                    constraintLayoutCommon.setBackground(getResources().getDrawable(R.drawable.background_date_default));
                } else {
                    imgCommon.setImageResource(R.drawable.ic_check_checked);
                    timeslotCheckList.set(i, true);
                    constraintLayoutCommon.setBackground(getResources().getDrawable(R.drawable.background_date_selected));
                }
            }
        }
        txtCommon = findViewById(R.id.txtSaveDaily);
        txtCommon.setVisibility(View.VISIBLE);
    }
}