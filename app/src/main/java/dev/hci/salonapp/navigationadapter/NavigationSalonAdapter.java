package dev.hci.salonapp.navigationadapter;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import dev.hci.salonapp.fragments.DetailFragment;
import dev.hci.salonapp.fragments.ReviewFragment;
import dev.hci.salonapp.fragments.ServiceFragment;
import dev.hci.salonapp.fragments.services.WashFragment;

public class NavigationSalonAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    Activity activity;
    Fragment fragmentCommon;

    public NavigationSalonAdapter(FragmentManager fm, Context context, Activity activity, int totalTabs) {
        super(fm);
        this.context = context;
        this.activity = activity;
        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                fragmentCommon = new DetailFragment();
                return fragmentCommon;
            case 1:
                fragmentCommon = new ServiceFragment();
                return fragmentCommon;
            case 2:
                fragmentCommon = new ReviewFragment();
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
