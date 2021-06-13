package dev.hci.salonapp.dtos;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import dev.hci.salonapp.fragments.BookingFragment;
import dev.hci.salonapp.fragments.HistoryFragment;
import dev.hci.salonapp.fragments.ProductFragment;
import dev.hci.salonapp.fragments.ProfileFragment;

public class NavigationMainAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    Activity activity;

    public NavigationMainAdapter(FragmentManager fm, Context context, Activity activity, int totalTabs) {
        super(fm);
        this.context = context;
        this.activity = activity;
        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                BookingFragment bookingFragment = new BookingFragment();
                return bookingFragment;
            case 1:
                ProductFragment productFragment = new ProductFragment();
                return productFragment;
            case 2:
                HistoryFragment historyFragment = new HistoryFragment();
                return historyFragment;
            case 3:
                ProfileFragment profileFragment = new ProfileFragment();
                return profileFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
