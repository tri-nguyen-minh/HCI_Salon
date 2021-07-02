package dev.hci.manager.navigations;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import dev.hci.manager.fragments.statistics.TodayStatisticFragment;
import dev.hci.manager.fragments.statistics.TotalStatisticFragment;
import dev.hci.manager.fragments.statistics.WeeklyStatisticsFragment;

public class NavigationStatisticsAdapter extends FragmentPagerAdapter {
    private Context context;
    private int totalTabs;
    private Activity activity;
    private Fragment fragmentCommon;
    private Bundle bundle;

    public NavigationStatisticsAdapter(FragmentManager fm, Context context, Activity activity, int totalTabs) {
        super(fm);
        this.context = context;
        this.activity = activity;
        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                fragmentCommon = new TodayStatisticFragment();
                return fragmentCommon;
            case 1:
                fragmentCommon = new WeeklyStatisticsFragment();
                return fragmentCommon;
            case 2:
                fragmentCommon = new TotalStatisticFragment();
                return fragmentCommon;
        }
        return null;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}