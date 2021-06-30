package dev.hci.manager.navigations;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import dev.hci.manager.fragments.booking.CompletedBookingFragment;
import dev.hci.manager.fragments.booking.UpcomingBookingFragment;

public class NavigationBookingAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    Activity activity;
    Fragment fragmentCommon;

    public NavigationBookingAdapter(FragmentManager fm, Context context, Activity activity, int totalTabs) {
        super(fm);
        this.context = context;
        this.activity = activity;
        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                fragmentCommon = new CompletedBookingFragment();
                return fragmentCommon;
            default:
                fragmentCommon = new UpcomingBookingFragment();
                return fragmentCommon;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
