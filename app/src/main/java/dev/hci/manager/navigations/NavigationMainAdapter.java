package dev.hci.manager.navigations;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import dev.hci.manager.fragments.home.BookingFragment;
import dev.hci.manager.fragments.home.HomeFragment;
import dev.hci.manager.fragments.home.ProfileFragment;
import dev.hci.manager.fragments.home.RatingFragment;

public class NavigationMainAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    Activity activity;
    Fragment fragmentCommon;

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
                fragmentCommon = new HomeFragment();
                return fragmentCommon;
            case 1:
                fragmentCommon = new BookingFragment();
                return fragmentCommon;
            case 2:
                fragmentCommon = new RatingFragment();
                return fragmentCommon;
            case 3:
                fragmentCommon = new ProfileFragment();
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
