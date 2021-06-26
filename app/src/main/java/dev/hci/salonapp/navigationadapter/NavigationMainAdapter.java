package dev.hci.salonapp.navigationadapter;

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
//            case 0:
//                fragmentCommon = new BookingFragment();
//                return fragmentCommon;
//            case 1:
//                fragmentCommon = new ProductFragment();
//                return fragmentCommon;
//            case 2:
//                fragmentCommon = new HistoryFragment();
//                return fragmentCommon;
//            case 3:
//                fragmentCommon = new ProfileFragment();
//                return fragmentCommon;
//            default:
//                return null;
            case 0:
                fragmentCommon = new BookingFragment();
                return fragmentCommon;
            case 1:
                fragmentCommon = new HistoryFragment();
                return fragmentCommon;
            case 2:
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
