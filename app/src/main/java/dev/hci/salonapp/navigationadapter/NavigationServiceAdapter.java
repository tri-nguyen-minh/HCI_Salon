package dev.hci.salonapp.navigationadapter;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import dev.hci.salonapp.fragments.services.ColoringFragment;
import dev.hci.salonapp.fragments.services.CurlingFragment;
import dev.hci.salonapp.fragments.services.DiscountFragment;
import dev.hci.salonapp.fragments.services.HaircutFragment;
import dev.hci.salonapp.fragments.services.StraightenFragment;
import dev.hci.salonapp.fragments.services.StylingFragment;
import dev.hci.salonapp.fragments.services.TreatmentFragment;
import dev.hci.salonapp.fragments.services.WashFragment;

public class NavigationServiceAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    Activity activity;
    Fragment fragmentCommon;

    public NavigationServiceAdapter(FragmentManager fm, Context context, Activity activity, int totalTabs) {
        super(fm);
        this.context = context;
        this.activity = activity;
        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                fragmentCommon = new DiscountFragment();
                return fragmentCommon;
            case 1:
                fragmentCommon = new HaircutFragment();
                return fragmentCommon;
            case 2:
                fragmentCommon = new WashFragment();
                return fragmentCommon;
            case 3:
                fragmentCommon = new TreatmentFragment();
                return fragmentCommon;
            case 4:
                fragmentCommon = new ColoringFragment();
                return fragmentCommon;
            case 5:
                fragmentCommon = new CurlingFragment();
                return fragmentCommon;
            case 6:
                fragmentCommon = new StraightenFragment();
                return fragmentCommon;
            case 7:
                fragmentCommon = new StylingFragment();
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
