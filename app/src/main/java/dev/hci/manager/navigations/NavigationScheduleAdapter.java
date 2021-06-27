package dev.hci.manager.navigations;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import dev.hci.manager.activities.ScheduleActivity;
import dev.hci.manager.fragments.home.HomeFragment;
import dev.hci.manager.fragments.home.ProfileFragment;
import dev.hci.manager.fragments.home.RatingFragment;
import dev.hci.manager.fragments.schedule.DailyScheduleFragment;
import dev.hci.manager.fragments.schedule.GeneralScheduleFragment;

public class NavigationScheduleAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    Activity activity;
    Fragment fragmentCommon;

    public NavigationScheduleAdapter(FragmentManager fm, Context context, Activity activity, int totalTabs) {
        super(fm);
        this.context = context;
        this.activity = activity;
        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                fragmentCommon = new GeneralScheduleFragment();
                return fragmentCommon;
            case 1:
                fragmentCommon = new DailyScheduleFragment();
                return fragmentCommon;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
