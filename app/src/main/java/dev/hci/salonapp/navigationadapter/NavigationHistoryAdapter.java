package dev.hci.salonapp.navigationadapter;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import dev.hci.salonapp.fragments.history.BookingHistoryFragment;
import dev.hci.salonapp.fragments.history.ShoppingHistoryFragment;

public class NavigationHistoryAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    Activity activity;
    Fragment fragmentCommon;

    public NavigationHistoryAdapter(FragmentManager fm, Context context, Activity activity, int totalTabs) {
        super(fm);
        this.context = context;
        this.activity = activity;
        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                fragmentCommon = new BookingHistoryFragment();
                return fragmentCommon;
            case 1:
                fragmentCommon = new ShoppingHistoryFragment();
                return fragmentCommon;
            case 2:
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}