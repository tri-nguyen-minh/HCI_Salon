package dev.hci.salonapp.dtos;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import dev.hci.salonapp.fragments.DetailFragment;
import dev.hci.salonapp.fragments.ReviewFragment;
import dev.hci.salonapp.fragments.ServiceFragment;

public class NavigationSalonAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    Activity activity;

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
                DetailFragment detailFragment = new DetailFragment();
                return detailFragment;
            case 1:
                ServiceFragment serviceFragment = new ServiceFragment();
                return serviceFragment;
            case 2:
                ReviewFragment reviewFragment = new ReviewFragment();
                return reviewFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
