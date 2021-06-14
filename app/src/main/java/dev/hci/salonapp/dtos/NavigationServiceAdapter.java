package dev.hci.salonapp.dtos;

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
                DiscountFragment discountFragment = new DiscountFragment();
                return discountFragment;
            case 1:
                HaircutFragment haircutFragment = new HaircutFragment();
                return haircutFragment;
            case 2:
                WashFragment washFragment = new WashFragment();
                return washFragment;
            case 3:
                TreatmentFragment treatmentFragment = new TreatmentFragment();
                return treatmentFragment;
            case 4:
                ColoringFragment coloringFragment = new ColoringFragment();
                return coloringFragment;
            case 5:
                CurlingFragment curlingFragment = new CurlingFragment();
                return curlingFragment;
            case 6:
                StraightenFragment straightenFragment = new StraightenFragment();
                return straightenFragment;
            case 7:
                StylingFragment stylingFragment = new StylingFragment();
                return stylingFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
